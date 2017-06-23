package com.chariot.shadow.news;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Trung Vu on 2017/06/11.
 */
public class NewsInfrastructure {

    private EntityManager entityManager;

    private static final String NEWS_CRAWLER_PERSISTENCE_UNIT_NAME = "news-loader";

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(NEWS_CRAWLER_PERSISTENCE_UNIT_NAME);

    private void beforeExecute() {
        entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    private void afterExecute() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public NewsEntity find(String newsId) {
        beforeExecute();
        CriteriaBuilder b = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsEntity> query = b.createQuery(NewsEntity.class);
        query.where(newsDetectionPredicatesByNewsId(b, query.from(NewsEntity.class), newsId));
        List<NewsEntity> newsEntities = entityManager.createQuery(query).getResultList();
        afterExecute();
        return newsEntities.size() == 0 ? null : newsEntities.get(0);
    }

    public List<NewsEntity> retrieveInsertNews() {
        beforeExecute();
        CriteriaBuilder b = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsEntity> query = b.createQuery(NewsEntity.class);
        query.where(newsDetectionPredicatesByActionCode(b, query.from(NewsEntity.class), "I"));
        List<NewsEntity> newsEntities = entityManager.createQuery(query).getResultList();
        afterExecute();
        return newsEntities.size() == 0 ? null : newsEntities;
    }

    public List<NewsEntity> retrieveDeleteNews() {
        beforeExecute();
        CriteriaBuilder b = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsEntity> query = b.createQuery(NewsEntity.class);
        query.where(newsDetectionPredicatesByActionCode(b, query.from(NewsEntity.class), "D"));
        List<NewsEntity> newsEntities = entityManager.createQuery(query).getResultList();
        afterExecute();
        return newsEntities.size() == 0 ? null : newsEntities;
    }

    private Predicate[] newsDetectionPredicatesByActionCode(CriteriaBuilder builder, Root<NewsEntity> root, String actionCode) {
        return new Predicate[]{
                builder.equal(root.get(NewsEntity_.actionCode), actionCode),
                builder.equal(root.get(NewsEntity_.updateSign), 1)
        };
    }

    private Predicate[] newsDetectionPredicatesByNewsId(CriteriaBuilder builder, Root<NewsEntity> root, String newsId) {
        return new Predicate[]{
                builder.equal(root.get(NewsEntity_.newsId), newsId)
        };
    }

    public void updateUpdateSign(String newsId, int updateSign) {
        NewsEntity newsEntity = find(newsId);
        beforeExecute();
        newsEntity.setUpdateSign(updateSign);
        entityManager.merge(newsEntity);
        afterExecute();
    }
}
