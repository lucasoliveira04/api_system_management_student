package com.system_management_student.system_management_student.services.pattern.Regex;

import org.springframework.stereotype.Service;

@Service
public class MainRegex {

    public static class mainApplicationRegex {
        private String value;

        public <T> void apply(String type, T value) {
            switch (type) {
                case "cpf":
                    CpfRegex cpfRegex = new CpfRegex();
                    String formattedCpf = cpfRegex.formatCpf((String) value);
                    if (cpfRegex.applyRegex(formattedCpf)) {
                        this.value = formattedCpf;
                    } else {
                        throw new IllegalArgumentException("Cpf inválido");
                    }
                    break;
                case "rg":
                    RgxRegex rgRegex = new RgxRegex();
                    String formattedRg = rgRegex.formatRg((String) value);
                    if (rgRegex.applyRegex(formattedRg)) {
                        this.value = formattedRg;
                    } else {
                        throw new IllegalArgumentException("Rg inválido");
                    }
                    break;
                case "name":
                    NameRegex nameRegex = new NameRegex();
                    if (nameRegex.applyRegex((String) value)) {
                        this.value = (String) value;
                    } else {
                        throw new IllegalArgumentException("Nome inválido");
                    }
                    break;
                case "email":
                    EmailRegex emailRegex = new EmailRegex();
                    if (emailRegex.applyRegex((String) value)) {
                        this.value = (String) value;
                    } else {
                        throw new IllegalArgumentException("Email inválido");
                    }
                    break;
                case "nota":
                    NotaRegex notaRegex = new NotaRegex();
                    Double parsedNota = notaRegex.parseNota((String) value);
                    if (parsedNota != null && notaRegex.applyRegex(parsedNota)) {
                        this.value = parsedNota.toString();
                    } else {
                        throw new IllegalArgumentException("Nota inválida");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Tipo inválido");
            }
        }

        public String getValue() {
            return this.value;
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
            try {
                return Double.parseDouble(nota);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }
}

interface RegexColumnInterface<T> {
    boolean applyRegex(T column);
}
