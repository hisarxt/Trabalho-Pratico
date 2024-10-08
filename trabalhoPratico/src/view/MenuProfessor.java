package view;

import app.Professor;
import cadastros.CadastroProfessores;
import exceptions.CampoEmBrancoException;
import exceptions.PesquisaEmBrancoException;
import exceptions.ProfessorNaoEncontradoException;

import javax.swing.*;
import java.awt.event.*;

public class MenuProfessor {

    public static Professor telaCriacaoProfessor() throws CampoEmBrancoException {
        Professor professor = new Professor();

        JTextField campo1 = new JTextField();
        JTextField campo2 = new JTextField();
        JTextField campo3 = new JTextField();
        JTextField campo4 = new JTextField();
        JTextField campo5 = new JTextField();

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.add(new JLabel("Nome do professor:"));
        painel.add(campo1);
        painel.add(new JLabel("CPF:"));
        painel.add(campo2);
        painel.add(new JLabel("E-Mail:"));
        painel.add(campo3);
        painel.add(new JLabel("Matrícula FUB:"));
        painel.add(campo4);
        painel.add(new JLabel("Área de Formação:"));
        painel.add(campo5);


        int resultado;
        do {
            resultado = JOptionPane.showConfirmDialog(null, painel, "Cadastro de Professor",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (resultado == JOptionPane.OK_OPTION) {
                String nome = campo1.getText();
                String cpf = campo2.getText();
                String email = campo3.getText();
                String matriculaFub = campo4.getText();
                String areaDeFormacao = campo5.getText();

                if (nome.trim().isEmpty() || cpf.trim().isEmpty() || email.trim().isEmpty()
                        || matriculaFub.trim().isEmpty() || areaDeFormacao.trim().isEmpty()) {

                    String[] vetor = {nome,cpf,email,matriculaFub,areaDeFormacao};

                    throw new CampoEmBrancoException(3,vetor);
                } else {
                    professor.setNome(nome);
                    professor.setCpf(cpf);
                    professor.setEmail(email);
                    professor.setMatriculaFub(matriculaFub);
                    professor.setAreaFormacao(areaDeFormacao);

                    return professor;
                }
            } else {
                break;
            }
        } while (true);
        return null;
    }

    private static Professor telaPesquisaProfessor(CadastroProfessores cadastroProfessores)
            throws ProfessorNaoEncontradoException, PesquisaEmBrancoException {


        JTextField campo1 = new JTextField();

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.add(new JLabel("Matrícula FUB do professor:"));
        painel.add(campo1);

        int resultado;
        do {
            resultado = JOptionPane.showConfirmDialog(null, painel, "Pesquisar professor",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (resultado == JOptionPane.OK_OPTION) {
                String matricula = campo1.getText();


                if (matricula.trim().isEmpty()) {
                    throw new PesquisaEmBrancoException();
                } else {
                    Professor pesquisa = cadastroProfessores.pesquisarProfessores(matricula);
                    if (pesquisa == null) {
                        throw new ProfessorNaoEncontradoException();

                    }
                    return pesquisa;
                }
            } else {
                break;
            }
        } while (true);
        return null;
    }

    private static void cadastrarProfessor(CadastroProfessores cadastroProfessores) {
        Professor novoProfessor = null;
        try {
            novoProfessor = telaCriacaoProfessor();
            cadastroProfessores.cadastrarProfessor(novoProfessor);
            JOptionPane.showMessageDialog(null, "Professor '" + novoProfessor.getNome()
                    + "' cadastrado com sucesso.");
        } catch (CampoEmBrancoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void pesquisarProfessor(CadastroProfessores cadastroProfessores) {
        Professor pesquisa = null;
        try {
            pesquisa = telaPesquisaProfessor(cadastroProfessores);
            JOptionPane.showMessageDialog(null, pesquisa.toString());
        } catch (PesquisaEmBrancoException | ProfessorNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    private static void atualizarProfessor(CadastroProfessores cadastroProfessores) {
        Professor pesquisa = null;
        try {
            pesquisa = telaPesquisaProfessor(cadastroProfessores);
        } catch (ProfessorNaoEncontradoException | PesquisaEmBrancoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }


        Professor atualizacao = null;
        try {
            atualizacao = telaCriacaoProfessor();
            cadastroProfessores.atualizarProfessores(pesquisa.getMatriculaFub(), atualizacao);
            String mensagem = "Professor '" + atualizacao.getNome() + "' atualizado com sucesso.";
            JOptionPane.showMessageDialog(null, mensagem);
        } catch (CampoEmBrancoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void removerProfessor(CadastroProfessores cadastroProfessores) {
        Professor remover = null;
        try {
            remover = telaPesquisaProfessor(cadastroProfessores);
            cadastroProfessores.removerProfessores(remover);
            String mensagem = "Professor '" + remover.getNome() + "' removido com sucesso.";
            JOptionPane.showMessageDialog(null, mensagem);

        } catch (PesquisaEmBrancoException | ProfessorNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void menuProfessor(CadastroProfessores cadastroProfessores) {
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                MenuPrincipal.menuOpcoes();
            }
        };

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        JButton cadastrarProfessorBttn = new JButton("Cadastrar um professor");
        JButton pesquisarProfessorBttn = new JButton("Pesquisar um professor");
        JButton atualizarProfessorBttn = new JButton("Atualizar um professor");
        JButton removerProfessorBttn = new JButton("Remover um professor");
        JButton voltarBttn = new JButton("Voltar");


        panel.add(cadastrarProfessorBttn);
        panel.add(pesquisarProfessorBttn);
        panel.add(atualizarProfessorBttn);
        panel.add(removerProfessorBttn);
        panel.add(voltarBttn);


        Object[] options = {panel};

        JOptionPane optionPane = new JOptionPane(
                "Selecione sua opção:",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                null);

        JDialog dialog = optionPane.createDialog("Professores - menu");

        cadastrarProfessorBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarProfessor(cadastroProfessores);
            }
        });

        pesquisarProfessorBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarProfessor(cadastroProfessores);
            }
        });

        atualizarProfessorBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarProfessor(cadastroProfessores);
            }
        });

        removerProfessorBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerProfessor(cadastroProfessores);
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
