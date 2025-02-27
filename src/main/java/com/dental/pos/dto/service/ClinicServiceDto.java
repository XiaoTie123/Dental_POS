package com.dental.pos.dto.service;

import com.dental.pos.entity.ClinicService;
import com.dental.pos.util.common.CommonConstants;
import com.dental.pos.util.common.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicServiceDto {

    private Long serviceId;

    private String name;

    private String description;

    private Integer amount;

    private String amountDesc;

    private String createdDateTime;

    public ClinicServiceDto(ClinicService entity) {

        if (entity != null) {

            this.serviceId = entity.getServiceId();

            this.name = entity.getName();

            this.description = entity.getDescription();

            this.amount = (int)Math.round(entity.getAmount() );

            this.amountDesc = CommonUtil.formatNumberDouble(entity.getAmount()) + " MMK";


            this.createdDateTime = CommonUtil.dateToString(CommonConstants.MYSQL_DATE_FORMAT, entity.getCreatedTime());

        }
    }
}