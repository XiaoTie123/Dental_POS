package com.dental.pos.service;

import com.dental.pos.dto.appointment.AppointmentDto;
import com.dental.pos.dto.appointment.AppointmentSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AppointmentService {

    Page<AppointmentDto> getAllAppointment(Pageable pageable);

    Page<AppointmentDto> searchAppointment(AppointmentSearchDto searchDto, Pageable pageable);

    Optional<AppointmentDto> getAppointmentById(Long id);

    void saveAppointment(AppointmentDto appointmentDto);

    void updateAppointment(AppointmentDto appointmentDto);

    void deleteAppointment(Long id);
}
