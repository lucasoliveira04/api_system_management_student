package com.system_management_student.system_management_student.modal.dto.ViewData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.system_management_student.system_management_student.modal.entity.DataStudents;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.system_management_student.system_management_student.modal.entity.DataStudents}
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataStudentsDto implements Serializable {
    private Integer id;
    private Double nota_1;
    private Double nota_2;
    private Double nota_3;
    private Double nota_4;
    private Double nota_5;
    private Double mean_result_final;
    private String result;
    private Date date_insert_nota;

    public static DataStudentsDto fromEntity(DataStudents students){
        DataStudentsDto dto = new DataStudentsDto();
        dto.setId(students.getId());
        dto.setNota_1(students.getNota_1());
        dto.setNota_2(students.getNota_2());
        dto.setNota_3(students.getNota_3());
        dto.setNota_4(students.getNota_4());
        dto.setNota_5(students.getNota_5());
        dto.setMean_result_final(students.getMean_result_final());
        dto.setResult(students.getResult());
        dto.setDate_insert_nota(students.getDate_insert_nota());
        return dto;
    }
}