package com.system_management_student.system_management_student.modal.dto.ViewData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.system_management_student.system_management_student.modal.entity.DataStudents;
import com.system_management_student.system_management_student.modal.entity.DataUsers;
import com.system_management_student.system_management_student.modal.entity.Login;
import com.system_management_student.system_management_student.modal.entity.Register;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.system_management_student.system_management_student.modal.entity.DataUsers}
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataUsersDto implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cpf;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date dateOfBirth;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DataStudentsDto> dataStudentsDto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<LoginDto> loginDtos;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RegisterDto registerDto;

    public static DataUsersDto fromEntity(DataUsers dataUsers){
        DataUsersDto dto = new DataUsersDto();
        dto.setId(dataUsers.getId());
        dto.setName(dataUsers.getName());
        dto.setEmail(dataUsers.getEmail());
        dto.setRg(dataUsers.getRg());
        dto.setCpf(dataUsers.getCpf());
        dto.setDateOfBirth(dataUsers.getDateOfBirth());

        if (dataUsers.getRegister() != null){
            Register register = dataUsers.getRegister();
            dto.setRegisterDto(RegisterDto.fromEntity(register));
        }

        if (dataUsers.getStudents() != null) {
            List<DataStudentsDto> dataStudentsDtos = dataUsers.getStudents().stream()
                    .map(DataStudentsDto::fromEntity)
                    .collect(Collectors.toList());
            dto.setDataStudentsDto(dataStudentsDtos);
        }

        if(dataUsers.getLogins() != null) {
            List<LoginDto> loginDtos = dataUsers.getLogins().stream()
                    .map(LoginDto::fromLoginDto)
                    .collect(Collectors.toList());
            dto.setLoginDtos(loginDtos);
        }
        return dto;
    }
}