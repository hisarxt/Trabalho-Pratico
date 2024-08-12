package exceptions;

import java.util.ArrayList;
import java.util.Collections;

public class CampoEmBrancoException extends Exception {
    String message;

    /*

    Códigos de exceção:

    1 -> Aluno
    2 -> Disciplina
    3 -> Professor
    4 -> Turma

    Vai ajudar na hora de construir a mensagem e especificar quais campos estão vazios.
    */
    public CampoEmBrancoException(int codigo, String[] vetor) {
        this.setMessage(gerarMensagem(codigo, vetor));
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String gerarMensagem(int codigo, String[] vetor) {
        String mensagem = "Nenhum campo pode estar vazio!" +
                "\n\n" +
                "Corrija os seguintes campos e tente novamente:" +
                "\n\n";

        ArrayList<String> nomes = new ArrayList<>();

        switch (codigo) {
            case 1:
                Collections.addAll(nomes, "Nome", "CPF", "E-Mail", "Matrícula", "Curso");
                break;
            case 2:
                Collections.addAll(nomes, "Nome", "Código da Disciplina");
                break;
            case 3:
                Collections.addAll(nomes, "Nome", "CPF", "E-Mail", "Matrícula FUB", "Área de Formação");
                break;
        }

        String nulos = "";

        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i].trim().isEmpty()) {
                nulos += nomes.get(i) + ";\n";
            }
        }

        mensagem += nulos;

        return mensagem;
    }
}
