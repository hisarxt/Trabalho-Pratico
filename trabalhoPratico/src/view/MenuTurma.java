package view;

import app.Aluno;
import app.Disciplina;
import app.Professor;
import app.Turma;
import cadastros.CadastroAlunos;
import cadastros.CadastroDisciplinas;
import cadastros.CadastroProfessores;
import cadastros.CadastroTurmas;
import exceptions.*;

import javax.swing.*;
import java.awt.event.*;

public class MenuTurma {
    private static Aluno telaPesquisaAluno(CadastroAlunos cadastroAlunos) throws PesquisaEmBrancoException,
            AlunoNaoEncontradoException {

        JTextField campo1 = new JTextField();

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // Layout para exibir um abaixo do outro
        painel.add(new JLabel("Matrícula do aluno:"));
        painel.add(campo1);

        int resultado;
        do {
            resultado = JOptionPane.showConfirmDialog(null, painel, "Pesquisar aluno",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (resultado == JOptionPane.OK_OPTION) {
                String matricula = campo1.getText();

                if (matricula.trim().isEmpty()) {
                    throw new PesquisaEmBrancoException();
                } else {
                    Aluno pesquisa = cadastroAlunos.pesquisarAlunos(matricula);
                    if (pesquisa == null) {
                        throw new AlunoNaoEncontradoException();
                    }
                    return pesquisa;
                }
            } else {
                break;
            }
        } while (true);
        return null;
    }

    private static Turma telaPesquisarTurma(CadastroTurmas cadastroTurmas)
            throws PesquisaEmBrancoException, TurmaNaoEncontradaException {

        JTextField campo1 = new JTextField();

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.add(new JLabel("Código da turma:"));
        painel.add(campo1);

        int resultado;
        do {
            resultado = JOptionPane.showConfirmDialog(null, painel, "Pesquisa de Turma",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (resultado == JOptionPane.OK_OPTION) {
                String codigoDaTurma = campo1.getText();
                if (codigoDaTurma.trim().isEmpty()) {
                    throw new PesquisaEmBrancoException();
                } else {
                    Turma pesquisa = cadastroTurmas.pesquisarTurma(codigoDaTurma);
                    if (pesquisa == null) {
                        throw new TurmaNaoEncontradaException();
                    }
                    return pesquisa;
                }
            } else {
                break;
            }
        } while (true);
        return null;
    }

    private static Turma telaCriarTurma(CadastroProfessores cadastroProfessores, CadastroDisciplinas cadastroDisciplinas) throws CampoEmBrancoException, ProfessorNaoAtribuidoException,
            DisciplinaNaoAtribuidaException {
        JTextField campo1 = new JTextField();
        JTextField campo2 = new JTextField();
        JTextField campo3 = new JTextField();
        JTextField campo4 = new JTextField();

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.add(new JLabel("Nome da Turma:"));
        painel.add(campo1);
        painel.add(new JLabel("Código da Turma:"));
        painel.add(campo2);
        painel.add(new JLabel("Código da Disciplina:"));
        painel.add(campo3);
        painel.add(new JLabel("Matrícula FUB do Professor:"));
        painel.add(campo4);

        int resultado;
        do {
            resultado = JOptionPane.showConfirmDialog(null, painel, "Cadastro de Disciplina",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (resultado == JOptionPane.OK_OPTION) {
                String nome = campo1.getText();
                String codigoDaTurma = campo2.getText();
                String codigoDaDisciplina = campo3.getText();
                String matriculaFUB = campo4.getText();

                if (nome.trim().isEmpty() || codigoDaDisciplina.trim().isEmpty()) {
                    String[] vetor = {nome, codigoDaTurma, codigoDaDisciplina, matriculaFUB};
                    throw new CampoEmBrancoException(4, vetor);
                } else {
                    Professor professor = cadastroProfessores.pesquisarProfessores(matriculaFUB);
                    if (professor == null) {
                        throw new ProfessorNaoAtribuidoException();
                    } else {
                        Disciplina disciplina = cadastroDisciplinas.pesquisarDisciplina(codigoDaDisciplina);
                        if (disciplina == null) {
                            throw new DisciplinaNaoAtribuidaException();

                        } else {
                            Turma turma = new Turma(nome, codigoDaTurma, disciplina, professor);
                            return turma;
                        }
                    }
                }
            } else {
                break;
            }
        } while (true);
        return null;
    }

    private static void cadastrarTurma(CadastroTurmas cadastroTurmas, CadastroProfessores cadastroProfessores,
                                       CadastroDisciplinas cadastroDisciplinas) {
        Turma turma = null;
        try {
            turma = telaCriarTurma(cadastroProfessores, cadastroDisciplinas);
            cadastroTurmas.cadastrarTurma(turma);
            String mensagem = "Turma '" + turma.getNome() + "' criada com sucesso.";
            JOptionPane.showMessageDialog(null, mensagem);
        } catch (ProfessorNaoAtribuidoException | DisciplinaNaoAtribuidaException | CampoEmBrancoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void pesquisarTurma(CadastroTurmas cadastroTurmas) {
        Turma pesquisa = null;
        try {
            pesquisa = telaPesquisarTurma(cadastroTurmas);
            JOptionPane.showMessageDialog(null, pesquisa.toString());
        } catch (PesquisaEmBrancoException | TurmaNaoEncontradaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void atualizarTurma(CadastroTurmas cadastroTurmas, CadastroProfessores cadastroProfessores,
                                       CadastroDisciplinas cadastroDisciplinas) {
        Turma pesquisa = null;
        try {
            pesquisa = telaPesquisarTurma(cadastroTurmas);
            Turma novaTurma = telaCriarTurma(cadastroProfessores, cadastroDisciplinas);
            cadastroTurmas.atualizarTurma(novaTurma, pesquisa.getCodigoDaTurma());
            String mensagem = "Turma '" + novaTurma.getNome() + "' atualizada com sucesso.";
            JOptionPane.showMessageDialog(null, mensagem);
        } catch (DisciplinaNaoAtribuidaException | ProfessorNaoAtribuidoException | CampoEmBrancoException |
                 PesquisaEmBrancoException | TurmaNaoEncontradaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    private static void removerTurma(CadastroTurmas cadastroTurmas) {
        Turma pesquisa = null;
        try {
            pesquisa = telaPesquisarTurma(cadastroTurmas);
            cadastroTurmas.removerTurma(pesquisa);
            String mensagem = "Turma '" + pesquisa.getNome() + "' removida com sucesso.";
            JOptionPane.showMessageDialog(null, mensagem);
        } catch (PesquisaEmBrancoException | TurmaNaoEncontradaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void matricularAluno(CadastroTurmas cadastroTurmas, CadastroAlunos cadastroAlunos) {
        Turma turma = null;
        Aluno aluno = null;
        try {
            aluno = telaPesquisaAluno(cadastroAlunos);
            turma = telaPesquisarTurma(cadastroTurmas);
            cadastroTurmas.matricularAlunoNaTurma(turma, aluno);
            String mensagem = "Aluno '" + aluno.getNome() + "' matriculado com sucesso na turma " + turma.getNome() + ".";
            JOptionPane.showMessageDialog(null, mensagem);
        } catch (TurmaNaoEncontradaException | AlunoNaoEncontradoException | PesquisaEmBrancoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void imprimirListaDePresenca(CadastroTurmas cadastroTurmas) {
        Turma turma = null;
        try {
            turma = telaPesquisarTurma(cadastroTurmas);
            String listaDePresenca = turma.retornarListaDePresenca();
            JOptionPane.showMessageDialog(null, listaDePresenca);
        } catch (TurmaNaoEncontradaException | PesquisaEmBrancoException e) {
            throw new RuntimeException(e);
        }
    }

    public static void menuTurmas(CadastroTurmas cadastroTurmas, CadastroProfessores cadastroProfessores,
                                  CadastroDisciplinas cadastroDisciplinas, CadastroAlunos cadastroAlunos) {

        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                MenuPrincipal.menuOpcoes();
            }
        };

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        JButton cadastrarTurmaBttn = new JButton("Cadastrar Turma");
        JButton pesquisarTurmaBttn = new JButton("Pesquisar Turma");
        JButton atualizarTurmaBttn = new JButton("Atualizar Turma");
        JButton matricularAlunoBttn = new JButton("Matricular aluno");
        JButton imprimirListaDePresencaBttn = new JButton("Imprimir lista de presença");
        JButton removerTurmaBttn = new JButton("Remover Turma");
        JButton voltarBttn = new JButton("Voltar");


        panel.add(cadastrarTurmaBttn);
        panel.add(pesquisarTurmaBttn);
        panel.add(atualizarTurmaBttn);
        panel.add(removerTurmaBttn);
        panel.add(matricularAlunoBttn);
        panel.add(imprimirListaDePresencaBttn);
        panel.add(voltarBttn);


        Object[] options = {panel};

        JOptionPane optionPane = new JOptionPane(
                "Selecione sua opção:",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                null);


        JDialog dialog = optionPane.createDialog("Menu de Turmas");


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

        matricularAlunoBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matricularAluno(cadastroTurmas, cadastroAlunos);
            }
        });

        imprimirListaDePresencaBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirListaDePresenca(cadastroTurmas);
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
}

