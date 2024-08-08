package view;

import javax.swing.JOptionPane;

import app.Professor;
import cadastros.CadastroProfessores;

public class MenuProfessor {
	
	public static Professor dadosNovoProfessor() {
		String nome = lerNome();
		String cpf = lerCPF();
		String email = lerEmail();
		String matriculaFub = lerMatriculaFub();
		String areaFormacao = lerAreaFormacao();
		return new Professor(nome, cpf, email, matriculaFub, areaFormacao);
	}
	
	private static String lerNome() {
		return JOptionPane.showInputDialog("Informe o nome do professor: ");
	}
	
	private static String lerCPF() {
		return JOptionPane.showInputDialog("Informe o CPF do professor: ");
	}
	
	private static String lerEmail() {
		return JOptionPane.showInputDialog("Informe o email do professor: ");
	}
	
	private static String lerMatriculaFub() {
		return JOptionPane.showInputDialog("Informe a matricula do professor: ");
	}
	
	private static String lerAreaFormacao() {
		return JOptionPane.showInputDialog("Informe a area de formacao do professor: ");
	}
	
	public static void menuProfessor(CadastroProfessores cadProfessor) {
		String txt = "Informe a opcao desejada \n" + "1 - Cadastrar professor\n" + "2 - Pesquisar professor\n" + "3 - Atualizar professor\n" + "4 - Remover professor\n" + "0 - Voltar para o Menu";
		int opcao=-1;
		
		do {
			String strOpcao = JOptionPane.showInputDialog(txt);
			opcao = Integer.parseInt(strOpcao);
			
			switch(opcao) {
			case 1:
				Professor novoProfessor = dadosNovoProfessor();
				cadProfessor.cadastrarProfessor(novoProfessor);
				break;
			case 2:
				String matriculaFub = lerMatriculaFub();
				Professor p = cadProfessor.pesquisarProfessores(matriculaFub);
				if(p != null)
					JOptionPane.showMessageDialog(null, p.toString());
				break;
			case 3:
				matriculaFub = lerMatriculaFub();
				Professor novoCadastroP = dadosNovoProfessor();
				boolean atualizado = cadProfessor.atualizarProfessores(matriculaFub, novoCadastroP);
				if(atualizado) {
					JOptionPane.showMessageDialog(null, "Cadastro atualizado");
					break;	
				}
			case 4:
				matriculaFub = lerMatriculaFub();
				Professor remover = cadProfessor.pesquisarProfessores(matriculaFub);
				boolean removido = cadProfessor.removerProfessores(remover);
				if(removido) {
					JOptionPane.showMessageDialog(null, "Aluno removido do cadastro");
					System.gc();
				}
				default:
					break;
			}
			
		}while(opcao != 0);
		return;
	
	}

}
