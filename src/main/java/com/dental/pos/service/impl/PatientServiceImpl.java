package com.dental.pos.service.impl;

import com.dental.pos.dto.patient.PatientDto;
import com.dental.pos.dto.patient.PatientSearchDto;
import com.dental.pos.entity.Patient;
import com.dental.pos.exception.PatientNotFoundException;
import com.dental.pos.repository.patient.PatientRepository;
import com.dental.pos.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Page<PatientDto> getAllPatients(Pageable pageable) {
        Page<Patient> patientPage = patientRepository.findActivePatients(pageable);
        if (patientPage.isEmpty()) {
            throw new PatientNotFoundException("No active patients found for the given search criteria.");
        }
        return patientPage.map(this::convertToDto);
    }

    @Override
    public List<PatientDto> getPatientList() {
        List<Patient> resultList = patientRepository.getPatientList();
        List<PatientDto> patientDtoList = new ArrayList<>();
        for (Patient patient : resultList) {
            patientDtoList.add(new PatientDto(patient));
        }
        return patientDtoList;
    }


    @Override
    public Page<PatientDto> searchPatients(PatientSearchDto searchDto, Pageable pageable) {
        Page<Patient> patientPage = patientRepository.searchPatients(searchDto.getSearchName(), searchDto.getSearchContact(), pageable);

        if (patientPage.isEmpty()) {
            throw new PatientNotFoundException("No active patients found for the given search criteria.");
        }

        return patientPage.map(this::convertToDto);
    }

    private PatientDto convertToDto(Patient patient) {
        return new PatientDto(patient);
    }

    public Optional<PatientDto> getPatientById(Long id) {
        return patientRepository.findById(id).map(this::convertToDto);
    }

    public void savePatient(PatientDto patientDto) {
        if (patientDto == null) {
            throw new IllegalArgumentException("PatientDto cannot be null");
        }

        Patient patient = Patient.builder()
                .ref(patientDto.getRef())
                .name(patientDto.getName())
                .age(patientDto.getAge())
                .phone(patientDto.getPhone())
                .address(patientDto.getAddress())
                .contactDetail(patientDto.getContactDetail())
                .doctorId(patientDto.getDoctorId())
                .createdTime(new Date())
                .updatedTime(new Date())
                .delFlg(0)
                .build();
        patientRepository.save(patient);
    }

    public void updatePatient(PatientDto patientDto) {
        Patient patient = patientRepository.findById(patientDto.getPatientId())
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));

        patient.setName(patientDto.getName());
        patient.setAge(patientDto.getAge());
        patient.setPhone(patientDto.getPhone());
        patient.setAddress(patientDto.getAddress());
        patient.setDoctorId(patientDto.getDoctorId());
        patient.setContactDetail(patientDto.getContactDetail());
        patient.setUpdatedTime(new Date());
        patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.updateByDelFlg(id);
    }

    public String generateNextRefCode() {
        String lastRefCode = patientRepository.getLastRefCode();
        if (lastRefCode == null || lastRefCode.isEmpty()) {
            return "REF_ONE_000001"; // Starting code
        }
        int numericPart = Integer.parseInt(lastRefCode.substring(8)); // Adjusted to skip "DPS-101-"
        String nextCode = String.format("REF-ONE-%05d", numericPart + 1);
        return nextCode;
    }
}
