package app;

public class Professor extends PessoaFisica{
	String matriculaFub;
	String areaFormacao;
	
	public Professor(String nome, String cpf, String email, String matriculaFub, String areaFormacao) {
		super(nome, cpf, email);
		this.matriculaFub = matriculaFub;
		this.areaFormacao = areaFormacao;
	}
	
	public final String getMatriculaFub() {
		return matriculaFub;
	}
	
	public final String getAreaFormacao() {
		return areaFormacao;
	}
	
	protected void finalize() throws Throwable {
		System.out.println("Destruindo objeto: " + this);
	}
	
	public String toString() {
		String resposta = super.toString();
		resposta += "MATRICULA: " + matriculaFub + '\n';
		resposta += "AREA DE FORMACAO: " + areaFormacao + '\n';
		return resposta;
	}

}
