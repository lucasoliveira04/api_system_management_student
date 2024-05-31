package com.system_management_student.system_management_student.modal.dto.ViewData;

import com.system_management_student.system_management_student.modal.entity.DataUsers;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.system_management_student.system_management_student.modal.entity.Login}
 */
public record LoginDto(
        Integer id,
        Date dateLogin,
        DataUsersDto user
  ) implements Serializable {
}