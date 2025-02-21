package com.dental.pos.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientSearchDto {

    private String searchName;

    private String searchContact;
}
