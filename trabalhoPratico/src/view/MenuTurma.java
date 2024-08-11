package view;

import javax.swing.*;
import java.awt.event.*;

import app.Turma;
import cadastros.CadastroTurmas;
import app.Disciplina;
import cadastros.CadastroDisciplinas;
import app.Professor;
import cadastros.CadastroProfessores;

public class MenuTurma {

    private static Turma telaPesquisarTurma(CadastroTurmas cadastroTurmas) {
        JTextField campo1 = new JTextField();

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.add(new JLabel("Código da Turma:"));
        painel.add(campo1);

        int resultado;
        do {
            resultado = JOptionPane.showConfirmDialog(null, painel, "Pesquisa de Turma",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (resultado == JOptionPane.OK_OPTION) {
                String codigoDaTurma = campo1.getText();

                if (codigoDaTurma.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O campo não pode estar vazio!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    Turma pesquisa = cadastroTurmas.pesquisarTurma(codigoDaTurma);
                    return pesquisa;
                }
            } else {
                break;
            }
        } while (true);
        return null;
    }

    private static Turma telaCriarTurma(CadastroProfessores cadastroProfessores, CadastroDisciplinas cadastroDisciplinas) {
        JTextField campo1 = new JTextField();
        JTextField campo2 = new JTextField();
        JTextField campo3 = new JTextField();

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.add(new JLabel("Código da Turma:"));
        painel.add(campo1);
        painel.add(new JLabel("Matrícula do Professor:"));
        painel.add(campo2);
        painel.add(new JLabel("Código da Disciplina:"));
        painel.add(campo3);

        int resultado;
        do {
            resultado = JOptionPane.showConfirmDialog(null, painel, "Cadastro de Turma",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (resultado == JOptionPane.OK_OPTION) {
                String codigoDaTurma = campo1.getText();
                String matriculaFubProfessor = campo2.getText();
                String codigoDaDisciplina = campo3.getText();

                if (codigoDaTurma.trim().isEmpty() || matriculaFubProfessor.trim().isEmpty() || codigoDaDisciplina.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum campo pode estar vazio!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    Professor professor = cadastroProfessores.pesquisarProfessores(matriculaFubProfessor);
                    Disciplina disciplina = cadastroDisciplinas.pesquisarDisciplina(codigoDaDisciplina);

                    if (professor == null) {
                        JOptionPane.showMessageDialog(null, "Professor não encontrado!",
                                "Erro", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }

                    if (disciplina == null) {
                        JOptionPane.showMessageDialog(null, "Disciplina não encontrada!",
                                "Erro", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }

                    Turma turma = new Turma(codigoDaTurma, professor, disciplina);
                    return turma;
                }
            } else {
                break;
            }
        } while (true);
        return null;
    }

    private static void cadastrarTurma(CadastroTurmas cadastroTurmas, CadastroProfessores cadastroProfessores, CadastroDisciplinas cadastroDisciplinas) {
        Turma turma = telaCriarTurma(cadastroProfessores, cadastroDisciplinas);
        if (turma != null) {
            cadastroTurmas.cadastrarTurma(turma.getCodigoDaTurma(), turma.getProfessor().getMatriculaFub(), turma.getDisciplina().getCodigoDisciplina());
            String mensagem = "Turma '" + turma.getCodigoDaTurma() + "' criada com sucesso.";
            JOptionPane.showMessageDialog(null, mensagem);
        }
    }

    private static void pesquisarTurma(CadastroTurmas cadastroTurmas) {
        Turma pesquisa = telaPesquisarTurma(cadastroTurmas);
        if (pesquisa != null) {
            JOptionPane.showMessageDialog(null, pesquisa.toString());
        } else {
            JOptionPane.showMessageDialog(null,
                    "Turma não encontrada - código inválido?", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void atualizarTurma(CadastroTurmas cadastroTurmas, CadastroProfessores cadastroProfessores, CadastroDisciplinas cadastroDisciplinas) {
        Turma pesquisa = telaPesquisarTurma(cadastroTurmas);
        if (pesquisa != null) {
            Turma novaTurma = telaCriarTurma(cadastroProfessores, cadastroDisciplinas);
            if (novaTurma != null) {
                cadastroTurmas.atualizarTurma(pesquisa.getCodigoDaTurma(), novaTurma);
                String mensagem = "Turma '" + novaTurma.getCodigoDaTurma() + "' atualizada com sucesso.";
                JOptionPane.showMessageDialog(null, mensagem);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Turma não encontrada - código inválido?", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void removerTurma(CadastroTurmas cadastroTurmas) {
        Turma pesquisa = telaPesquisarTurma(cadastroTurmas);
        if (pesquisa != null) {
            cadastroTurmas.removerTurma(pesquisa.getCodigoDaTurma());
            String mensagem = "Turma '" + pesquisa.getCodigoDaTurma() + "' removida com sucesso.";
            JOptionPane.showMessageDialog(null, mensagem);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Turma não encontrada - código inválido?", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void menuTurmas(CadastroTurmas cadastroTurmas, CadastroProfessores cadastroProfessores, CadastroDisciplinas cadastroDisciplinas) {
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                MenuPrincipal.menuOpcoes();
            }
        };

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton cadastrarTurmaBttn = new JButton("Cadastrar uma turma");
        JButton pesquisarTurmaBttn = new JButton("Pesquisar uma turma");
        JButton atualizarTurmaBttn = new JButton("Atualizar uma turma");
        JButton removerTurmaBttn = new JButton("Remover uma turma");
        JButton voltarBttn = new JButton("Voltar");

        panel.add(cadastrarTurmaBttn);
        panel.add(pesquisarTurmaBttn);
        panel.add(atualizarTurmaBttn);
        panel.add(removerTurmaBttn);
        panel.add(voltarBttn);

        Object[] options = {panel};

        JOptionPane optionPane = new JOptionPane(
                "Selecione sua opção:",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                null);

        JDialog dialog = optionPane.createDialog("Turmas - menu");

        cadastrarTurmaBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarTurma(cadastroTurmas, cadastroProfessores, cadastroDisciplinas);
            }
        });

        pesquisarTurmaBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarTurma(cadastroTurmas);
            }
        });

        atualizarTurmaBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTurma(cadastroTurmas, cadastroProfessores, cadastroDisciplinas);
            }
        });

        removerTurmaBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerTurma(cadastroTurmas);
            }
        });

        voltarBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.addWindowListener(windowListener);
        dialog.setVisible(true);
    }
