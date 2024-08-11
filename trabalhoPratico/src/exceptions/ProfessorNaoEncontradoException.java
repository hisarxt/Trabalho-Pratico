package exceptions;

public class ProfessorNaoEncontradoException extends Exception{
	String message = "Professor não encontrado - matrícula FUB inválida?";
	public ProfessorNaoEncontradoException() {

	}
	@Override
	public String getMessage(){
		return message;
	}

}
