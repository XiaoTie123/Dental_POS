package com.dental.pos.repository.appointment;

import com.dental.pos.util.enums.Doctor;
import com.dental.pos.util.enums.Status;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Transactional
public class AppointmentCustomRepositoryImpl implements AppointmentCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional // Ensure a transaction is active
    public void updateByDelFlg(Long id) {
        // Correct JPQL update query using parameter binding
        String jpql = "UPDATE Appointment p SET p.delFlg = 1 WHERE p.appointmentId = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        query.executeUpdate(); // Execute update inside a transaction
    }

    @Override
    @Transactional // Ensure a transaction is active
    public void updateByStatus(Long patientId) {
        // Obtain current date in Java
        Date now = new Date();
        // Correct JPQL update query using parameter binding
        String jpql = "UPDATE Appointment p SET p.status = :status WHERE p.patient.id = :patientId AND p.date = :currentDate";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("status", Status.FINISHED.getCode());
        query.setParameter("patientId", patientId);
        query.setParameter("currentDate", now, TemporalType.DATE); // Pass 'now' as parameter and specify the temporal type
        query.executeUpdate(); // Execute update inside a transaction
    }
}
