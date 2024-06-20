package com.system_management_student.system_management_student.controller;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataStudentsDto;
import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.services.insert.AddNotaStudent;
import com.system_management_student.system_management_student.services.insert.AddStudentServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= "*", allowedHeaders= "*")
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
    public ResponseEntity<?> addStudent(@RequestBody DataUsersDto dto){
       return addStudentServices.insertStudent(dto);
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
