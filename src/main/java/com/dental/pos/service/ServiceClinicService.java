package com.dental.pos.service;

import com.dental.pos.dto.patient.PatientDto;
import com.dental.pos.dto.service.ClinicServiceDto;
import com.dental.pos.dto.service.ClinicServiceSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ServiceClinicService {

    List<ClinicServiceDto> getClinicServiceList();

    Page<ClinicServiceDto> getAllClinicService(Pageable pageable);

    Page<ClinicServiceDto> searchClinicService(ClinicServiceSearchDto searchDto, Pageable pageable);

    Optional<ClinicServiceDto> getClinicServiceById(Long id);

    void saveClinicService(ClinicServiceDto clinicServiceDto);

    void updateClinicService(ClinicServiceDto clinicServiceDto);
    void deleteClinicService(Long id);
}
