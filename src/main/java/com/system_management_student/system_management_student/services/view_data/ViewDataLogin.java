package com.system_management_student.system_management_student.services.view_data;

import com.system_management_student.system_management_student.modal.dto.ViewData.LoginDto;
import com.system_management_student.system_management_student.modal.entity.Login;
import com.system_management_student.system_management_student.modal.repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewDataLogin {
    private final LoginRepository loginRepository;

    public ViewDataLogin(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public List<LoginDto> getAllDadsLogin(){
        return loginRepository.findAll().stream()
                .map(LoginDto::fromLoginDto)
                .collect(Collectors.toList());
    }
}
