package com.system_management_student.system_management_student.services.insert;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.modal.entity.DataUsers;
import com.system_management_student.system_management_student.modal.entity.Register;
import com.system_management_student.system_management_student.modal.repository.DataUsersRepository;
import com.system_management_student.system_management_student.modal.repository.RegisterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddStudentServices {
    private final DataUsersRepository dataUsersRepository;
    private final RegisterRepository registerRepository;

    public AddStudentServices(DataUsersRepository dataUsersRepository, RegisterRepository registerRepository) {
        this.dataUsersRepository = dataUsersRepository;
        this.registerRepository = registerRepository;
    }

    public void insertStudent(DataUsersDto dto) {
        DataUsers dataUsers = getDataUsers(dto);
        Register register = getRegister(dto);

        // Salvar o registro primeiro
        registerRepository.save(register);

        // Definir o registro para o usuário
        dataUsers.setRegister(register);

        // Relacionando user na FK
        register.setUser(dataUsers);

        // Salvar o usuário
        dataUsersRepository.save(dataUsers);
    }

    private DataUsers getDataUsers(DataUsersDto dto) {
        DataUsers dataUsers = new DataUsers();
        dataUsers.setName(dto.getName());
        dataUsers.setEmail(dto.getEmail());
        dataUsers.setRg(dto.getRg());
        dataUsers.setCpf(dto.getCpf());
        dataUsers.setDateOfBirth(dto.getDateOfBirth());
        return dataUsers;
    }

    private Register getRegister(DataUsersDto dto) {
        Register register = new Register();
        register.setEmail(dto.getEmail());
        register.setDateRegister(LocalDateTime.now());
        register.setPassword(dto.getRegisterDto().getPassword());
        return register;
    }
}
