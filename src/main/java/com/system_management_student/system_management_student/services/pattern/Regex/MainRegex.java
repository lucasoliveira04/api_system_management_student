package com.system_management_student.system_management_student.services.pattern.Regex;

import org.springframework.stereotype.Service;

@Service
public class MainRegex {

    public static class mainApplicationRegex {
        public <T> T apply(T column, String value) {
            if (column.equals("cpf")) {
                CpfRegex cpfRegex = new CpfRegex();
                String formattedValue = cpfRegex.formatCpf(value);
                if (cpfRegex.applyRegex(formattedValue)) {
                    return (T) formattedValue;
                } else {
                    throw new IllegalArgumentException("Cpf inválido");
                }
            } else if (column.equals("rg")) {
                RgxRegex rgRegex = new RgxRegex();
                String formattedValue = rgRegex.formatRg(value);
                if (rgRegex.applyRegex(formattedValue)) {
                    return (T) formattedValue;
                } else {
                    throw new IllegalArgumentException("Rg inválido");
                }
            } else if (column.equals("name")) {
                NameRegex nameRegex = new NameRegex();
                if (nameRegex.applyRegex(value)) {
                    return (T) value;
                } else {
                    throw new IllegalArgumentException("Nome inválido");
                }
            } else if (column.equals("email")) {
                EmailRegex emailRegex = new EmailRegex();
                if (emailRegex.applyRegex(value)) {
                    return (T) value;
                } else {
                    throw new IllegalArgumentException("Email inválido");
                }
            }
            return column;
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
}

interface RegexColumnInterface<T> {
    boolean applyRegex(String column);
}
