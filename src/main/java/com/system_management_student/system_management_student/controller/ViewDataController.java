package com.system_management_student.system_management_student.controller;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.services.view_data.ViewAllDadsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("view-all-data/")
public class ViewDataController {
    @Autowired
    private ViewAllDadsServices viewAllDadsServices;

    @GetMapping("/user")
    public List<DataUsersDto> getAllDataUser() {
        return viewAllDadsServices.getAllDadsUser();
    }
}
