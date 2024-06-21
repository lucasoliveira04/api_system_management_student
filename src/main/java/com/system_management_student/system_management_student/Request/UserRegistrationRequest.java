package com.system_management_student.system_management_student.Request;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.modal.dto.ViewData.RegisterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UserRegistrationRequest {
    private DataUsersDto dataUsersDto;
    private RegisterDto registerDto;


}
