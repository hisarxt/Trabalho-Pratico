package cadastros;

import app.Aluno;
import app.Disciplina;
import app.Professor;
import app.Turma;

import java.util.ArrayList;
import java.util.List;

	public class CadastroTurmas {
		private List<Turma> turmas;
		private CadastroProfessores cadastroProfessor;
		private CadastroDisciplinas cadastroDisciplina;
		private CadastroAlunos cadastroAluno;
	
	public CadastroTurmas(CadastroProfessores cadastroProfessor, CadastroDisciplinas cadastroDisciplina){
		this.turmas = new ArrayList<>();
		this.cadastroProfessor = cadastroProfessor;
		this.cadastroDisciplina = cadastroDisciplina;
	}
	public void cadastrarTurma(String nome, String codigoDaTurma, String matriculaFubProfessor) {
		Professor professor = cadastroProfessor.pesquisarProfessores(matriculaFubProfessor);
		Disciplina disciplina = cadastroDisciplina.pesquisarDisciplina(nome);	
	
		if(professor == null) {
			System.out.println("Erro: Professor com matricula " + matriculaFubProfessor + "nao encontrado");
			return;
		}
		if(disciplina == null) {
			System.out.println("Erro: Disciplina com codigo " + codigoDaTurma + "nao encontrada");
			return;
		}
	
		Turma turma = new Turma(codigoDaTurma, professor, disciplina);
		turmas.add(turma);
		System.out.println("Turma cadastrada com sucesso: " + codigoDaTurma);
	}

	public Turma pesquisarTurma(String codigoDaTurma) {
        for (Turma turma : turmas) {
            if (turma.getCodigoDaTurma().equalsIgnoreCase(codigoDaTurma)) {
                return turma;
            }
        }
        return null;
    }
	
	public void atualizarTurma(String codigoDaTurma, String novaMatriculaFubProfessor, String novaDisciplinaCodigo) {
		Turma turma = pesquisarTurma(codigoDaTurma);
		if(turma != null) {
			Professor novoProfessor = cadastroProfessor.pesquisarProfessores(novaMatriculaFubProfessor);
			Disciplina novaDisciplina = cadastroDisciplina.pesquisarDisciplina(novaDisciplinaCodigo);
			turma = new Turma(codigoDaTurma, novoProfessor, novaDisciplina);
		}
	}
	
	public boolean removerTurma(String codigoDaTurma) {
        Turma turma = pesquisarTurma(codigoDaTurma);
        if (turma != null) {
            turmas.remove(turma);
            return true;
        }
        return false;
    }
	
	public void matricularAlunoNaTurma(String codigoDaTurma, String matriculaAluno) {
		Turma turma = pesquisarTurma(codigoDaTurma);
		Aluno aluno = cadastroAluno.pesquisarAlunos(matriculaAluno);
		
		if(turma != null && aluno != null) {
			turma.matricularAluno(aluno);
		} else {
			System.out.println("Turma ou aluno nao encontrado.");
		}
	}


	public void listarTurmas() {
        System.out.println("Lista de turmas:");
        for (Turma t : turmas) {
            System.out.println(t.getCodigoDaTurma() + ": " + t.getProfessor().getNome() + " - " + t.getDisciplina().getNome());
        }
    }
	
	public void imprimirListaDePresenca(String codigoDaTurma) {
		Turma turma = pesquisarTurma(codigoDaTurma);
		if(turma != null) {
			turma.imprimirListaDePresenca();
		} else {
			System.out.println("Turma nao encontrada");
		}
	}
	public void atualizarTurma(String codigoDaTurma, Turma atualizacao) {
		
	}
	public void cadastrarTurma(Turma novaTurma) {
		
	}
	public void removerTurma(Turma pesquisa) {
		
	}
}
