package com.system_management_student.system_management_student.services.auth;

import com.system_management_student.system_management_student.exception.CustomExceptions;
import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.modal.dto.ViewData.LoginDto;
import com.system_management_student.system_management_student.modal.entity.DataUsers;
import com.system_management_student.system_management_student.modal.entity.Login;
import com.system_management_student.system_management_student.modal.entity.Register;
import com.system_management_student.system_management_student.modal.repository.LoginRepository;
import com.system_management_student.system_management_student.modal.repository.RegisterRepository;
import com.system_management_student.system_management_student.services.jwt.JwtFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {
    private final LoginRepository loginRepository;
    private final RegisterRepository registerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtFilter jwtFilter;

    public AuthService(LoginRepository loginRepository, RegisterRepository registerRepository, BCryptPasswordEncoder passwordEncoder, JwtFilter jwtFilter) {
        this.loginRepository = loginRepository;
        this.registerRepository = registerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtFilter = jwtFilter;
    }

    public DataUsersDto getAuth(String username, String password) {
        try {
            Register register = registerRepository.findRegisterByUsername(username);

            if (register != null && passwordEncoder.matches(password, register.getPassword())) {
                DataUsers dataUsers = register.getUser();
                Login login = new Login();
                login.setDateLogin(LocalDateTime.now());
                String token = jwtFilter.generateToken(username);
                login.setToken(token);

                Date tokenExpirationDate = jwtFilter.getExpirationDateFromToken(token);
                login.setTokenExpirationDate(tokenExpirationDate);

                login.setId_user(dataUsers);
                loginRepository.save(login);



                DataUsersDto dataUsersDto = DataUsersDto.fromEntityLogin(dataUsers);
                dataUsersDto.setLastLogin(LoginDto.fromEntity(login));
                return dataUsersDto;
            }

        } catch (CustomExceptions.InvalidLoginException e) {
            e.getMessage();
        }

        return null;
    }

    public DataUsersDto getUserDataByUsername(String username) {
        Register register = registerRepository.findRegisterByUsername(username);
        if (register != null){
            return DataUsersDto.fromEntity(register.getUser());
        }
        return null;
    }
}