package com.system_management_student.system_management_student.modal.dto.ViewData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.system_management_student.system_management_student.modal.entity.DataStudents;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.system_management_student.system_management_student.modal.entity.DataStudents}
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
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
        return DataStudentsDto.builder()
                .id(students.getId())
                .nota_1(students.getNota_1())
                .nota_2(students.getNota_2())
                .nota_3(students.getNota_3())
                .nota_4(students.getNota_4())
                .nota_5(students.getNota_5())
                .mean_result_final(students.getMean_result_final())
                .result(students.getResult())
                .date_insert_nota(students.getDate_insert_nota())
                .build();
    }
}