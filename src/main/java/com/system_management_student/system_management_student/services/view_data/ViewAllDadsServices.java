package com.system_management_student.system_management_student.services.view_data;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.modal.repository.DataStudentsRepository;
import com.system_management_student.system_management_student.modal.repository.DataUsersRepository;
import com.system_management_student.system_management_student.modal.repository.LoginRepository;
import com.system_management_student.system_management_student.modal.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewAllDadsServices {

    private final DataUsersRepository dataUsersRepository;
    private final DataStudentsRepository dataStudentsRepository;
    private final LoginRepository loginRepository;
    private final RegisterRepository registerRepository;

    @Autowired
    public ViewAllDadsServices(DataUsersRepository dataUsersRepository, DataStudentsRepository dataStudentsRepository, LoginRepository loginRepository, RegisterRepository registerRepository) {
        this.dataUsersRepository = dataUsersRepository;
        this.dataStudentsRepository = dataStudentsRepository;
        this.loginRepository = loginRepository;
        this.registerRepository = registerRepository;
    }

    public List<DataUsersDto> getAllDadsUser(){
        return dataUsersRepository.findAll().stream()
                .map(DataUsersDto::fromEntity)
                .collect(Collectors.toList());
    }
}
