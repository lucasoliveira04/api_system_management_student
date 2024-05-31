package com.system_management_student.system_management_student.modal.dto.ViewData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.system_management_student.system_management_student.modal.entity.Register;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.system_management_student.system_management_student.modal.entity.Register}
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDto implements Serializable {
    private Integer id;
    private String email;
    private String password;
    private Date dateRegister;

    public static RegisterDto fromEntity(Register register){
        RegisterDto dto = new RegisterDto();
        dto.setId(register.getId());
        dto.setEmail(register.getEmail());
        dto.setPassword(register.getPassword());
        dto.setDateRegister(register.getDateRegister());
        return dto;
    }
}