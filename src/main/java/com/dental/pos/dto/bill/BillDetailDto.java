package com.dental.pos.dto.bill;

import com.dental.pos.util.common.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailDto {

    private Long billDetailId;

    private Long serviceId;

    private String serviceName;

    private Integer serviceAmount;

    private String serviceAmountDesc;

    private Integer qty;

    public BillDetailDto(Long serviceId, String serviceName, Integer serviceAmount, Integer qty) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceAmount = serviceAmount;
        this.serviceAmountDesc = CommonUtil.formatNumberDouble(serviceAmount) + " MMK";
        this.qty = qty;
    }

}
