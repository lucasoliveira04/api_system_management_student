package com.system_management_student.system_management_student.modal.dto.ViewData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.system_management_student.system_management_student.modal.entity.Login;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.system_management_student.system_management_student.modal.entity.Login}
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDto implements Serializable {
    private Long id;
    private String token;
    private LocalDateTime dateLogin;
    private DataUsersDto dataUsersDto;


    public static LoginDto fromLoginDto(Login loginDto) {
        return LoginDto.builder()
                .id(loginDto.getId())
                .dateLogin(loginDto.getDateLogin())
                .token(loginDto.getToken())

                .build();
    }

}