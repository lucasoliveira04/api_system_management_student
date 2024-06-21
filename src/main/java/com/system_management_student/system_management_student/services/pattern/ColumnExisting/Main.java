package com.system_management_student.system_management_student.services.pattern.ColumnExisting;

import com.system_management_student.system_management_student.exception.CustomExceptions;
import com.system_management_student.system_management_student.modal.repository.DataUsersRepository;
import com.system_management_student.system_management_student.modal.repository.RegisterRepository;
import org.springframework.stereotype.Service;

@Service
public class Main {
    private final DataUsersRepository dataUsersRepository;

    public Main(DataUsersRepository dataUsersRepository) {
        this.dataUsersRepository = dataUsersRepository;
    }

    @Service
    public static class CpfExisting implements ColumnExistingStrategyinterface<String> {
        private final DataUsersRepository dataUsersRepository;

        public CpfExisting(DataUsersRepository dataUsersRepository) {
            this.dataUsersRepository = dataUsersRepository;
        }

        @Override
        public boolean checkExistingColumn(String column) {
            if (dataUsersRepository.existsByCpf(column)) {
                throw new CustomExceptions.DuplicateFieldException("CPF já existe: " + column);
            }
            return false;
        }
    }

    @Service
    public static class RgExisting implements ColumnExistingStrategyinterface<String> {
        private final DataUsersRepository dataUsersRepository;

        public RgExisting(DataUsersRepository dataUsersRepository) {
            this.dataUsersRepository = dataUsersRepository;
        }

        @Override
        public boolean checkExistingColumn(String column) {
            if (dataUsersRepository.existsByRg(column)) {
                throw new CustomExceptions.DuplicateFieldException("RG já existe: " + column);
            }
            return false;
        }
    }

    @Service
    public static class EmailExisting implements ColumnExistingStrategyinterface<String> {
        private final DataUsersRepository dataUsersRepository;

        public EmailExisting(DataUsersRepository dataUsersRepository) {
            this.dataUsersRepository = dataUsersRepository;
        }

        @Override
        public boolean checkExistingColumn(String column) {
            if (dataUsersRepository.existsByEmail(column)) {
                throw new CustomExceptions.DuplicateFieldException("Email já existe: " + column);
            }
            return false;
        }
    }

    @Service
    public static class UsernameExisting implements ColumnExistingStrategyinterface<String> {

        private final RegisterRepository registerRepository;

        public UsernameExisting(RegisterRepository registerRepository) {
            this.registerRepository = registerRepository;
        }

        @Override
        public boolean checkExistingColumn(String column) {
            if (registerRepository.existsByUsername(column)){
                throw new CustomExceptions.DuplicateFieldException("Username já existe: " + column);
            }
            return false;
        }
    }
}

interface ColumnExistingStrategyinterface<T> {
    boolean checkExistingColumn(T column);
}
