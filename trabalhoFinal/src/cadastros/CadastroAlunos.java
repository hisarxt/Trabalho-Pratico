package cadastros;

import java.util.ArrayList;
import java.util.List;

import app.Aluno;

public class CadastroAlunos {
	int numAlunos;
	private List<Aluno> alunos;
	
	public CadastroAlunos() {
		numAlunos = 0;
		alunos = new ArrayList<Aluno>();
	}
	
	public int cadastrarAluno(Aluno a) {
		boolean cadastrou = alunos.add(a);
		if(cadastrou) {
			numAlunos = alunos.size();
		}
		return numAlunos;
	}
	
	public Aluno pesquisarAlunos(String matriculaAluno) {
		for(Aluno a: alunos) {
			if(a.getMatricula().equalsIgnoreCase(matriculaAluno)) {
				return a;
			}
		}
		return null;
	}
	
	public boolean removerAlunos(Aluno a) {
		boolean removeu = false;
		if(alunos.contains(a)) {
			removeu = alunos.remove(a);
		}
		return removeu;
	}
	
	public boolean atualizarAluno(String matricula, Aluno a) {
		boolean resposta = false;
		Aluno remover = pesquisarAlunos(matricula);
		if(remover != null) {
			alunos.remove(remover);
			resposta = alunos.add(a);
		}
		return resposta;
	}

}
