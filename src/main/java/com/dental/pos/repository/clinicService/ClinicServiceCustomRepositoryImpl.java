package com.dental.pos.repository.clinicService;

import com.dental.pos.entity.ClinicService;
import com.dental.pos.entity.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ClinicServiceCustomRepositoryImpl implements ClinicServiceCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional // Ensure a transaction is active
    public void updateByDelFlg(Long id) {
        // Correct JPQL update query using parameter binding
        String jpql = "UPDATE ClinicService s SET s.delFlg = 1 WHERE s.serviceId = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        query.executeUpdate(); // Execute update inside a transaction
    }

    @Override
    public List<ClinicService> getClinicServiceList() {
        String jpql = "SELECT s FROM ClinicService s WHERE s.delFlg = 0";
        TypedQuery<ClinicService> query = entityManager.createQuery(jpql, ClinicService.class);
        return query.getResultList(); // Return list of active clinicServiceList
    }
}
