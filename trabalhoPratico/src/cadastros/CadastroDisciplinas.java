package cadastros;

import app.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class CadastroDisciplinas {
    int numDisciplinas;
    private List<Disciplina> disciplinas;

    public CadastroDisciplinas(){
        numDisciplinas = 0;
        disciplinas = new ArrayList<>();
    }

    public int cadastrarDisciplina(Disciplina disciplina) {
        boolean cadastrou = disciplinas.add(disciplina);
        if(cadastrou) {
            numDisciplinas = disciplinas.size();
        }
        return numDisciplinas;
    }

    public Disciplina pesquisarDisciplina(String codigoDisciplina) {
        for(Disciplina d: disciplinas) {
            if(d.getCodigoDisciplina().equalsIgnoreCase(codigoDisciplina)) {
                return d;
            }
        }
        return null;
    }

    public boolean removerDisciplina(Disciplina disciplina) {
        boolean removeu = false;
        if(disciplinas.contains(disciplina)) {
            removeu = disciplinas.remove(disciplina);
        }
        return removeu;
    }

    public boolean atualizarDisciplina(String codigoDisciplina, Disciplina disciplina) {
        boolean resposta = false;
        Disciplina remover = pesquisarDisciplina(codigoDisciplina);
        if(remover != null) {
            disciplinas.remove(remover);
            resposta = disciplinas.add(disciplina);
        }
        return resposta;
    }
}
