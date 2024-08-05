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
	
	public Professor pesquisarProfessor(String matriculaFUB) {
		for (Professor p: professores) {
			if(p.getMatriculaFUB().equalsIgnoreCase(matriculaFUB)) {
				return p;
			}
		}
		return null;
	}
	 
	public boolean removerProfessor(Professor p) {
		boolean removeu = false;
		if(professores.contains(p)) {
			removeu = professores.remove(p); 
		}
		return removeu;
	}
	
	public boolean atualizarProfessor(String matriculaFUB, Professor p) {
		boolean resposta = false;
		Professor remover = pesquisarProfessor(matriculaFUB);
		if(remover != null) {
			professores.remove(remover);
			resposta = professores.add(p);
		}
		return resposta;
		
	}
}
