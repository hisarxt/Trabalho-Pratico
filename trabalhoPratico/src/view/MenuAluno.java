package view;

import app.Aluno;
import cadastros.CadastroAlunos;

import javax.swing.*;
import java.awt.event.*;

public class MenuAluno {
    private static Aluno telaCriacaoAluno() {
        Aluno aluno = new Aluno();

        JTextField campo1 = new JTextField();
        JTextField campo2 = new JTextField();
        JTextField campo3 = new JTextField();
        JTextField campo4 = new JTextField();
        JTextField campo5 = new JTextField();

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.add(new JLabel("Nome do aluno:"));
        painel.add(campo1);
        painel.add(new JLabel("CPF:"));
        painel.add(campo2);
        painel.add(new JLabel("E-Mail:"));
        painel.add(campo3);
        painel.add(new JLabel("Matrícula:"));
        painel.add(campo4);
        painel.add(new JLabel("Curso:"));
        painel.add(campo5);


        int resultado;
        do {
            resultado = JOptionPane.showConfirmDialog(null, painel, "Cadastro de Disciplina",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (resultado == JOptionPane.OK_OPTION) {
                String nome = campo1.getText();
                String cpf = campo2.getText();
                String email = campo3.getText();
                String matricula = campo4.getText();
                String curso = campo5.getText();

                if (nome.trim().isEmpty() || cpf.trim().isEmpty() || email.trim().isEmpty() || matricula.trim().isEmpty()
                        || curso.trim().isEmpty()) {
                    // Lançar aqui a CampoEmBrancoException
                    JOptionPane.showMessageDialog(null, "Nenhum campo pode estar vazio!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    aluno.setNome(nome);
                    aluno.setCpf(cpf);
                    aluno.setEmail(email);
                    aluno.setMatricula(matricula);
                    aluno.setCurso(curso);

                    return aluno;
                }
            } else {
                break;
            }
        } while (true);
        return null;
    }

    private static Aluno telaPesquisaAluno(CadastroAlunos cadastroAlunos) {

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
                    JOptionPane.showMessageDialog(null, "O campo não pode estar vazio!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    Aluno pesquisa = cadastroAlunos.pesquisarAlunos(matricula);
                    return pesquisa;
                }
            } else {
                break;
            }
        } while (true);
        return null;
    }

    private static void cadastrarAluno(CadastroAlunos cadastroAlunos) {
        Aluno novoAluno = telaCriacaoAluno();
        cadastroAlunos.cadastrarAluno(novoAluno);
        JOptionPane.showMessageDialog(null, "Aluno '" + novoAluno.getNome()
                + "' cadastrado com sucesso.");
    }

    private static void pesquisarAluno(CadastroAlunos cadastroAlunos) {
        Aluno pesquisa = telaPesquisaAluno(cadastroAlunos);
        if (pesquisa != null) {
            JOptionPane.showMessageDialog(null, pesquisa.toString());
        }
        // Incluir exception aqui embaixo
        else {
            JOptionPane.showMessageDialog(null,
                    "Aluno não encontrado - matrícula inválida?", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void atualizarAluno(CadastroAlunos cadastroAlunos) {
        Aluno pesquisa = telaPesquisaAluno(cadastroAlunos);

        if (pesquisa != null) {
            Aluno atualizacao = telaCriacaoAluno();
            cadastroAlunos.atualizarAluno(pesquisa.getMatricula(), atualizacao);

            String mensagem = "Aluno '" + atualizacao.getNome() + "' atualizado com sucesso.";
            JOptionPane.showMessageDialog(null, mensagem);
        }

        // Incluir exception aqui embaixo
        else {
            JOptionPane.showMessageDialog(null,
                    "Aluno não encontrado - matrícula inválida?", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void removerAluno(CadastroAlunos cadastroAlunos) {
        Aluno remover = telaPesquisaAluno(cadastroAlunos);

        if (remover != null) {
            cadastroAlunos.removerAlunos(remover);

            String mensagem = "Aluno '" + remover.getNome() + "' removido com sucesso.";
            JOptionPane.showMessageDialog(null, mensagem);
        }
        // Incluir exception aqui embaixo
        else {
            JOptionPane.showMessageDialog(null,
                    "Aluno não encontrado - matrícula inválida?", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void menuAluno(CadastroAlunos cadastroAlunos) {

        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                MenuPrincipal.menuOpcoes();
            }
        };

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        JButton cadastrarAlunoBttn = new JButton("Cadastrar um aluno");
        JButton pesquisarAlunoBttn = new JButton("Pesquisar um aluno");
        JButton atualizarAlunoBttn = new JButton("Atualizar um aluno");
        JButton removerAlunoBttn = new JButton("Remover um aluno");
        JButton voltarBttn = new JButton("Voltar");


        panel.add(cadastrarAlunoBttn);
        panel.add(pesquisarAlunoBttn);
        panel.add(atualizarAlunoBttn);
        panel.add(removerAlunoBttn);
        panel.add(voltarBttn);


        Object[] options = {panel};

        JOptionPane optionPane = new JOptionPane(
                "Selecione sua opção:",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                null);


        JDialog dialog = optionPane.createDialog("Alunos - menu");


        cadastrarAlunoBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAluno(cadastroAlunos);
            }
        });

        pesquisarAlunoBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarAluno(cadastroAlunos);
            }
        });

        atualizarAlunoBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarAluno(cadastroAlunos);
            }
        });

        removerAlunoBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerAluno(cadastroAlunos);
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