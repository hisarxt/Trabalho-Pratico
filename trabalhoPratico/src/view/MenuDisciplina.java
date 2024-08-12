package view;

import app.Disciplina;
import cadastros.CadastroDisciplinas;
import exceptions.CampoEmBrancoException;
import exceptions.DisciplinaNaoEncontradaException;
import exceptions.PesquisaEmBrancoException;

import javax.swing.*;
import java.awt.event.*;

public class MenuDisciplina {

    private static Disciplina telaPesquisarDisciplina(CadastroDisciplinas cadastroDisciplinas)
            throws DisciplinaNaoEncontradaException, PesquisaEmBrancoException {

        JTextField campo1 = new JTextField();

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // Layout para exibir um abaixo do outro
        painel.add(new JLabel("Código da Disciplina:"));
        painel.add(campo1);

        int resultado;
        do {
            resultado = JOptionPane.showConfirmDialog(null, painel, "Pesquisa de Disciplina",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (resultado == JOptionPane.OK_OPTION) {
                String codigoDaDisciplina = campo1.getText();
                if (codigoDaDisciplina.trim().isEmpty()) {
                    throw new PesquisaEmBrancoException();
                } else {
                    Disciplina pesquisa = cadastroDisciplinas.pesquisarDisciplina(codigoDaDisciplina);
                    if (pesquisa == null) {
                        throw new DisciplinaNaoEncontradaException();
                    }
                    return pesquisa;
                }
            } else {
                break;
            }
        } while (true);
        return null;
    }

    private static Disciplina telaCriarDisciplina() throws CampoEmBrancoException {
        JTextField campo1 = new JTextField();
        JTextField campo2 = new JTextField();

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.add(new JLabel("Nome da Disciplina:"));
        painel.add(campo1);
        painel.add(new JLabel("Código da Disciplina:"));
        painel.add(campo2);

        int resultado;
        do {
            resultado = JOptionPane.showConfirmDialog(null, painel, "Cadastro de Disciplina",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (resultado == JOptionPane.OK_OPTION) {
                String nome = campo1.getText();
                String codigoDaDisciplina = campo2.getText();

                if (nome.trim().isEmpty() || codigoDaDisciplina.trim().isEmpty()) {
                    String[] vetor = {nome, codigoDaDisciplina};
                    throw new CampoEmBrancoException(2, vetor);
                } else {
                    Disciplina disciplina = new Disciplina(nome, codigoDaDisciplina);
                    return disciplina;
                }
            } else {
                break;
            }
        } while (true);
        return null;
    }


    private static void cadastrarDisciplina(CadastroDisciplinas cadastroDisciplinas) {
        Disciplina disciplina = null;
        try {
            disciplina = telaCriarDisciplina();
            cadastroDisciplinas.cadastrarDisciplina(disciplina);
            String mensagem = "Disciplina '" + disciplina.getNome() + "' criada com sucesso.";
            JOptionPane.showMessageDialog(null, mensagem);
        } catch (CampoEmBrancoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void pesquisarDisciplina(CadastroDisciplinas cadastroDisciplinas) {
        Disciplina pesquisa = null;
        try {
            pesquisa = telaPesquisarDisciplina(cadastroDisciplinas);
            JOptionPane.showMessageDialog(null, pesquisa.toString());
        } catch (PesquisaEmBrancoException | DisciplinaNaoEncontradaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private static void atualizarDisciplina(CadastroDisciplinas cadastroDisciplinas) {
        Disciplina pesquisa = null;
        try {
            pesquisa = telaPesquisarDisciplina(cadastroDisciplinas);
            Disciplina novaDisciplina = telaCriarDisciplina();
            cadastroDisciplinas.atualizarDisciplina(pesquisa.getCodigoDisciplina(), novaDisciplina);
            String mensagem = "Disciplina '" + novaDisciplina.getNome() + "' atualizada com sucesso.";
            JOptionPane.showMessageDialog(null, mensagem);
        } catch (CampoEmBrancoException | PesquisaEmBrancoException | DisciplinaNaoEncontradaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }


    private static void removerDisciplina(CadastroDisciplinas cadastroDisciplinas) {
        Disciplina pesquisa = null;
        try {
            pesquisa = telaPesquisarDisciplina(cadastroDisciplinas);
            cadastroDisciplinas.removerDisciplina(pesquisa);
            String mensagem = "Disciplina '" + pesquisa.getNome() + "' removida com sucesso.";
            JOptionPane.showMessageDialog(null, mensagem);
        } catch (PesquisaEmBrancoException | DisciplinaNaoEncontradaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void menuDisciplinas(CadastroDisciplinas cadastroDisciplinas) {


        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                MenuPrincipal.menuOpcoes();
            }
        };

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        JButton cadastrarDisciplinaBttn = new JButton("Cadastrar uma disciplina");
        JButton pesquisarDisciplinaBttn = new JButton("Pesquisar uma disciplina");
        JButton atualizarDisciplinaBttn = new JButton("Atualizar uma disciplina");
        JButton removerDisciplinaBttn = new JButton("Remover uma disciplina");
        JButton voltarBttn = new JButton("Voltar");


        panel.add(cadastrarDisciplinaBttn);
        panel.add(pesquisarDisciplinaBttn);
        panel.add(atualizarDisciplinaBttn);
        panel.add(removerDisciplinaBttn);
        panel.add(voltarBttn);


        Object[] options = {panel};

        JOptionPane optionPane = new JOptionPane(
                "Selecione sua opção:",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                null);


        JDialog dialog = optionPane.createDialog("Disciplinas - menu");


        cadastrarDisciplinaBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarDisciplina(cadastroDisciplinas);
            }
        });

        pesquisarDisciplinaBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarDisciplina(cadastroDisciplinas);
            }
        });

        atualizarDisciplinaBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarDisciplina(cadastroDisciplinas);
            }
        });

        removerDisciplinaBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerDisciplina(cadastroDisciplinas);
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

