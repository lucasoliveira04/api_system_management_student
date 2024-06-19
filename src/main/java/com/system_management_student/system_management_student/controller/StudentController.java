package com.system_management_student.system_management_student.controller;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataStudentsDto;
import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.services.insert.AddNotaStudent;
import com.system_management_student.system_management_student.services.insert.AddStudentServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class StudentController {
    private final AddStudentServices addStudentServices;
    private final AddNotaStudent addNotaStudent;

    public StudentController(AddStudentServices addStudentServices, AddNotaStudent addNotaStudent) {
        this.addStudentServices = addStudentServices;
        this.addNotaStudent = addNotaStudent;
    }

    @PostMapping("/add/student")
    public ResponseEntity<String> addStudent(@RequestBody DataUsersDto dto){
        try{
            addStudentServices.insertStudent(dto);
            return ResponseEntity.ok().body("Student added successfully");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("An error occured while adding student");
        }
    }

    @PostMapping("/add/student/nota")
    public ResponseEntity<String> addNota(@RequestBody DataStudentsDto dto){
        try{
            addNotaStudent.insertNotaStudent(dto);
            return ResponseEntity.ok().body("Nota added successfully");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("An error occured while adding student");
        }
    }
}
