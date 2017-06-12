package com.chariot.shadow.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Trung Vu on 2017/06/11.
 */
@Component
public class NewsInfrastructure {

    @Autowired
    private EntityManager entityManager;

    private NewsEntity find(String supplierId, String newsId) {
        CriteriaBuilder b = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsEntity> query = b.createQuery(NewsEntity.class);
        query.where(newsDetectionPredicates(b, query.from(NewsEntity.class), supplierId, newsId));
        List<NewsEntity> newsEntities = entityManager.createQuery(query).getResultList();
        return newsEntities.size() == 0 ? null : newsEntities.get(0);
    }

    private Predicate[] newsDetectionPredicates(CriteriaBuilder builder, Root<NewsEntity> root, String supplierId, String newsId) {
        return new Predicate[]{
                builder.equal(root.get(NewsEntity_.supplierId), supplierId),
                builder.equal(root.get(NewsEntity_.newsId), newsId)
        };
    }

    public NewsEntity find(String id) {
        return entityManager.find(NewsEntity.class, id);
    }
}
