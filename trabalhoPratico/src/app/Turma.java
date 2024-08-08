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
}
