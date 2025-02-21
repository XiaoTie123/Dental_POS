package com.dental.pos.repository.patient;

import com.dental.pos.entity.Patient;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientCustomRepository {

    void updateByDelFlg(Long id);

    String getLastRefCode();

    List<Patient> getPatientList();
}