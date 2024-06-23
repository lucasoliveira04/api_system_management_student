package com.system_management_student.system_management_student.services.recuperar_senha;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.modal.dto.ViewData.RegisterDto;
import com.system_management_student.system_management_student.modal.entity.DataUsers;
import com.system_management_student.system_management_student.modal.entity.Register;
import com.system_management_student.system_management_student.modal.repository.DataUsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RecuperarSenhaService {
    private final DataUsersRepository dataUsersRepository;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 12;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final int MAX_PASSWORD_CHANGES_PER_DAY = 3;

    public RecuperarSenhaService(DataUsersRepository dataUsersRepository, BCryptPasswordEncoder passwordEncoder) {
        this.dataUsersRepository = dataUsersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> recuperarSenha(DataUsersDto dto) {
        try {
            String newPassword = processarRecuperacaoSenha(dto);
            String messagePasswordRecuperada =
                    """
                    Sua senha foi alterada com sucesso.
                    Nova Senha : %s.
                    Não compartilhe com ninguém
                    """.formatted(newPassword);
            return ResponseEntity.ok().body(messagePasswordRecuperada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String processarRecuperacaoSenha(DataUsersDto dto) {
        Optional<DataUsers> usersOptional = dataUsersRepository.findByEmail(dto.getEmail());

        if (usersOptional.isPresent()) {
            DataUsers users = usersOptional.get();
            Register register = users.getRegister();

            String newPassword;
            if (dto.getRegisterDto() != null && dto.getRegisterDto().getNewPassword() != null){
                newPassword = dto.getRegisterDto().getNewPassword();
            } else {
                newPassword = generatePassword();
            }

            String encryptedPassword = passwordEncoder.encode(newPassword);
            register.setPassword(encryptedPassword);
            dataUsersRepository.save(users);
            return newPassword;

        } else {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
    }

    private String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }

        return password.toString();
    }
}
