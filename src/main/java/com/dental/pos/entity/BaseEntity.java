package com.dental.pos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
public class BaseEntity {

    @Column(name = "created_at")
    private Date createdTime;

    @Column(name = "updated_at")
    private Date updatedTime;

    @Column(name = "del_flg")
    private Integer delFlg;
}
