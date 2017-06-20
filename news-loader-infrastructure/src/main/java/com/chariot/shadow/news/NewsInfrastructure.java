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

    public NewsEntity find(String newsId) {
        CriteriaBuilder b = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsEntity> query = b.createQuery(NewsEntity.class);
        query.where(newsDetectionPredicatesByNewsId(b, query.from(NewsEntity.class), newsId));
        List<NewsEntity> newsEntities = entityManager.createQuery(query).getResultList();
        return newsEntities.size() == 0 ? null : newsEntities.get(0);
    }

    public List<NewsEntity> retrieveInsertNews() {
        CriteriaBuilder b = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsEntity> query = b.createQuery(NewsEntity.class);
        query.where(newsDetectionPredicatesByActionCode(b, query.from(NewsEntity.class), "I"));
        List<NewsEntity> newsEntities = entityManager.createQuery(query).getResultList();
        return newsEntities.size() == 0 ? null : newsEntities;
    }

    public List<NewsEntity> retrieveDeleteNews() {
        CriteriaBuilder b = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsEntity> query = b.createQuery(NewsEntity.class);
        query.where(newsDetectionPredicatesByActionCode(b, query.from(NewsEntity.class), "D"));
        List<NewsEntity> newsEntities = entityManager.createQuery(query).getResultList();
        return newsEntities.size() == 0 ? null : newsEntities;
    }

    private Predicate[] newsDetectionPredicatesByActionCode(CriteriaBuilder builder, Root<NewsEntity> root, String actionCode) {
        return new Predicate[]{
                builder.equal(root.get(NewsEntity_.actionCode), actionCode)
        };
    }

    private Predicate[] newsDetectionPredicatesByNewsId(CriteriaBuilder builder, Root<NewsEntity> root, String newsId) {
        return new Predicate[]{
                builder.equal(root.get(NewsEntity_.newsId), newsId)
        };
    }
}
