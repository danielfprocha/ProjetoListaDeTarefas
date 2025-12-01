package Classes;

public class Usuario {
    private String nomeUsuario;
    private String email;
    private String senhaCriptografada;
    private String plano;

    public Usuario (String nomeUsuario, String email, String senhaCriptografada, String plano) {
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senhaCriptografada = senhaCriptografada;
        this.plano = plano;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario (String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }

    public void setSenhaCriptografada (String senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano (String plano) {
        this.plano = plano;
    }

    public void exibirInformacoes() {
        System.out.printf("Nome: %s; Email: %s; Plano: %s", nomeUsuario, email, plano);
    } 

    @Override
    public String toString() {
        return nomeUsuario + ";" + email + ";" + senhaCriptografada + ";" + plano;
    }

    public String toCSV() {
        return String.join(";", nomeUsuario, email, senhaCriptografada, plano);
    }
}
