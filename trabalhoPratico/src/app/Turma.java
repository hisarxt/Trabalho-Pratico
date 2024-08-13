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

    public String retornarListaDePresenca() {
        String mensagem = "";
        mensagem += "Lista de Presenca: \n";
        mensagem += "Disciplina: " + disciplina.getNome() + "\n";
        mensagem += "Professor: " + professor.getNome() + "\n";
        mensagem += "Codigo da turma: " + codigoDaTurma + "\n";
        mensagem += "Alunos: " + "\n";
        for (Aluno aluno : alunosMatriculados) {
            mensagem += aluno.getNome() + " - " + aluno.getMatricula() + "\n";
        }
        return mensagem;
    }

    public String toString() {
        return "Turma: " + "\n" +
                "Nome = '" + nome + "'\n" +
                "Código da Turma = '" + codigoDaTurma + '\'' + "\n" +
                "Professor = '" + professor.getNome() + '\'' + "\n" +
                "Disciplina = '" + disciplina.getNome() + '\'';

    }
}
