package exceptions;

public class CampoEmBrancoException extends Exception {
    String message = "Nenhum campo pode estar vazio!";

    public CampoEmBrancoException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
