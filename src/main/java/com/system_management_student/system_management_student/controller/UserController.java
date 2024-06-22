package com.system_management_student.system_management_student.controller;

import com.system_management_student.system_management_student.Request.UserRegistrationRequest;
import com.system_management_student.system_management_student.services.insert.AddUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= "*", allowedHeaders= "*")
@RestController
@RequestMapping("/api/")
public class UserController {
    private final AddUserService addUserService;

    public UserController(AddUserService addUserService) {
        this.addUserService = addUserService;
    }


    @PostMapping("/add/user")
    public ResponseEntity<?> addUser(@RequestBody UserRegistrationRequest request){
        return addUserService.insertUser(request.getDataUsersDto(), request.getRegisterDto());
    }
}
