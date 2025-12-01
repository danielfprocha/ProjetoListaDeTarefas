package Classes;

public class Categoria {
    private String nomeCategoria;
    private String emailUsuario;

    public Categoria (String nomeCategoria, String emailUsuario) {
        this.nomeCategoria = nomeCategoria;
        this.emailUsuario = emailUsuario;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    @Override
    public String toString() {
        return nomeCategoria + ";" + emailUsuario;
    }

    public String toCSV() {
        return String.join(";", nomeCategoria, emailUsuario);
    }
}
