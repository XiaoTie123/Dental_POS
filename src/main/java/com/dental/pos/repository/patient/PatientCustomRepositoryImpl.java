package com.dental.pos.repository.patient;

import com.dental.pos.entity.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Pageable;

@Repository
@Transactional
public class PatientCustomRepositoryImpl implements PatientCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional // Ensure a transaction is active
    public void updateByDelFlg(Long id) {
        // Correct JPQL update query using parameter binding
        String jpql = "UPDATE Patient p SET p.delFlg = 1 WHERE p.patientId = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        query.executeUpdate(); // Execute update inside a transaction
    }

    @Override
    public String getLastRefCode() {
        try {
            String jpql = "SELECT p.ref FROM Patient p WHERE p.delFlg = 0 AND p.ref IS NOT NULL ORDER BY p.createdTime DESC";
            TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
            query.setMaxResults(1); // Equivalent to LIMIT 1
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // Handle case where no result is found
        }
    }

    @Override
    public List<Patient> getPatientList() {
        String jpql = "SELECT p FROM Patient p WHERE p.delFlg = 0";
        TypedQuery<Patient> query = entityManager.createQuery(jpql, Patient.class);
        return query.getResultList(); // Return list of active patients
    }

}
