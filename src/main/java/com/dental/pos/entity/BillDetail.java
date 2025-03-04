package com.dental.pos.entity;

import com.dental.pos.util.common.TableConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = TableConstants.TBL_BILLDETAIL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BillDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_detail_id")
    private Long billDetailId;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ClinicService serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_amount")
    private Double serviceAmount;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "total_amount")
    private Double totalAmount;

}