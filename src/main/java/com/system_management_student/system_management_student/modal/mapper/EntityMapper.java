package com.system_management_student.system_management_student.modal.mapper;


import com.system_management_student.system_management_student.modal.dto.ViewData.DataStudentsDto;
import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.modal.dto.ViewData.LoginDto;
import com.system_management_student.system_management_student.modal.dto.ViewData.RegisterDto;
import com.system_management_student.system_management_student.modal.entity.DataStudents;
import com.system_management_student.system_management_student.modal.entity.DataUsers;
import com.system_management_student.system_management_student.modal.entity.Login;
import com.system_management_student.system_management_student.modal.entity.Register;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);
    DataUsersDto toDataUsersDto(DataUsers dataUsers);
    LoginDto toLoginDto(Login login);
    DataStudentsDto toDataStudentsDto(DataStudents dataStudents);
    RegisterDto toRegisterDto(Register register);
}
