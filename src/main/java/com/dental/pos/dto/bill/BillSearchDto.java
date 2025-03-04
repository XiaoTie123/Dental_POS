package com.dental.pos.dto.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillSearchDto {

    private String patientName;

    private String patientPhone;
    
    private Integer doctorId;
}
