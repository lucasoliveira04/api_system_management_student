package com.system_management_student.system_management_student.modal.repository;

import com.system_management_student.system_management_student.modal.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register, Integer> {
}