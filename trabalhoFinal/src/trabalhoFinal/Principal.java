package trabalhoFinal;

import javax.swing.JOptionPane;


import cadastros.CadastroAlunos;
import view.MenuAluno;
import view.MenuPrincipal;
import cadastros.CadastroProfessores;
import view.MenuProfessor;


public class Principal {
	
	static CadastroAlunos cadAluno;
	static CadastroProfessores cadProfessor;

	public static void main(String[] args) {
		
		cadAluno = new CadastroAlunos();
		cadProfessor = new CadastroProfessores();
		
		int opcao = 0;
		
		do {
			opcao = MenuPrincipal.menuOpcoes();
			
			switch(opcao) {
			case 1:
				MenuAluno.menuAluno(cadAluno);
				break; 
			case 2:
				MenuProfessor.menuProfessor(cadProfessor);
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Cadastro de disciplinas a ser implementado");
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "Cadastro de turmas a ser implementado");
				break;
			case 0:
				break;
				default:
					JOptionPane.showMessageDialog(null, "Opcao invalida");
				opcao = -1;
				break;
			}
		}while(opcao != 0);
		
		return;
	}

}
