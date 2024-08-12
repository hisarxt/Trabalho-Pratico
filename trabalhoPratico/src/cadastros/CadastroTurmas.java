package cadastros;

import app.Aluno;
import app.Turma;

import java.util.ArrayList;
import java.util.List;

public class CadastroTurmas {
    private List<Turma> turmas;

    public CadastroTurmas() {
        this.turmas = new ArrayList<>();
    }

    public void cadastrarTurma(Turma turma) {
        turmas.add(turma);
    }

    public Turma pesquisarTurma(String codigoDaTurma) {
        for (Turma turma : turmas) {
            if (turma.getCodigoDaTurma().equalsIgnoreCase(codigoDaTurma)) {
                return turma;
            }
        }
        return null;
    }

    public boolean atualizarTurma(Turma novaTurma, String codigoDaTurma) {
        boolean resposta = false;
        Turma remover = pesquisarTurma(codigoDaTurma);
        if (remover != null) {
            turmas.remove(remover);
            resposta = turmas.add(novaTurma);
        }
        return resposta;
    }

    public boolean removerTurma(Turma turma) {
        if (turma != null) {
            turmas.remove(turma);
            return true;
        }
        return false;
    }

    public void matricularAlunoNaTurma(Turma turma, Aluno aluno) {
        turma.matricularAluno(aluno);
    }


    public void listarTurmas() {
        System.out.println("Lista de turmas:");
        for (Turma t : turmas) {
            System.out.println(t.getCodigoDaTurma() + ": " + t.getProfessor().getNome() + " - " + t.getDisciplina().getNome());
        }
    }

    public void imprimirListaDePresenca(Turma turma) {
        turma.imprimirListaDePresenca();
    }
}