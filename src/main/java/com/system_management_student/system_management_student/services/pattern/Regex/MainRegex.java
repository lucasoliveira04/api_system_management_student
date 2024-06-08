package com.system_management_student.system_management_student.services.pattern.Regex;

import com.system_management_student.system_management_student.exception.CustomExceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainRegex {
    public static void main(String[] args) {
        RegexName regexName = new RegexName();
        System.out.println(regexName.applyregex("lucas", null, "Nome"));

        RegexCpf regexCpf = new RegexCpf();
        String cpf = "123.123.12323";
        System.out.println(regexCpf.applyregex(cpf, null, "Cpf"));
    }

    static class RegexCpf implements RegexApplInterface<String>{

        @Override
        public String applyregex(String input, String regex, String field) {
            field = "Cpf";
            regex = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}";

            if (input == null || input.length() != 14){
                throw new CustomExceptions.InvalidFieldException(field + " Inválido: " + input);
            }

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()){
                return input;
            }

            throw new CustomExceptions.InvalidFieldException(field + " Inválido: " + input);
        }
    }

    static class RegexName implements RegexApplInterface<String>{

        @Override
        public String applyregex(String input, String regex, String field) {
            field = "Nome";
            regex = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?: [A-Za-zÀ-ÖØ-öø-ÿ]+)*$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()){
                return input;
            }

            throw new CustomExceptions.InvalidFieldException(field + " Inválido: " + input);
        }
    }
}





interface RegexApplInterface<T>{
    T applyregex(T input, String regex, String field);
}