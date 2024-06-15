package com.system_management_student.system_management_student.services.insert;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.modal.entity.DataUsers;
import com.system_management_student.system_management_student.modal.entity.Register;
import com.system_management_student.system_management_student.modal.repository.DataUsersRepository;
import com.system_management_student.system_management_student.modal.repository.RegisterRepository;
import com.system_management_student.system_management_student.services.pattern.ID.GeneratorId;
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
        DataUsers dataUsers = getDataUsers(dto); // Obtém os dados do usuário

        // Salva o usuário
        dataUsersRepository.save(dataUsers);

        // Usuario salvo, registro criado
        Register register = getRegister(dto);

        // Define o usuário associado ao registro
        register.setUser(dataUsers);

        // Salva o registro
        registerRepository.save(register);
    }

    private DataUsers getDataUsers(DataUsersDto dto) {
        DataUsers dataUsers = new DataUsers();
        GeneratorId geratorId = new GeneratorId();
        dataUsers.setId(Long.valueOf(geratorId.MainGenerator("student")));
        dataUsers.setName(dto.getName());
        dataUsers.setEmail(dto.getEmail());
        dataUsers.setRg(dto.getRg());
        dataUsers.setCpf(dto.getCpf());
        dataUsers.setDateOfBirth(dto.getDateOfBirth());
        return dataUsers;
    }

    private Register getRegister(DataUsersDto dto) {
        Register register = new Register();
        GeneratorId geratorId = new GeneratorId();
        register.setId(Long.valueOf(geratorId.MainGenerator("student")));
        register.setEmail(dto.getEmail());
        register.setDateRegister(LocalDateTime.now());

        String dateOfBirthWithouthyphes = dto.getDateOfBirth().replace("-", "");
        String nameWithoutSpaces = dto.getName().replace(" ", "");
        String password = dateOfBirthWithouthyphes + nameWithoutSpaces.toLowerCase();

        register.setPassword(password);
        return register;
    }
}