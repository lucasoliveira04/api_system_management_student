package com.system_management_student.system_management_student.controller;

import com.system_management_student.system_management_student.modal.dto.ViewData.LoginDto;
import com.system_management_student.system_management_student.modal.entity.Login;
import com.system_management_student.system_management_student.modal.entity.Register;
import com.system_management_student.system_management_student.services.insert.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= "*", allowedHeaders= "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
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
}
