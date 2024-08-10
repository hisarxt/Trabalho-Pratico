package app;

import java.util.ArrayList;

public class Turma {
    String nome;
    String codigoDaTurma;
    Disciplina disciplina;
    Professor professor;
    ArrayList<Aluno> alunosMatriculados = new ArrayList<Aluno>();
    Sala sala;
    String horario;
    
    public Turma(String nome, String codigoDaTurma, Disciplina disciplina, Professor professor) {
    	this.nome = nome;
    	this.codigoDaTurma = codigoDaTurma;
    	this.disciplina = disciplina;
    	this.professor = professor;
    }
    
    public Turma(String codigoDaTurma, Professor professor, Disciplina disciplina) {
    	
	}

	public String getNome() {
    	return nome;
    }
    
    public String getCodigoDaTurma() {
    	return codigoDaTurma;
    }
    
    public Disciplina getDisciplina() {
    	return disciplina;
    }
    
    public Professor getProfessor() {
    	return professor;
    }
    
    public void matricularAluno(Aluno aluno) {
    	alunosMatriculados.add(aluno);
    }
    
    public void imprimirListaDePresenca() {
    	System.out.println("Lista de Presenca: ");
    	System.out.println("Disciplina: " + disciplina.getNome());
    	System.out.println("Professor: " + professor.getNome());
    	System.out.println("Codigo da turma: " + codigoDaTurma);
    	System.out.println("Alunos: ");
    	for(Aluno aluno : alunosMatriculados) {
    		System.out.println(aluno.getMatricula() + " - " + aluno.getNome());
    	}
    }
    
    public String toString() {
    	return "Turma{" +
                "codigo='" + codigoDaTurma + '\'' +
                ", professor=" + professor +
                ", disciplina=" + disciplina +
                ", alunos=" + alunosMatriculados +
                '}';
    }
}
