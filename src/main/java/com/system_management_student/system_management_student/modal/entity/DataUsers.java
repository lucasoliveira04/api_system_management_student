package com.system_management_student.system_management_student.modal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class DataUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "rg")
    private String rg;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "student")
    private List<DataStudents> students;

    @OneToOne(mappedBy = "user")
    private Register register;

    @OneToMany(mappedBy = "user")
    private List<Login> logins;
}
