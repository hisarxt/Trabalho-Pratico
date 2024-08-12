package exceptions;

public class PesquisaEmBrancoException extends Exception{
    String message = "O campo não pode estar vazio!";
    public PesquisaEmBrancoException() {
    }
    @Override
    public String getMessage() {
        return message;
    }
}
