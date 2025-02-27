package com.dental.pos.repository.clinicService;

import com.dental.pos.entity.Appointment;
import com.dental.pos.entity.ClinicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicServiceRepository extends JpaRepository<ClinicService, Long>, ClinicServiceCustomRepository {

    @Query("SELECT s FROM ClinicService s WHERE s.delFlg = 0 AND " +
            " (:name IS NULL OR s.name LIKE CONCAT('%', :name, '%')) order by s.serviceId desc ")
    Page<ClinicService> searchClinicService(String name, Pageable pageable);


    @Query("SELECT s FROM ClinicService s WHERE s.delFlg = 0 order by s.serviceId desc")
    Page<ClinicService> findActiveClinicService(Pageable pageable);
}
