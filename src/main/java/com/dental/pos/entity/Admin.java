package com.dental.pos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "admin") // Use the correct table name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admin_id;

    private String name;
    private String email;
    private String password;
    private Date created_at;
    private Date updated_at;
    private String role;
    private Integer delFlg;

}
