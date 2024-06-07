package com.system_management_student.system_management_student.services.pattern.ID;

import java.util.Random;

/**
 * Classe principal para geração de IDs.
 * Utiliza o padrão Strategy para delegar a geração de IDs a diferentes implementações.
 */
public class GeneratorId {

    /**
     * Gera um ID baseado no tipo especificado.
     *
     * @param type Tipo de entidade (student ou teacher).
     * @return ID gerado ou mensagem de erro.
     */
    public String MainGenerator(String type) {
        if (type == null) {
            return Messages.ERROR_NULL_ID;
        }

        type = type.toLowerCase();

        IGeneratorId generator;

        if (type.equals("student")) {
            generator = new TypesIdGenerator.IdStudent();
        } else if (type.equals("teacher")) {
            generator = new TypesIdGenerator.IdTeacher();
        } else {
            return Messages.ERROR_INVALID_TYPE;
        }

        return generator.generator(type);
    }
}

/**
 * Contém as implementações específicas de geradores de ID para diferentes tipos de entidade.
 */
class TypesIdGenerator {

    /**
     * Gerador de ID específico para estudantes.
     */
    static class IdStudent implements IGeneratorId {

        /**
         * Gera um ID para um estudante.
         *
         * @param type Tipo de entidade (deve ser "student").
         * @return ID gerado ou mensagem de erro.
         */
        @Override
        public String generator(String type) {
            Generator gerId = new Generator();
            String verificType = "student";

            if (verificType.equals(type)) {
                return gerId.randomIdStudent();
            }
            return Messages.ERROR_INVALID_TYPE_STUDENT;
        }
    }

    /**
     * Gerador de ID específico para professores.
     */
    static class IdTeacher implements IGeneratorId {

        /**
         * Gera um ID para um professor.
         *
         * @param type Tipo de entidade (deve ser "teacher").
         * @return ID gerado ou mensagem de erro.
         */
        @Override
        public String generator(String type) {
            Generator gerId = new Generator();
            String verificType = "teacher";

            if (verificType.equals(type)) {
                return gerId.randomIdTeacher();
            }
            return Messages.ERROR_INVALID_TYPE_TEACHER;
        }
    }
}

/**
 * Classe utilitária para geração de IDs aleatórios.
 */
class Generator {

    /**
     * Gera um ID aleatório para um estudante.
     *
     * @return ID de 8 dígitos.
     */
    public String randomIdStudent() {
        Random rand = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int digit = rand.nextInt(9) + 1;
            stringBuilder.append(digit);
        }

        return stringBuilder.toString();
    }

    /**
     * Gera um ID aleatório para um professor.
     *
     * @return ID de 12 dígitos.
     */
    public String randomIdTeacher() {
        Random rand = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            int digit = rand.nextInt(9) + 1;
            stringBuilder.append(digit);
        }

        return stringBuilder.toString();
    }
}

class Messages {
    public static final String ERROR_NULL_ID = "Erro. Id não pode ser null";
    public static final String ERROR_INVALID_TYPE = "Erro. Tipo inválido";
    public static final String ERROR_INVALID_TYPE_STUDENT = "Erro. Tipo inválido para student";
    public static final String ERROR_INVALID_TYPE_TEACHER = "Erro. Tipo inválido para teacher";
}

interface IGeneratorId {
    String generator(String type);
}
