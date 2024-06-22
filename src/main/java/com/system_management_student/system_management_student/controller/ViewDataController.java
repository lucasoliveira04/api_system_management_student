package com.system_management_student.system_management_student.controller;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.modal.dto.ViewData.LoginDto;
import com.system_management_student.system_management_student.services.view_data.ViewAllDadsServices;
import com.system_management_student.system_management_student.services.view_data.ViewDataLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins= "*", allowedHeaders= "*")
@RestController
@RequestMapping("view-all-data/")
public class ViewDataController {

    private final ViewAllDadsServices viewAllDadsServices;
    private final ViewDataLogin viewDataLogin;

    public ViewDataController(ViewAllDadsServices viewAllDadsServices, ViewDataLogin viewDataLogin) {
        this.viewAllDadsServices = viewAllDadsServices;
        this.viewDataLogin = viewDataLogin;
    }

    @GetMapping("/user")
    public List<DataUsersDto> getAllDataUser() {
        return viewAllDadsServices.getAllDadsUser();
    }

    @GetMapping("/auth/data")
    public List<LoginDto> getAllLoginData() {
        return viewDataLogin.getAllDadsLogin();
    }
}
