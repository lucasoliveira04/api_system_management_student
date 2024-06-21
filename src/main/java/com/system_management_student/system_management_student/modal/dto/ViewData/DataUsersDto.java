package com.system_management_student.system_management_student.modal.dto.ViewData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.system_management_student.system_management_student.modal.entity.DataUsers;
import com.system_management_student.system_management_student.modal.entity.Login;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.system_management_student.system_management_student.modal.entity.DataUsers}
 */
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

    public static DataUsersDto fromEntity(DataUsers dataUsers) {
        if (dataUsers == null) {
            return null;
        }
        return DataUsersDto.builder()
                .id(dataUsers.getId())
                .name(dataUsers.getName())
                .email(dataUsers.getEmail())
                .rg(dataUsers.getRg())
                .cpf(dataUsers.getCpf())
                .dateOfBirth(dataUsers.getDateOfBirth())
                .registerDto(dataUsers.getRegister() != null ?
                        RegisterDto.fromEntity(dataUsers.getRegister()) : null)
                .dataStudentsDto(dataUsers.getStudents() != null ?
                        dataUsers.getStudents()
                                .stream()
                                .map(DataStudentsDto::fromEntity)
                                .collect(Collectors.toList()) : null)
                .loginDto(dataUsers.getLogins() != null ?
                        dataUsers.getLogins()
                                .stream()
                                .map(LoginDto::fromLoginDto)
                                .collect(Collectors.toList()) : null)
                .build();
    }
}
