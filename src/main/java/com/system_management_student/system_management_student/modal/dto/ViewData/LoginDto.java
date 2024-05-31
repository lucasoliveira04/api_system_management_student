package com.system_management_student.system_management_student.modal.dto.ViewData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.system_management_student.system_management_student.modal.entity.Login;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.system_management_student.system_management_student.modal.entity.Login}
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDto implements Serializable {
    private Integer id;
    private Date dateLogin;

    public static LoginDto fromLoginDto(Login loginDto) {
        LoginDto dto = new LoginDto();
        dto.setId(loginDto.getId());
        dto.setDateLogin(loginDto.getDateLogin());
        return dto;
    }
}