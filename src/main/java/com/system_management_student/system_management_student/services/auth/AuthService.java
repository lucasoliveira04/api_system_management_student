package com.system_management_student.system_management_student.services.auth;

import com.system_management_student.system_management_student.exception.CustomExceptions;
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

    public LoginDto getAuth(String username, String password) {
        try {
            Register register = registerRepository.findRegisterByUsername(username);

            if (register != null && passwordEncoder.matches(password, register.getPassword())) {
                DataUsers dataUsers = register.getUser();
                Login login = new Login();
                login.setDateLogin(LocalDateTime.now());
                String token = jwtFilter.generateToken(username);
                login.setToken(token);
                login.setId_user(dataUsers);
                loginRepository.save(login);
                return LoginDto.fromLoginDto(login);
            }

        } catch (CustomExceptions.InvalidLoginException e) {
            e.getMessage();
        }

        return null;
    }
}

