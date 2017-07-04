package com.chariot.shadow.company;

import com.chariot.shadow.economy.company.CompanyEntity;
import com.chariot.shadow.economy.company.CompanyEntity_;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * Created by Trung Vu on 2017/07/04.
 */
public class CompanyInfrastructure {

    private EntityManager entityManager;

    private static final String COMPANY_LOADER_PERSISTENCE_UNIT_NAME = "company-loader";

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(COMPANY_LOADER_PERSISTENCE_UNIT_NAME);

    private void beforeExecute() {
        entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    private void afterExecute() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public CompanyEntity find(String companyId, String supplierId) {
        beforeExecute();
        CriteriaBuilder b = entityManager.getCriteriaBuilder();
        CriteriaQuery<CompanyEntity> query = b.createQuery(CompanyEntity.class);
        query.where(companyDetectionPredicatesById(b, query.from(CompanyEntity.class), companyId, supplierId));
        List<CompanyEntity> companyEntities = entityManager.createQuery(query).getResultList();
        afterExecute();
        return companyEntities.size() == 0 ? null : companyEntities.get(0);
    }

    public List<CompanyEntity> retrieveInsertCompanies() {
        beforeExecute();
        CriteriaBuilder b = entityManager.getCriteriaBuilder();
        CriteriaQuery<CompanyEntity> query = b.createQuery(CompanyEntity.class);
        query.where(companyDetectionPredicatesByActionCode(b, query.from(CompanyEntity.class), "I"));
        List<CompanyEntity> companyEntities = entityManager.createQuery(query).getResultList();
        afterExecute();
        return companyEntities.size() == 0 ? Collections.emptyList() : companyEntities;
    }

    public List<CompanyEntity> retrieveDeleteCompanies() {
        beforeExecute();
        CriteriaBuilder b = entityManager.getCriteriaBuilder();
        CriteriaQuery<CompanyEntity> query = b.createQuery(CompanyEntity.class);
        query.where(companyDetectionPredicatesByActionCode(b, query.from(CompanyEntity.class), "D"));
        List<CompanyEntity> companyEntities = entityManager.createQuery(query).getResultList();
        afterExecute();
        return companyEntities.size() == 0 ? Collections.emptyList() : companyEntities;
    }

    private Predicate[] companyDetectionPredicatesByActionCode(CriteriaBuilder builder, Root<CompanyEntity> root, String actionCode) {
        return new Predicate[]{
                builder.equal(root.get(CompanyEntity_.actionCode), actionCode),
                builder.equal(root.get(CompanyEntity_.updateSign), 1)
        };
    }

    private Predicate[] companyDetectionPredicatesById(CriteriaBuilder builder, Root<CompanyEntity> root, String companyId, String supplierId) {
        return new Predicate[]{
                builder.equal(root.get(CompanyEntity_.companyId), companyId),
                builder.equal(root.get(CompanyEntity_.supplierId), supplierId)
        };
    }

    public void updateUpdateSign(String companyId, String supplierId, int updateSign) {
        CompanyEntity company = find(companyId, supplierId);
        if (company != null) {
            beforeExecute();
            company.setUpdateSign(updateSign);
            entityManager.merge(company);
            afterExecute();
        }
    }
}
