package com.system_management_student.system_management_student.modal.dto.ViewData;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.system_management_student.system_management_student.modal.entity.Register}
 */
public record RegisterDto(
        Integer id,
        String email,
        String password,
        Date dateRegister,
        DataUsersDto dataUsersDto
) implements Serializable {
}