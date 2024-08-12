package app;

public class Professor extends PessoaFisica{
	String matriculaFub;
	String areaFormacao;
	
	public Professor(String nome, String cpf, String email, String matriculaFub, String areaFormacao) {
		super(nome, cpf, email);
		this.matriculaFub = matriculaFub;
		this.areaFormacao = areaFormacao;
	}

	public Professor() {
	}

	public final String getMatriculaFub() {
		return matriculaFub;
	}
	
	public final String getAreaFormacao() {
		return areaFormacao;
	}

	public void setMatriculaFub(String matriculaFub) {
		this.matriculaFub = matriculaFub;
	}

	public void setAreaFormacao(String areaFormacao) {
		this.areaFormacao = areaFormacao;
	}

	protected void finalize() throws Throwable {
		System.out.println("Destruindo objeto: " + this);
	}
	
	public String toString() {
		return "Professor: " + "\n" +
				"Matricula Fub do professor = '" + matriculaFub + '\'' + "\n" +
				"Area de formacao: '" + areaFormacao + '\'';
	}

}
