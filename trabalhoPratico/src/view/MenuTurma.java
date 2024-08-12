package view;

import app.Turma;
import app.Disciplina;
import app.Professor;
import cadastros.CadastroTurmas;
import cadastros.CadastroDisciplinas;
import cadastros.CadastroProfessores;
import exceptions.CampoEmBrancoException;
import exceptions.DisciplinaNaoEncontradaException;
import exceptions.ProfessorNaoEncontradoException;
import exceptions.TurmaNaoEncontradaException;
import exceptions.PesquisaEmBrancoException;

import javax.swing.*;
import java.awt.event.*;

public class MenuTurma {
	private static Turma telaPesquisarTurma(CadastroTurmas cadastroTurmas)
		throws TurmaNaoEncontradaException, PesquisaEmBrancoException {
			
			JTextField campo1 = new JTextField();
			
			JPanel painel = new JPanel();
			painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // Layout para exibir um abaixo do outro
	        painel.add(new JLabel("Código da Turma:"));
	        painel.add(campo1);
	        
	        int resultado;
	        do {
	        	resultado = JOptionPane.showConfirmDialog(null, painel, "Pesquisa de Turma",
	        			JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        	if(resultado == JOptionPane.OK_OPTION) {
	        		String codigoDaTurma = campo1.getText();
	        		if(codigoDaTurma.trim().isEmpty()) {
	        			throw new PesquisaEmBrancoException();
	        	} else {
	        		Turma pesquisa = cadastroTurmas.pesquisarTurma(codigoDaTurma);
	        		if(pesquisa == null) {
	        			throw new TurmaNaoEncontradaException();
	        		}
	        		return pesquisa;
	        	} 
	        } else {
	        	break;
	        }
	        }while(true);
	        return null;
		}
	
	private static Turma telaCriarTurma() throws CampoEmBrancoException{
		JTextField campo1 = new JTextField();
		JTextField campo2 = new JTextField();
		JTextField campo3 = new JTextField();
		
		JPanel painel = new JPanel();
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
		painel.add(new JLabel("Codigo da Turma: "));
		painel.add(campo1);
		painel.add(new JLabel("Codigo da Disciplina: "));
		painel.add(campo2);
		painel.add(new JLabel("Matricula do Professor: "));
		painel.add(campo3);
		
		int resultado;
		do {
			resultado = JOptionPane.showConfirmDialog(null, painel, "Cadastro de Turma",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			if(resultado == JOptionPane.OK_OPTION) {
				String codigoDaTurma = campo1.getText();
				String codigoDisciplina = campo2.getText();
				String matriculaFub = campo3.getText();
				
				if(codigoDaTurma.trim().isEmpty() || codigoDisciplina.trim().isEmpty() || matriculaFub.trim().isEmpty()) {
					String[] vetor = {codigoDaTurma, codigoDisciplina, matriculaFub};
					throw new CampoEmBrancoException(3, vetor);
				} else {
					Turma turma = new Turma(codigoDaTurma, codigoDisciplina, matriculaFub);
					return turma;
				}
			} else {
				break;
			}
		}while(true);
		return null;
	}
	
	private static void cadastrarTurma(CadastroTurmas cadastroTurmas, CadastroDisciplinas cadastroDisciplinas, CadastroProfessores cadastroProfessores) {
		Turma turma = null;
		try {
			turma = telaCriarTurma();
			cadastroTurmas.cadastrarTurma(turma);
			String mensagem = "Turma '" + turma.getCodigoDaTurma() + "' criada com sucesso.";
			JOptionPane.showMessageDialog(null, mensagem);
		}catch (CampoEmBrancoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private static void pesquisarTurma(CadastroTurmas cadastroTurmas) {
		Turma pesquisa = null;
		try {
			pesquisa = telaPesquisarTurma(cadastroTurmas);
			JOptionPane.showMessageDialog(null, pesquisa.toString());
		} catch(PesquisaEmBrancoException | TurmaNaoEncontradaException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private static void atualizarTurma(CadastroTurmas cadastroTurmas) {
		Turma pesquisa = null;
		try {
			pesquisa = telaPesquisarTurma(cadastroTurmas);
			Turma novaTurma = telaCriarTurma();
			cadastroTurmas.atualizarTurma(pesquisa.getCodigoDaTurma(), novaTurma);
			String mensagem = "Turma '" + novaTurma.getCodigoDaTurma() + "' atualizada com sucesso.";
			JOptionPane.showMessageDialog(null, mensagem);
		}catch(CampoEmBrancoException | PesquisaEmBrancoException | TurmaNaoEncontradaException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private static void removerTurma(CadastroTurmas cadastroTurmas) {
		Turma pesquisa = null;
		try {
			pesquisa = telaPesquisarTurma(cadastroTurmas);
			cadastroTurmas.removerTurma(pesquisa);
			cadastroTurmas.removerTurma(pesquisa);
			String mensagem = "Turma '" + pesquisa.getCodigoDaTurma() + "' removida com sucesso.";
			JOptionPane.showMessageDialog(null, mensagem);
		} catch(PesquisaEmBrancoException | TurmaNaoEncontradaException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void menuTurmas(CadastroTurmas cadastroTurmas, CadastroDisciplinas cadastroDisciplinas, CadastroProfessores cadastroProfessores) {
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
				"Selecione sua opcao:",
				JOptionPane.PLAIN_MESSAGE,
				JOptionPane.DEFAULT_OPTION,
				null,
				options,
				null);
		
		JDialog dialog = optionPane.createDialog("Turmas - menu");
		
		cadastrarTurmaBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerfomed(ActionEvent e) {
				cadastrarTurma(cadastroTurmas, cadastroDisciplinas, cadastroProfessores);
			}
		});
		
		pesquisarTurmaBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerfomed(ActionEvent e) {
				pesquisarTurma(cadastroTurmas);
			}
		});
		
		atualizarTurmaBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerfomed(ActionEvent e) {
				atualizarTurma(cadastroTurmas, cadastroDisciplinas, cadastroProfessores);
			}
		});
		
		removerTurmaBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerfomed(ActionEvent e) {
				removerTurma(cadastroTurmas);
			}
		});
		
		voltarBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerfomed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		dialog.addWindowListener(windowListener);
		
		dialog.setVisible(true);
	}
}
