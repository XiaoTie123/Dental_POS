package com.dental.pos.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSearchDto {

    private String patientName;

    private String patientPhone;
    
    private Integer doctorId;
}
