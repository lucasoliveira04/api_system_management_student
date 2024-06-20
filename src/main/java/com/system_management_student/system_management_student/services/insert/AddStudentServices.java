package com.system_management_student.system_management_student.services.insert;

import com.system_management_student.system_management_student.exception.CustomExceptions;
import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.modal.entity.DataUsers;
import com.system_management_student.system_management_student.modal.entity.Register;
import com.system_management_student.system_management_student.modal.repository.DataUsersRepository;
import com.system_management_student.system_management_student.modal.repository.RegisterRepository;
import com.system_management_student.system_management_student.services.pattern.ColumnExisting.Main;
import com.system_management_student.system_management_student.services.pattern.ID.GeneratorId;
import com.system_management_student.system_management_student.services.pattern.Regex.MainRegex;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddStudentServices {
    private final DataUsersRepository dataUsersRepository;
    private final RegisterRepository registerRepository;
    private final Main.CpfExisting cpfExisting;
    private final Main.EmailExisting emailExisting;
    private final Main.RgExisting rgExisting;
    private final MainRegex mainRegex;

    public AddStudentServices(DataUsersRepository dataUsersRepository,
                              RegisterRepository registerRepository,
                              Main.CpfExisting cpfExisting, Main.EmailExisting emailExisting, Main.RgExisting rgExisting, MainRegex mainRegex) {
        this.dataUsersRepository = dataUsersRepository;
        this.registerRepository = registerRepository;
        this.cpfExisting = cpfExisting;
        this.emailExisting = emailExisting;
        this.rgExisting = rgExisting;
        this.mainRegex = mainRegex;
    }

    public ResponseEntity<String> insertStudent(DataUsersDto dto) {
        try {
            verificFieldExisitng(dto);
            DataUsers dataUsers = getDataUsers(dto); // Obtém os dados do usuário

            // Salva o usuário
            dataUsersRepository.save(dataUsers);

            // Usuario salvo, registro criado
            Register register = getRegister(dto);

            // Define o usuário associado ao registro
            register.setUser(dataUsers);

            // Salva o registro
            registerRepository.save(register);

            return ResponseEntity.ok().body("Estudante inserido com sucesso!");
        } catch (CustomExceptions.DuplicateFieldException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private DataUsers getDataUsers(DataUsersDto dto) {
        DataUsers dataUsers = new DataUsers();
        GeneratorId geratorId = new GeneratorId();
        dataUsers.setId(Long.valueOf(geratorId.MainGenerator("student")));
        dataUsers.setName(formattedValidateName(dto.getName()));
        dataUsers.setEmail(formattedValueValidateEmail(dto.getEmail()));
        dataUsers.setRg(formattedValidatedRg(dto.getRg()));
        dataUsers.setCpf(formattedValidatedCpf(dto.getCpf()));
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

    private void verificFieldExisitng(DataUsersDto dto) {
        cpfExisting.checkExistingColumn(dto.getCpf());
        rgExisting.checkExistingColumn(dto.getRg());
        emailExisting.checkExistingColumn(dto.getEmail());
    }

    private String formattedValidatedCpf(String cpf) {
        MainRegex.mainApplicationRegex cpfRegex = new MainRegex.mainApplicationRegex();
        cpfRegex.apply("cpf", cpf);
        return cpfRegex.toString();
    }
    private String formattedValidatedRg(String rg) {
        MainRegex.mainApplicationRegex rgRegex = new MainRegex.mainApplicationRegex();
        rgRegex.apply("rg", rg);
        return rgRegex.toString();
    }
    private String formattedValidateName(String name){
        MainRegex.mainApplicationRegex nameRegex = new MainRegex.mainApplicationRegex();
        nameRegex.apply("name", name);
        return nameRegex.toString();
    }
    private String formattedValueValidateEmail(String email){
        MainRegex.mainApplicationRegex emailRegex = new MainRegex.mainApplicationRegex();
        emailRegex.apply("email", email);
        return emailRegex.toString();
    }
}
