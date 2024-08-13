package app;

import java.util.ArrayList;

public class Disciplina {
    String nome;
    String codigoDisciplina;

    @Override
    public String toString() {
        return "Disciplina: " + "\n" +
                "Nome = '" + nome + '\'' + "\n" +
                "Código da Disciplina = '" +  codigoDisciplina + '\'';
    }

    public Disciplina(String nome, String codigoDisciplina) {
        this.nome = nome;
        this.codigoDisciplina = codigoDisciplina;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

}
