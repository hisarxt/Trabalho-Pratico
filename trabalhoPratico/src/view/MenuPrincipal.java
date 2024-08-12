package view;

import cadastros.CadastroAlunos;
import cadastros.CadastroDisciplinas;
import cadastros.CadastroProfessores;
import cadastros.CadastroTurmas;

import javax.swing.*;
import java.awt.event.*;

public class MenuPrincipal {

    static CadastroAlunos cadAluno = new CadastroAlunos();
    static CadastroProfessores cadProfessor = new CadastroProfessores();
    static CadastroDisciplinas cadDisciplina = new CadastroDisciplinas();
    static CadastroTurmas cadTurma = new CadastroTurmas(cadProfessor, cadDisciplina);

    public static void menuOpcoes() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));



        JButton alunosBttn = new JButton("Cadastro de alunos");
        JButton professoresBttn = new JButton("Cadastro de professores");
        JButton disciplinasBttn = new JButton("Cadastro de disciplinas");
        JButton turmasBttn = new JButton("Cadastro de turmas");


        panel.add(alunosBttn);
        panel.add(professoresBttn);
        panel.add(disciplinasBttn);
        panel.add(turmasBttn);


        Object[] options = {panel};

        JOptionPane optionPane = new JOptionPane(
                "Selecione sua opção:",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                null);


        JDialog dialog = optionPane.createDialog("SGA - Sistema de Gestão Acadêmica");


        alunosBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                MenuAluno.menuAluno(cadAluno);
            }
        });

        professoresBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuProfessor.menuProfessor(cadProfessor);
            }
        });

        disciplinasBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuDisciplina.menuDisciplinas(cadDisciplina);

            }
        });

        turmasBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MenuTurma.menuTurmas(cadTurma, cadDisciplina, cadProfessor);
            }
        });

        dialog.setVisible(true);
    }
}
