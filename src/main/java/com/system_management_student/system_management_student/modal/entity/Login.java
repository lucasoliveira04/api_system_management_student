package com.system_management_student.system_management_student.modal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "date_login")
    private Date dateLogin;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private DataUsers user;
}
