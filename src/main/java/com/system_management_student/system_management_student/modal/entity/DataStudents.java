package com.system_management_student.system_management_student.modal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class DataStudents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Double nota_1;
    private Double nota_2;
    private Double nota_3;
    private Double nota_4;
    private Double nota_5;
    private Double mean_result_final;
    private String result;
    private Date date_insert_nota;

    @ManyToOne
    @JoinColumn(name = "id_student", referencedColumnName = "id")
    private DataUsers student;

    public Double resultNotas() {
        List<Double> notas = Arrays.asList(this.nota_1, this.nota_2, this.nota_3, this.nota_4, this.nota_5);
        verificNotas(notas);

        double sum = notas.stream().mapToDouble(Double::doubleValue).sum();
        this.mean_result_final = sum / notas.size();

        if (this.mean_result_final < 7) {
            this.result = "reprovado";
        } else {
            this.result = "aprovado";
        }

        return this.mean_result_final;
    }

    private void verificNotas(List<Double> notas) {
        for (Double nota : notas) {
            if (nota < 0 || nota > 10) {
                throw new IllegalArgumentException("Nota inválida: " + nota + ". Notas devem estar entre 0 e 10.");
            }
        }
    }
}
