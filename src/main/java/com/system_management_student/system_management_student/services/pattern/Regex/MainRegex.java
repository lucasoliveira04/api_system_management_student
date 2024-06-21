package com.system_management_student.system_management_student.services.pattern.Regex;

import org.springframework.stereotype.Service;

@Service
public class MainRegex {

    public static class mainApplicationRegex {
        public <T> T apply(T column, T value) {
            switch (column.toString()) {
                case "cpf":
                    CpfRegex cpfRegex = new CpfRegex();
                    String formattedCpf = cpfRegex.formatCpf((String) value);
                    if (cpfRegex.applyRegex(formattedCpf)) {
                        return (T) formattedCpf;
                    } else {
                        throw new IllegalArgumentException("Cpf inválido");
                    }
                case "rg":
                    RgxRegex rgRegex = new RgxRegex();
                    String formattedRg = rgRegex.formatRg((String) value);
                    if (rgRegex.applyRegex(formattedRg)) {
                        return (T) formattedRg;
                    } else {
                        throw new IllegalArgumentException("Rg inválido");
                    }
                case "name":
                    NameRegex nameRegex = new NameRegex();
                    if (nameRegex.applyRegex((String) value)) {
                        return (T) value;
                    } else {
                        throw new IllegalArgumentException("Nome inválido");
                    }
                case "email":
                    EmailRegex emailRegex = new EmailRegex();
                    if (emailRegex.applyRegex((String) value)) {
                        return (T) value;
                    } else {
                        throw new IllegalArgumentException("Email inválido");
                    }
                case "nota":
                    NotaRegex notaRegex = new NotaRegex();
                    Double parseNota = notaRegex.parseNota((String) value);
                    if (parseNota != null && notaRegex.applyRegex(parseNota)){
                        return (T) parseNota;
                    } else {
                        throw new IllegalArgumentException("Nota invalida");
                    }
                default:
                    return column;
            }
        }
    }

    static class CpfRegex implements RegexColumnInterface<String> {

        @Override
        public boolean applyRegex(String column) {
            String patternCpf = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
            return column.matches(patternCpf);
        }

        public String formatCpf(String cpf) {
            if (cpf.length() == 11) {
                return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
            }
            return cpf;
        }
    }

    static class RgxRegex implements RegexColumnInterface<String> {
        @Override
        public boolean applyRegex(String column) {
            String patternRg = "\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}";
            return column.matches(patternRg);
        }

        public String formatRg(String rg) {
            if (rg.length() >= 7 && rg.length() <= 9) {
                return rg.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d?)", "$1.$2.$3-$4");
            }
            return rg;
        }
    }

    static class NameRegex implements RegexColumnInterface<String> {

        @Override
        public boolean applyRegex(String column) {
            String patternName = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$";
            return column.matches(patternName);
        }

    }

    static class EmailRegex implements RegexColumnInterface<String> {

        @Override
        public boolean applyRegex(String column) {
            String patternEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            return column.matches(patternEmail);
        }
    }

    static class NotaRegex implements RegexColumnInterface<Double> {

        @Override
        public boolean applyRegex(Double column) {
            return column >= 0 && column <= 10;
        }

        public Double parseNota(String nota) {
            try{
                return Double.parseDouble(nota);
            } catch (NumberFormatException e){
                return null;
            }
        }
    }
}

interface RegexColumnInterface<T> {
    boolean applyRegex(T column);
}
