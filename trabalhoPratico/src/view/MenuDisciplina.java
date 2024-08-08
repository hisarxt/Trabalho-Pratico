package view;

import app.Disciplina;
import cadastros.CadastroDisciplinas;

import javax.swing.*;

public class MenuDisciplina {

    private static Disciplina pesquisarCodigo(CadastroDisciplinas cadastroDisciplinas) {
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
                    JOptionPane.showMessageDialog(null, "O campo não pode estar vazio!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    Disciplina pesquisa = cadastroDisciplinas.pesquisarDisciplina(codigoDaDisciplina);
                    return pesquisa;
                }
            } else {
                break;
            }
        } while (true);
        return null;
    }

    private static Disciplina criarDisciplina() {
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
                    JOptionPane.showMessageDialog(null, "Nenhum campo pode estar vazio!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
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


    public static void cadastrarDisciplina(CadastroDisciplinas cadastroDisciplinas) {
        Disciplina disciplina = criarDisciplina();
        cadastroDisciplinas.cadastrarDisciplina(disciplina);
        String mensagem = "Disciplina '" + disciplina.getNome() + "' criada com sucesso.";
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public static void pesquisarDisciplina(CadastroDisciplinas cadastroDisciplinas) {
        Disciplina pesquisa = pesquisarCodigo(cadastroDisciplinas);
        if (pesquisa != null) {
            JOptionPane.showMessageDialog(null, pesquisa.toString());
        } else {
            JOptionPane.showMessageDialog(null,
                    "Disciplina não encontrada - código inválido?", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void atualizarDisciplina(CadastroDisciplinas cadastroDisciplinas) {
        Disciplina pesquisa = pesquisarCodigo(cadastroDisciplinas);
        if (pesquisa != null) {
            Disciplina novaDisciplina = criarDisciplina();
            cadastroDisciplinas.atualizarDisciplina(pesquisa.getCodigoDisciplina(),novaDisciplina);

            String mensagem = "Disciplina '" + novaDisciplina.getNome() + "' atualizada com sucesso.";
            JOptionPane.showMessageDialog(null, mensagem);

        } else {
            JOptionPane.showMessageDialog(null,
                    "Disciplina não encontrada - código inválido?", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void removerDisciplina(CadastroDisciplinas cadastroDisciplinas) {
        Disciplina pesquisa = pesquisarCodigo(cadastroDisciplinas);
        cadastroDisciplinas.removerDisciplina(pesquisa);
        if (pesquisa != null) {
            cadastroDisciplinas.removerDisciplina(pesquisa);

            String mensagem = "Disciplina '" + pesquisa.getNome() + "' removida com sucesso.";
            JOptionPane.showMessageDialog(null, mensagem);

        } else {
            JOptionPane.showMessageDialog(null,
                    "Disciplina não encontrada - código inválido?", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void menuDisciplinas(CadastroDisciplinas cadastroDisciplinas) {
        String txt = "Informe a opcao desejada \n" + "1 - Cadastrar disciplina\n" + "2 - Pesquisar disciplina\n" +
                "3 - Atualizar disciplina\n" + "4 - Remover disciplina\n" + "0 - Voltar para o Menu";

        int opcao = -1;

        do {
            String strOpcao = JOptionPane.showInputDialog(txt);
            opcao = Integer.parseInt(strOpcao);

            switch (opcao) {
                case 1:
                    cadastrarDisciplina(cadastroDisciplinas);
                    break;
                case 2:
                    pesquisarDisciplina(cadastroDisciplinas);
                    break;
                case 3:
                    atualizarDisciplina(cadastroDisciplinas);
                    break;
                case 4:
                    removerDisciplina(cadastroDisciplinas);
                    break;

            }


        } while (opcao != 0);
    }
}

