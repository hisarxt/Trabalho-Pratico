package exceptions;

public class TurmaNaoEncontradaException extends Exception{
	    String message = "Turma não encontrada - código inválido?";

	    public TurmaNaoEncontradaException() {
	    }

	    @Override
	    public String getMessage() {
	        return message;
	    }
	}
