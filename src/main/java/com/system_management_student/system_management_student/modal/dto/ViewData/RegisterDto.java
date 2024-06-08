package com.system_management_student.system_management_student.modal.dto.ViewData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.system_management_student.system_management_student.modal.entity.Register;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.system_management_student.system_management_student.modal.entity.Register}
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDto implements Serializable {
    private Integer id;
    private String email;
    private String password;
    private Date dateRegister;

    public static RegisterDto fromEntity(Register register){
        return RegisterDto.builder()
                .id(register.getId())
                .email(register.getEmail())
                .password(register.getPassword())
                .dateRegister(register.getDateRegister())
                .build();
    }
}