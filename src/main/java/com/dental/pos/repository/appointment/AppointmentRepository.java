package com.dental.pos.repository.appointment;

import com.dental.pos.dto.appointment.AppointmentSearchDto;
import com.dental.pos.entity.Appointment;
import com.dental.pos.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>, AppointmentCustomRepository {

    @Query("SELECT a FROM Appointment a WHERE a.delFlg = 0 " +
            "AND (:#{#searchDto.patientName} IS NULL OR LOWER(a.patient.name) LIKE LOWER(CONCAT('%', :#{#searchDto.patientName}, '%'))) " +
            "AND (:#{#searchDto.patientPhone} IS NULL OR a.patient.phone LIKE CONCAT('%', :#{#searchDto.patientPhone}, '%')) " +
            "AND (:#{#searchDto.doctorId} IS NULL OR a.doctorId = :#{#searchDto.doctorId}) order by a.appointmentId desc")
    Page<Appointment> searchAppointment(AppointmentSearchDto searchDto, Pageable pageable);


    @Query("SELECT a FROM Appointment a WHERE a.delFlg = 0 order by a.appointmentId desc")
    Page<Appointment> findActiveAppointment(Pageable pageable);
}
