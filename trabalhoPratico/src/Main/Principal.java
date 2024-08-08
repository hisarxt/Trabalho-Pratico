package Main;

import cadastros.CadastroAlunos;
import cadastros.CadastroDisciplinas;
import cadastros.CadastroProfessores;
import view.MenuAluno;
import view.MenuDisciplina;
import view.MenuPrincipal;
import view.MenuProfessor;

import javax.swing.*;


public class Principal {

    static CadastroAlunos cadAluno;
    static CadastroProfessores cadProfessor;
    static CadastroDisciplinas cadDisciplinas;

    public static void main(String[] args) {

        cadAluno = new CadastroAlunos();
        cadProfessor = new CadastroProfessores();
        cadDisciplinas = new CadastroDisciplinas();
        int opcao;

        do {
            opcao = MenuPrincipal.menuOpcoes();

            switch (opcao) {
                case 1:
                    MenuAluno.menuAluno(cadAluno);
                    break;
                case 2:
                    MenuProfessor.menuProfessor(cadProfessor);
                    break;
                case 3:
                    MenuDisciplina.menuDisciplinas(cadDisciplinas);
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
        } while (opcao != 0);
    }

}