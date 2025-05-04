package com.dental.pos.service.impl;

import com.dental.pos.dto.patient.PatientDto;
import com.dental.pos.dto.service.ClinicServiceDto;
import com.dental.pos.dto.service.ClinicServiceSearchDto;
import com.dental.pos.entity.ClinicService;
import com.dental.pos.entity.Patient;
import com.dental.pos.exception.ClinicServiceNotFoundException;
import com.dental.pos.repository.clinicService.ClinicServiceRepository;
import com.dental.pos.service.ServiceClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceClinicServiceImpl implements ServiceClinicService {

    @Autowired
    private ClinicServiceRepository clinicServiceRepository;

    @Override
    public Page<ClinicServiceDto> getAllClinicService(Pageable pageable) {
        Page<ClinicService> clinicServicePage = clinicServiceRepository.findActiveClinicService(pageable);
        if (clinicServicePage.isEmpty()) {
            throw new ClinicServiceNotFoundException("No active clinic service found for the given search criteria.");
        }
        return clinicServicePage.map(this::convertToDto);
    }

    @Override
    public Page<ClinicServiceDto> searchClinicService(ClinicServiceSearchDto searchDto, Pageable pageable) {
        Page<ClinicService> clinicServicePage = clinicServiceRepository.searchClinicService(searchDto.getSearchName(), pageable);

        if (clinicServicePage.isEmpty()) {
            throw new ClinicServiceNotFoundException("No active clinic service found for the given search criteria.");
        }

        return clinicServicePage.map(this::convertToDto);
    }

    private ClinicServiceDto convertToDto(ClinicService clinicService) {
        return new ClinicServiceDto(clinicService);
    }

    public Optional<ClinicServiceDto> getClinicServiceById(Long id) {
        return clinicServiceRepository.findById(id).map(this::convertToDto);
    }

    public void saveClinicService(ClinicServiceDto clinicServiceDto) {
        if (clinicServiceDto == null) {
            throw new IllegalArgumentException("ClinicServiceDto cannot be null");
        }

        ClinicService clinicService = ClinicService.builder()
                .name(clinicServiceDto.getName())
                .description(clinicServiceDto.getDescription())
                .amount(clinicServiceDto.getAmount().doubleValue())
                .createdTime(new Date())
                .updatedTime(new Date())
                .delFlg(0)
                .build();
        clinicServiceRepository.save(clinicService);
    }

    public void updateClinicService(ClinicServiceDto clinicServiceDto) {
        ClinicService clinicService = clinicServiceRepository.findById(clinicServiceDto.getServiceId())
                .orElseThrow(() -> new ClinicServiceNotFoundException("Clinic Service not found"));
        clinicService.setName(clinicServiceDto.getName());
        clinicService.setDescription(clinicServiceDto.getDescription());
        clinicService.setAmount(clinicServiceDto.getAmount().doubleValue());
        clinicService.setUpdatedTime(new Date());
        clinicServiceRepository.save(clinicService);
    }

    public void deleteClinicService(Long id) {
        clinicServiceRepository.updateByDelFlg(id);
    }

    @Override
    public List<ClinicServiceDto> getClinicServiceList() {
        List<ClinicService> resultList = clinicServiceRepository.getClinicServiceList();
        List<ClinicServiceDto> clinicServiceDtoList = new ArrayList<>();
        for (ClinicService clinicService : resultList) {
            clinicServiceDtoList.add(new ClinicServiceDto(clinicService));
        }
        return clinicServiceDtoList ;
    }

}
