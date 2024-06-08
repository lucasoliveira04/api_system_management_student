package com.system_management_student.system_management_student.modal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class DataStudents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double nota_1;
    private Double nota_2;
    private Double nota_3;
    private Double nota_4;
    private Double nota_5;
    private Double mean_result_final;
    private String result;
    private LocalDateTime date_insert_nota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_student")
    private DataUsers student;
}
