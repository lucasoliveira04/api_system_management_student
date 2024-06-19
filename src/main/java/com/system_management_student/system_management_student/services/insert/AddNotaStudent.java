package com.system_management_student.system_management_student.services.insert;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataStudentsDto;
import com.system_management_student.system_management_student.modal.entity.DataStudents;
import com.system_management_student.system_management_student.modal.entity.DataUsers;
import com.system_management_student.system_management_student.modal.repository.DataStudentsRepository;
import com.system_management_student.system_management_student.modal.repository.DataUsersRepository;
import com.system_management_student.system_management_student.services.pattern.ID.GeneratorId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AddNotaStudent {
    private final DataUsersRepository dataUsersRepository;
    private final DataStudentsRepository dataStudentsRepository;

    public AddNotaStudent(DataUsersRepository dataUsersRepository, DataStudentsRepository dataStudentsRepository) {
        this.dataUsersRepository = dataUsersRepository;
        this.dataStudentsRepository = dataStudentsRepository;
    }

    public void insertNotaStudent(DataStudentsDto dataStudentsDto) {
        int currentYear = LocalDateTime.now().getYear();
        verificBimestre(dataStudentsDto, currentYear);

        DataStudents students = getDataStudents(dataStudentsDto, currentYear);
        dataStudentsRepository.save(students);
    }

    private DataStudents getDataStudents(DataStudentsDto dto, int currentYear) {
        DataStudents students = new DataStudents();
        GeneratorId generatorId = new GeneratorId();
        students.setId(Long.valueOf(generatorId.MainGenerator("student")));
        students.setNota_1(dto.getNota_1());
        students.setNota_2(dto.getNota_2());
        students.setNota_3(dto.getNota_3());
        students.setNota_4(dto.getNota_4());
        students.setNota_5(dto.getNota_5());
        students.setBimestre(dto.getBimestre());
        students.setAno(currentYear);

        double mean = calcularMeanNota(students);
        students.setMean_result_final(mean);

        if (students.getMean_result_final() <= 7) {
            students.setResult("Reprovado");
        } else {
            students.setResult("Aprovado");
        }

        Optional<DataUsers> optionalDataUsers = dataUsersRepository.findById(Math.toIntExact(dto.getStudentId()));
        if (optionalDataUsers.isPresent()) {
            students.setStudent(optionalDataUsers.get());
        } else {
            throw new RuntimeException("Usuário não encontrado com o ID: " + dto.getStudentId());
        }

        students.setDate_insert_nota(LocalDateTime.now());
        return students;
    }

    private void verificBimestre(DataStudentsDto dataStudentsDto, int currentYear) {
        Optional<DataStudents> existingRecord = dataStudentsRepository.findByStudentIdAndBimestreAndAno(
                dataStudentsDto.getStudentId(),
                dataStudentsDto.getBimestre(),
                currentYear
        );

        if (existingRecord.isPresent()) {
            throw new RuntimeException("O aluno já possui notas registradas para este bimestre e ano.");
        }
    }

    private double calcularMeanNota(DataStudents students) {
        double sum = students.getNota_1() + students.getNota_2() + students.getNota_3() + students.getNota_4() + students.getNota_5();
        return sum / 5;
    }
}
