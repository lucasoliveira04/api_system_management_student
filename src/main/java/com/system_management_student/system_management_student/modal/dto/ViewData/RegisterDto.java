package com.system_management_student.system_management_student.modal.dto.ViewData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.system_management_student.system_management_student.modal.entity.Register;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDto implements Serializable {
    private Long id;
    private String email;
    private String username;
    private String password;
    private LocalDateTime dateRegister;
    private String newPassword;
    private int limitPassword;


    public static RegisterDto fromEntity(Register register){
        return RegisterDto.builder()
                .id(register.getId())
                .email(register.getEmail())
                .password(register.getPassword())
                .dateRegister(register.getDateRegister())
                .username(register.getUsername())
                .build();
    }

    public static RegisterDto fromEntityLogin(Register register){
        return RegisterDto.builder()
                .id(register.getId())
                .dateRegister(register.getDateRegister())
                .username(register.getUsername())
                .build();
    }
}
