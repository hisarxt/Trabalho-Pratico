package exceptions;

public class ProfessorNaoAtribuidoException extends Exception {

    String message = "Professor não encontrado - Matrícula UFB inválida?\n\nTurmas não podem ser " +
            "cadastradas sem um professor atribuído";

    public ProfessorNaoAtribuidoException() {
    }

    @Override
    public String getMessage() {
        return message;
    }

}
