package com.system_management_student.system_management_student.services.insert;

import com.system_management_student.system_management_student.modal.dto.ViewData.DataStudentsDto;
import com.system_management_student.system_management_student.modal.entity.DataStudents;
import com.system_management_student.system_management_student.modal.entity.DataUsers;
import com.system_management_student.system_management_student.modal.repository.DataStudentsRepository;
import com.system_management_student.system_management_student.modal.repository.DataUsersRepository;
import com.system_management_student.system_management_student.services.pattern.ID.GeneratorId;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AddNotaStudent {
    private final DataUsersRepository dataUsersRepository;
    private final DataStudentsRepository dataStudentsRepository;

    public AddNotaStudent(DataUsersRepository dataUsersRepository, DataStudentsRepository dataStudentsRepository) {
        this.dataUsersRepository = dataUsersRepository;
        this.dataStudentsRepository = dataStudentsRepository;
    }

    public ResponseEntity<String> insertNotaStudent(DataStudentsDto dataStudentsDto) {
        try {
            int currentYear = LocalDateTime.now().getYear();
            verificBimestre(dataStudentsDto, currentYear);

            DataStudents students = getDataStudents(dataStudentsDto, currentYear);
            dataStudentsRepository.save(students);

            updateAnnualMeanResult(dataStudentsDto.getStudentId(), currentYear);

            return ResponseEntity.ok().body("Nota inserida com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private DataStudents getDataStudents(DataStudentsDto dto, int currentYear) {
        DataStudents students = new DataStudents();
        GeneratorId generatorId = new GeneratorId();
        students.setId(Long.valueOf(generatorId.MainGenerator("student")));
        students.setNota_1(validatedNota(dto.getNota_1()));
        students.setNota_2(validatedNota(dto.getNota_2()));
        students.setNota_3(validatedNota(dto.getNota_3()));
        students.setNota_4(validatedNota(dto.getNota_4()));
        students.setNota_5(validatedNota(dto.getNota_5()));
        students.setBimestre(dto.getBimestre());
        students.setAno(currentYear);

        double mean = calcularMeanNota(students);
        students.setMeanResult(String.valueOf(mean));
        students.setMean_result_final(mean);

        Optional<DataUsers> optionalDataUsers = dataUsersRepository.findById(Math.toIntExact(dto.getStudentId()));
        if (optionalDataUsers.isPresent()) {
            students.setStudent(optionalDataUsers.get());
        } else {
            throw new RuntimeException("Usuário não encontrado com o ID: " + dto.getStudentId());
        }

        students.setDate_insert_nota(LocalDateTime.now());
        return students;
    }

    private double validatedNota(double nota) {
        if (nota < 0 || nota > 10) {
            throw new RuntimeException("Nota deve ser entre 0 e 10");
        }
        return nota;
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

    private void updateAnnualMeanResult(long studentId, int currentYear) {
        List<DataStudents> allBimestres = dataStudentsRepository.findByStudentIdAndAno(studentId, currentYear);

        if (allBimestres.size() < 4) {
            return;
        }

        double sum = 0;
        for (DataStudents bimestre : allBimestres) {
            sum += Double.parseDouble(bimestre.getMeanResult());
        }

        double annualMean = sum / 4;

        String meanResultFinal = annualMean >= 7 ? "Aprovado" : "Reprovado";

        for (DataStudents bimestre : allBimestres) {
            bimestre.setMean_result_final(annualMean);
            bimestre.setMeanResult(meanResultFinal);
            dataStudentsRepository.save(bimestre);
        }
    }

    private double calcularMeanNota(DataStudents students) {
        double sum = students.getNota_1() + students.getNota_2() + students.getNota_3() + students.getNota_4() + students.getNota_5();
        return sum / 5;
    }
}
