package com.system_management_student.system_management_student.modal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "date_register")
    private LocalDateTime dateRegister;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private DataUsers user;
}
