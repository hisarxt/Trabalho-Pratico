package view;

import cadastros.CadastroAlunos;
import cadastros.CadastroDisciplinas;
import cadastros.CadastroProfessores;

import javax.swing.*;
import java.awt.event.*;

public class MenuPrincipal {

    static CadastroAlunos cadAluno = new CadastroAlunos();
    static CadastroProfessores cadProfessor = new CadastroProfessores();
    static CadastroDisciplinas cadDisciplina = new CadastroDisciplinas();

    public static void menuOpcoes() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));



        JButton button1 = new JButton("Cadastro de alunos");
        JButton button2 = new JButton("Cadastro de professores");
        JButton button3 = new JButton("Cadastro de disciplinas");
        JButton button4 = new JButton("Cadastro de turmas");


        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);


        Object[] options = {panel};

        JOptionPane optionPane = new JOptionPane(
                "Selecione sua opção:",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                null);


        JDialog dialog = optionPane.createDialog("SGA - Sistema de Gestão Acadêmica");


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                MenuAluno.menuAluno(cadAluno);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuProfessor.menuProfessor(cadProfessor);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuDisciplina.menuDisciplinas(cadDisciplina);

            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Sistema de turmas à ser implementado.");
            }
        });

        dialog.setVisible(true);
    }
}