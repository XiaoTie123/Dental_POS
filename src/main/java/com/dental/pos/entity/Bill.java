package com.dental.pos.entity;

import com.dental.pos.util.common.TableConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = TableConstants.TBL_BILLING)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Bill extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long billId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "percentage")
    private Integer percentage;

    @Column(name = "percentage_amount")
    private Double percentageAmount;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "tax")
    private Integer tax;

    @Column(name = "tax_amount")
    private Double taxAmount;

    @Column(name = "net_amount")
    private Double netAmount;

    @Column(name = "transfer")
    private Integer transfer;
}