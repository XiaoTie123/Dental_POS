package com.dental.pos.repository.clinicService;

import com.dental.pos.entity.ClinicService;
import com.dental.pos.entity.Patient;

import java.util.List;

public interface ClinicServiceCustomRepository {

    void updateByDelFlg(Long id);

    List<ClinicService> getClinicServiceList();
}