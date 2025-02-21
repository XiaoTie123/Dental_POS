package com.dental.pos.service;

import com.dental.pos.dto.patient.PatientDto;
import com.dental.pos.dto.patient.PatientSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    List<PatientDto> getPatientList();

    Page<PatientDto> getAllPatients(Pageable pageable);

    Page<PatientDto> searchPatients(PatientSearchDto searchDto, Pageable pageable);

    Optional<PatientDto> getPatientById(Long id);

    void savePatient(PatientDto patientDto);

    void updatePatient(PatientDto patientDto);

    void deletePatient(Long id);

    String generateNextRefCode();
}

