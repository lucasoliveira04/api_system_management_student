package com.system_management_student.system_management_student.modal.dto.ViewData;

import com.system_management_student.system_management_student.modal.entity.Login;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.system_management_student.system_management_student.modal.entity.DataUsers}
 */
public record DataUsersDto(
        Integer id,
        String name,
        String email,
        String rg,
        String cpf,
        Date dateOfBirth,
        LoginDto login
) implements Serializable {
}