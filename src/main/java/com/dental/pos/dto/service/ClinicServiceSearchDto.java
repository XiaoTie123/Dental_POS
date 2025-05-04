package com.dental.pos.dto.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicServiceSearchDto {

    private String searchName;
    private String searchDesc;

}
