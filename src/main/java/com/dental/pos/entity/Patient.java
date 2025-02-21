package com.dental.pos.entity;

import com.dental.pos.util.common.TableConstants;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = TableConstants.TBL_PATIENT)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Patient extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "ref")
    private String ref;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "age")
    private String age;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_detail")
    private String contactDetail;

    @Column(name = "doctor_id")
    private Integer doctorId;
}