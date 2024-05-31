package com.system_management_student.system_management_student.modal.dto.ViewData;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.system_management_student.system_management_student.modal.entity.DataStudents}
 */
public record DataStudentsDto(
        Integer id,
        Double nota_1,
        Double nota_2,
        Double nota_3,
        Double nota_4,
        Double nota_5,
        Double mean_result_final,
        String result,
        Date date_insert_nota
) implements Serializable {
}