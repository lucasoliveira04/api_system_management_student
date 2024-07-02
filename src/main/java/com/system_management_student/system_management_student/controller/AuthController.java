package com.system_management_student.system_management_student.controller;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.modal.dto.ViewData.LoginDto;
import com.system_management_student.system_management_student.modal.entity.AuthResponse;
import com.system_management_student.system_management_student.modal.entity.DataUsers;
import com.system_management_student.system_management_student.modal.entity.Register;
import com.system_management_student.system_management_student.services.auth.AuthService;
import com.system_management_student.system_management_student.services.jwt.JwtFilter;
import com.system_management_student.system_management_student.services.recuperar_senha.RecuperarSenhaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= "*", allowedHeaders= "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final RecuperarSenhaService recuperarSenhaService;
    private final JwtFilter jwtFilter;

    public AuthController(AuthService authService, RecuperarSenhaService recuperarSenhaService, JwtFilter jwtFilter) {
        this.authService = authService;
        this.recuperarSenhaService = recuperarSenhaService;
        this.jwtFilter = jwtFilter;
    }

    @PostMapping("/")
    public ResponseEntity<DataUsersDto> login(@RequestBody Register request){
        DataUsersDto dataUsersDto = authService.getAuth(request.getUsername(), request.getPassword());

        if (dataUsersDto != null) {
            String token = jwtFilter.generateToken(request.getUsername());
            return ResponseEntity.ok(new AuthResponse(dataUsersDto, token).getDataUsersDto());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/recuperar-senha")
    public ResponseEntity<?> recuperarSenha(@RequestBody DataUsersDto request){
        ResponseEntity<String> response = recuperarSenhaService.recuperarSenha(request);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/user")
    public ResponseEntity<DataUsersDto> getUserData(@RequestParam String username){
        DataUsersDto usersDto = authService.getUserDataByUsername(username);

        if (usersDto != null) {
            return ResponseEntity.ok(usersDto);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
    @GetMapping("/verify-token")
    public ResponseEntity<?> verifyToken(HttpServletRequest request){
        try {
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            boolean isValid = jwtFilter.validateToken(token);

            if (isValid) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(401).body("Token invalido");
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }

    }
}
