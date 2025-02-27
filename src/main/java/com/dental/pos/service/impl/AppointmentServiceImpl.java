package com.dental.pos.service.impl;

import com.dental.pos.dto.appointment.AppointmentDto;
import com.dental.pos.dto.appointment.AppointmentSearchDto;
import com.dental.pos.entity.Appointment;
import com.dental.pos.entity.Patient;
import com.dental.pos.exception.AppointmentNotFoundException;
import com.dental.pos.repository.appointment.AppointmentRepository;
import com.dental.pos.service.AppointmentService;
import com.dental.pos.util.common.CommonConstants;
import com.dental.pos.util.common.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Page<AppointmentDto> getAllAppointment(Pageable pageable) {
        Page<Appointment> appintmentPage = appointmentRepository.findActiveAppointment(pageable);
        if (appintmentPage.isEmpty()) {
            throw new AppointmentNotFoundException("No active appointment found for the given search criteria.");
        }
        return appintmentPage.map(this::convertToDto);
    }

    @Override
    public Page<AppointmentDto> searchAppointment(AppointmentSearchDto searchDto, Pageable pageable) {
        Page<Appointment> appointmentPage = appointmentRepository.searchAppointment(searchDto, pageable);

        if (appointmentPage.isEmpty()) {
            throw new AppointmentNotFoundException("No active appointment found for the given search criteria.");
        }

        return appointmentPage.map(this::convertToDto);
    }

    private AppointmentDto convertToDto(Appointment appointment) {
        return new AppointmentDto(appointment);
    }

    public Optional<AppointmentDto> getAppointmentById(Long id) {
        return appointmentRepository.findById(id).map(this::convertToDto);
    }

    public void saveAppointment(AppointmentDto appointmentDto) {
        if (appointmentDto == null) {
            throw new IllegalArgumentException("AppointmentDto cannot be null");
        }

        Patient resultPatient = new Patient();
        if(appointmentDto.getPatientId() != null){
            resultPatient.setPatientId(appointmentDto.getPatientId());
        }

        Appointment appointment = Appointment.builder()
                .patient(resultPatient)
                .doctorId(appointmentDto.getDoctorId())
                .date(CommonUtil.stringToDate(CommonConstants.MYSQL_DATE_FORMAT, appointmentDto.getDateDesc()))
                .timeId(appointmentDto.getTimeId())
                .status(appointmentDto.getStatus())
                .createdTime(new Date())
                .updatedTime(new Date())
                .delFlg(0)
                .build();
        appointmentRepository.save(appointment);
    }

    public void updateAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = appointmentRepository.findById(appointmentDto.getAppointmentId())
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));
        appointment.setStatus(appointmentDto.getStatus());
        appointment.setUpdatedTime(new Date());
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.updateByDelFlg(id);
    }

}
