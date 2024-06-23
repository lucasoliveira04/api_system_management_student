package com.system_management_student.system_management_student.modal.dto.ViewData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.system_management_student.system_management_student.modal.entity.DataUsers;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataUsersDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String rg;
    private String cpf;
    private String dateOfBirth;
    private RegisterDto registerDto;
    private List<DataStudentsDto> dataStudentsDto;
    private List<LoginDto> loginDto;
    private Integer loginCount;
    private LoginDto lastLogin;
    private String typeUser;

    public static DataUsersDto fromEntity(DataUsers dataUsers) {
        if (dataUsers == null) {
            return null;
        }

        List<LoginDto> loginDtos = dataUsers.getLogins() != null ?
                dataUsers.getLogins().stream()
                        .map(LoginDto::fromEntity)
                        .collect(Collectors.toList()) : null;
        LoginDto lastLoginDto = loginDtos != null && !loginDtos.isEmpty() ?
                loginDtos.get(loginDtos.size() - 1) : null;

        boolean isHashUsername = dataUsers.getRegister() != null && dataUsers.getRegister().getUsername() != null;

        String typeUser = isHashUsername ? "admin" : "student";

        return DataUsersDto.builder()
                .id(dataUsers.getId())
                .typeUser(typeUser)
                .name(dataUsers.getName())
                .email(dataUsers.getEmail())
                .rg(dataUsers.getRg())
                .cpf(dataUsers.getCpf())
                .dateOfBirth(dataUsers.getDateOfBirth())
                .registerDto(dataUsers.getRegister() != null ?
                        RegisterDto.fromEntity(dataUsers.getRegister()) : null)
                .dataStudentsDto(!isHashUsername && dataUsers.getStudents() != null ?
                        dataUsers.getStudents().stream()
                                .map(DataStudentsDto::fromEntity)
                                .collect(Collectors.toList()) : null)
                .loginDto(loginDtos)
                .loginCount(loginDtos != null ? loginDtos.size() : 0)
                .lastLogin(lastLoginDto)
                .build();
    }

    public static DataUsersDto fromEntityLogin(DataUsers dataUsers){
        if (dataUsers == null) {
            return null;
        }

        List<LoginDto> loginDtos = dataUsers.getLogins() != null ?
                dataUsers.getLogins().stream()
                        .map(LoginDto::fromEntity)
                        .collect(Collectors.toList()) : null;
        LoginDto lastLoginDto = loginDtos != null && !loginDtos.isEmpty() ?
                loginDtos.get(loginDtos.size() - 1) : null;

        boolean isHashUsername = dataUsers.getRegister() != null && dataUsers.getRegister().getUsername() != null;

        String typeUser = isHashUsername ? "admin" : "student";

        return DataUsersDto.builder()
                .id(dataUsers.getId())
                .typeUser(typeUser)
                .name(dataUsers.getName())
                .email(dataUsers.getEmail())
                .rg(dataUsers.getRg())
                .cpf(dataUsers.getCpf())
                .dateOfBirth(dataUsers.getDateOfBirth())
                .loginCount(loginDtos != null ? loginDtos.size() : 0)
                .lastLogin(lastLoginDto)
                .build();
    }
}
