package com.dental.pos.repository.patient;

import com.dental.pos.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, PatientCustomRepository {

    @Query("SELECT p FROM Patient p WHERE p.delFlg = 0 AND " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:contact IS NULL OR p.phone LIKE CONCAT('%', :contact, '%')) order by p.patientId desc ")
    Page<Patient> searchPatients(String name, String contact, Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE p.delFlg = 0 order by p.patientId desc")
    Page<Patient> findActivePatients(Pageable pageable);
}
