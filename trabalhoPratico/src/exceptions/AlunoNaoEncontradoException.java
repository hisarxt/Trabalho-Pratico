package exceptions;

public class AlunoNaoEncontradoException extends Exception{
	String message = "Aluno não encontrado - matrícula inválida?";
	public AlunoNaoEncontradoException() {
	}
	@Override
	public String getMessage() {
		return message;
	}
}
