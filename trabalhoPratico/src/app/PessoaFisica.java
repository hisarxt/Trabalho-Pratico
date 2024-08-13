package app;

public class PessoaFisica {
    private String nome, cpf, email;

    public PessoaFisica() {
    }

    public PessoaFisica(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public final String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Nome = '" + nome + '\'' + "\n" +
                "CPF = '" + cpf + '\'' + "\n" +
                "Email = '" + email + '\'';
    }

}
