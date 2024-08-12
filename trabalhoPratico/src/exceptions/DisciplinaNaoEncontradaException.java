package exceptions;

public class DisciplinaNaoEncontradaException extends Exception{
    String message = "Disciplina não encontrada - código inválido?";

    public DisciplinaNaoEncontradaException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
