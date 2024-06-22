package com.system_management_student.system_management_student.controller;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.modal.dto.ViewData.LoginDto;
import com.system_management_student.system_management_student.modal.entity.Register;
import com.system_management_student.system_management_student.services.auth.AuthService;
import com.system_management_student.system_management_student.services.recuperar_senha.RecuperarSenhaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= "*", allowedHeaders= "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final RecuperarSenhaService recuperarSenhaService;

    public AuthController(AuthService authService, RecuperarSenhaService recuperarSenhaService) {
        this.authService = authService;
        this.recuperarSenhaService = recuperarSenhaService;
    }

    @PostMapping("/")
    public ResponseEntity<?> login(@RequestBody Register request){
        String username = request.getUsername();
        String password = request.getPassword();

        LoginDto auth = authService.getAuth(username, password);

        if (auth != null){
            return ResponseEntity.ok().body(auth.getToken());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/recuperar-senha")
    public ResponseEntity<?> recuperarSenha(@RequestBody DataUsersDto request){
        ResponseEntity<String> response = recuperarSenhaService.recuperarSenha(request);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }


}
