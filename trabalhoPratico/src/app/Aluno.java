package app;

public class Aluno extends PessoaFisica{
String matricula, curso;
	
	public Aluno(String nome, String cpf, String email, String matricula, String curso) {
		super(nome, cpf, email);
		this.matricula = matricula;
		this.curso = curso;
	}

	public Aluno() {
	}

	public final String getMatricula() {
		return matricula;
	}
	
	public final String getCurso() {
		return curso;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	protected void finalize() throws Throwable{
		System.out.println("Destruindo objeto: " + this);
	}
	
	public String toString() {
		String resposta = super.toString();
		resposta += "MATRICULA: " + matricula + '\n';
		resposta += "CURSO: " + curso + '\n';
		return resposta; 
	}

}