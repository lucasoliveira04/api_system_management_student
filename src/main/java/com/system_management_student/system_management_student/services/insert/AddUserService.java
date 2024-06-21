package com.system_management_student.system_management_student.services.insert;

import com.system_management_student.system_management_student.exception.CustomExceptions;
import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.modal.dto.ViewData.RegisterDto;
import com.system_management_student.system_management_student.modal.entity.DataUsers;
import com.system_management_student.system_management_student.modal.entity.Register;
import com.system_management_student.system_management_student.modal.repository.DataUsersRepository;
import com.system_management_student.system_management_student.modal.repository.RegisterRepository;
import com.system_management_student.system_management_student.services.pattern.ColumnExisting.Main;
import com.system_management_student.system_management_student.services.pattern.ID.GeneratorId;
import com.system_management_student.system_management_student.services.pattern.Regex.MainRegex;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddUserService {
    private final DataUsersRepository dataUsersRepository;
    private final RegisterRepository registerRepository;
    private final Main.CpfExisting cpfExisting;
    private final Main.EmailExisting emailExisting;
    private final Main.RgExisting rgExisting;
    private final Main.UsernameExisting usernameExisting;
    private final MainRegex mainRegex;
    private final BCryptPasswordEncoder passwordEncoder;

    public AddUserService(DataUsersRepository dataUsersRepository, RegisterRepository registerRepository, Main.CpfExisting cpfExisting, Main.EmailExisting emailExisting, Main.RgExisting rgExisting, Main.UsernameExisting usernameExisting, MainRegex mainRegex, BCryptPasswordEncoder passwordEncoder) {
        this.dataUsersRepository = dataUsersRepository;
        this.registerRepository = registerRepository;
        this.cpfExisting = cpfExisting;
        this.emailExisting = emailExisting;
        this.rgExisting = rgExisting;
        this.usernameExisting = usernameExisting;
        this.mainRegex = mainRegex;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> insertUser(DataUsersDto dataUsersDto, RegisterDto registerDto) {
        try {
            verificFieldExisitng(dataUsersDto, registerDto);
            DataUsers dataUsers = getDataUser(dataUsersDto);
            dataUsersRepository.save(dataUsers);
            Register register = getRegister(registerDto, dataUsers);
            registerRepository.save(register);
            return ResponseEntity.ok().body("Usuario registrado com sucesso");
        } catch (CustomExceptions.DuplicateFieldException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private DataUsers getDataUser(DataUsersDto dto) {
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

    private Register getRegister(RegisterDto registerDto, DataUsers dataUsers) {
        Register register = new Register();
        GeneratorId geratorId = new GeneratorId();
        register.setId(Long.valueOf(geratorId.MainGenerator("student")));
        register.setUsername(registerDto.getUsername());
        register.setEmail(registerDto.getEmail());

        String passwordBcrypt = passwordEncoder.encode(registerDto.getPassword());
        register.setPassword(passwordBcrypt);

        register.setDateRegister(LocalDateTime.now());
        register.setUser(dataUsers);
        return register;
    }

    private void verificFieldExisitng(DataUsersDto dataUsersDto, RegisterDto registerDto) {
        cpfExisting.checkExistingColumn(dataUsersDto.getCpf());
        rgExisting.checkExistingColumn(dataUsersDto.getRg());
        emailExisting.checkExistingColumn(dataUsersDto.getEmail());
        usernameExisting.checkExistingColumn(registerDto.getUsername());
    }

    private String formattedValidatedCpf(String cpf) {
        MainRegex.mainApplicationRegex cpfRegex = new MainRegex.mainApplicationRegex();
        cpfRegex.apply("cpf", cpf);
        return cpfRegex.getValue();
    }

    private String formattedValidatedRg(String rg) {
        MainRegex.mainApplicationRegex rgRegex = new MainRegex.mainApplicationRegex();
        rgRegex.apply("rg", rg);
        return rgRegex.getValue();
    }

    private String formattedValidateName(String name){
        MainRegex.mainApplicationRegex nameRegex = new MainRegex.mainApplicationRegex();
        nameRegex.apply("name", name);
        return nameRegex.getValue();
    }

    private String formattedValueValidateEmail(String email){
        MainRegex.mainApplicationRegex emailRegex = new MainRegex.mainApplicationRegex();
        emailRegex.apply("email", email);
        return emailRegex.getValue();
    }
}
