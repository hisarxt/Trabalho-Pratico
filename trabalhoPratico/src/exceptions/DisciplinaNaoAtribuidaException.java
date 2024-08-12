package exceptions;

public class DisciplinaNaoAtribuidaException extends Exception{

    String message = "Disciplina não encontrada - código inválido?\n\nTurmas não podem ser " +
            "cadastradas sem uma disciplina atribuída";

    public DisciplinaNaoAtribuidaException() {
    }

    @Override
    public String getMessage() {
        return message;
    }

}
