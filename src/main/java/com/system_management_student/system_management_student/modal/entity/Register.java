package com.system_management_student.system_management_student.modal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Register {
    @Id
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "date_register")
    private LocalDateTime dateRegister;

    @Column(name = "limit_password")
    private Integer limitPassword;

    @Column(name = "last_password_change")
    private LocalDateTime lastPasswordChange;

    @Column(name = "password_change_count")
    private Integer passwordChangeCount;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private DataUsers user;
}
