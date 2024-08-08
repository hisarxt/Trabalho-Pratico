package cadastros;

import java.util.ArrayList;
import java.util.List;

import app.Professor;

public class CadastroProfessores {
	int numProfessores;
	private List<Professor> professores;
	
	public CadastroProfessores() {
		numProfessores = 0;
		professores = new ArrayList<Professor>();
	}
	
	public int cadastrarProfessor(Professor p) {
		boolean cadastrou = professores.add(p);
		if(cadastrou) {
			numProfessores = professores.size();
		}
		return numProfessores;
	}
	
	public Professor pesquisarProfessores(String matriculaFubProfessor) {
		for(Professor p: professores) {
			if(p.getMatriculaFub().equalsIgnoreCase(matriculaFubProfessor)) {
				return p;
			}
		}
		return null;
	}
	
	public boolean removerProfessores(Professor p) {
		boolean removeu = false;
		if(professores.contains(p)) {
			removeu = professores.remove(p);
		}
		return removeu;
	}
	
	public boolean atualizarProfessores(String matriculaFub, Professor p) {
		boolean resposta = false;
		Professor remover = pesquisarProfessores(matriculaFub);
		if(remover != null) {
			professores.remove(remover);
			resposta = professores.add(p);
		}
		return resposta;
	}

}