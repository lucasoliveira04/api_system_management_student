package com.system_management_student.system_management_student.modal.dto.ViewData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.system_management_student.system_management_student.modal.entity.Login;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDto implements Serializable {
    private Long id;
    private String token;
    private LocalDateTime dateLogin;

    public static LoginDto fromEntity(Login login) {
        if (login == null) {
            return null;
        }

        return LoginDto.builder()
                .id(login.getId())
                .token(login.getToken())
                .dateLogin(login.getDateLogin())
                .build();
    }
}
