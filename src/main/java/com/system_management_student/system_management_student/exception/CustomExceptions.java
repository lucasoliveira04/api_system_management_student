package com.system_management_student.system_management_student.exception;

public class CustomExceptions {

    public static class InvalidFieldException extends RuntimeException {
        public InvalidFieldException(String message) {
            super(message);
        }
    }

    public static class DuplicateFieldException  extends RuntimeException {
        public DuplicateFieldException(String message) {
            super(message);
        }
    }

    public static class InvalidLoginException extends RuntimeException {
        public InvalidLoginException(String message) {}
    }
}
