package com.dental.pos.dto.bill;

import com.dental.pos.entity.Bill;
import com.dental.pos.util.common.CommonConstants;
import com.dental.pos.util.common.CommonUtil;
import com.dental.pos.util.enums.Doctor;
import com.dental.pos.util.enums.Transfer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

    private Long billId;

    private Long patientId;

    private String patientName;

    private String patientContact;

    private Integer doctorId;

    private String doctorName;

    private Integer percentage;

    private String percentageDesc;

    private Integer totalAmount;

    private String totalAmountDesc;

    private Integer taxAmount;

    private String taxAmountDesc;

    private Integer percentageAmount;

    private String percentageAmountDesc;

    private Integer netAmount;

    private String netAmountDesc;

    private Integer tax;

    private String taxDesc;

    private Integer transfer;

    private String transferDesc;

    private String createdDateTime;

    private Long serviceId;

    private List<BillDetailDto> billDetailDtoList = new ArrayList<>();

    public BillDto(Bill entity) {

        if (entity != null) {

            this.billId = entity.getBillId();

            if(entity.getPatient() != null){
                this.patientId = entity.getPatient().getPatientId();
                this.patientName = entity.getPatient().getName();
                this.patientContact = entity.getPatient().getPhone();
            }

            this.doctorId = entity.getDoctorId();

            this.doctorName = Doctor.getDescriptionByCode(this.doctorId);

            this.percentage = entity.getPercentage();

            this.percentageDesc = entity.getPercentage() + "%";

            this.totalAmount = (int)Math.round(entity.getTotalAmount() );

            this.totalAmountDesc = CommonUtil.formatNumberDouble(entity.getTotalAmount()) + " MMK";

            this.taxAmount = (int)Math.round(entity.getTaxAmount() );

            this.taxAmountDesc = CommonUtil.formatNumberDouble(entity.getTaxAmount()) + " MMK";

            this.percentageAmount = (int)Math.round(entity.getPercentageAmount() );

            this.percentageAmountDesc = CommonUtil.formatNumberDouble(entity.getPercentageAmount()) + " MMK";

            this.netAmount = (int)Math.round(entity.getNetAmount() );

            this.netAmountDesc = CommonUtil.formatNumberDouble(entity.getNetAmount()) + " MMK";

            this.tax = entity.getTax();

            this.taxDesc = entity.getTax() + "%";

            this.transfer = entity.getTransfer();

            this.transferDesc = Transfer.getDescriptionByCode(this.transfer);

            this.createdDateTime = CommonUtil.dateToString(CommonConstants.STD_DATE_TIME_FORMAT, entity.getCreatedTime());
        }
    }
}