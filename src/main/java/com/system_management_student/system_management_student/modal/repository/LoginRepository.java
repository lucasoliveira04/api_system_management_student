package com.system_management_student.system_management_student.modal.repository;

import com.system_management_student.system_management_student.modal.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Integer> {
}