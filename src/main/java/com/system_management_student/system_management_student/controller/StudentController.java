package com.system_management_student.system_management_student.controller;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataStudentsDto;
import com.system_management_student.system_management_student.modal.dto.ViewData.DataUsersDto;
import com.system_management_student.system_management_student.services.insert.AddNotaStudent;
import com.system_management_student.system_management_student.services.insert.AddStudentServices;
import com.system_management_student.system_management_student.services.view_data.ViewAllDadsServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins= "*", allowedHeaders= "*")
@RestController
@RequestMapping("/api/")
public class StudentController {
    private final AddStudentServices addStudentServices;
    private final AddNotaStudent addNotaStudent;
    private final ViewAllDadsServices viewAllDadsServices;

    public StudentController(AddStudentServices addStudentServices, AddNotaStudent addNotaStudent, ViewAllDadsServices viewAllDadsServices) {
        this.addStudentServices = addStudentServices;
        this.addNotaStudent = addNotaStudent;
        this.viewAllDadsServices = viewAllDadsServices;
    }

    @PostMapping("/add/student")
    public ResponseEntity<?> addStudent(@RequestBody DataUsersDto dto){
       return addStudentServices.insertStudent(dto);
    }

    @PostMapping("/add/student/nota")
    public ResponseEntity<?> addNota(@RequestBody DataStudentsDto dto){
        return addNotaStudent.insertNotaStudent(dto);
    }

    @GetMapping("/historico-escolar/{id}")
    public ResponseEntity<?> getHistoricoEscolarById(@PathVariable Integer id) {
        Optional<DataUsersDto> dataUsersDto = viewAllDadsServices.getHistoricoEscolarById(id);
        if (dataUsersDto.isPresent()) {
            return ResponseEntity.ok(dataUsersDto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
    }
}
