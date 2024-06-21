package com.system_management_student.system_management_student.modal.repository;

import com.system_management_student.system_management_student.modal.entity.DataUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataUsersRepository extends JpaRepository<DataUsers, Integer> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByRg(String rg);
    boolean existsByUsername(String username);
}
