package com.dental.pos.repository.appointment;

public interface AppointmentCustomRepository {

    void updateByDelFlg(Long id);

    void updateByStatus(Long patientId);


}