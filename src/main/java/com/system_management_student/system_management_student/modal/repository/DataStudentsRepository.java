package com.system_management_student.system_management_student.modal.repository;

import com.system_management_student.system_management_student.modal.entity.DataStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataStudentsRepository extends JpaRepository<DataStudents, Long> {
    Optional<DataStudents> findByStudentIdAndBimestreAndAno(Long studentId, Integer bimestre, Integer ano);
}
