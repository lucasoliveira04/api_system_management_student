package com.system_management_student.system_management_student.modal.entity;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class AuthResponse {
    private DataUsersDto dataUsersDto;
    private String token;
}
