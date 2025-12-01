package Classes;

public class Tarefa {
    private String nomeTarefa;
    private String status;
    private String categoria;
    private String dataInicio;
    private String prazoFinal;
    private String emailUsuario;

    public Tarefa (String nomeTarefa, String status, String categoria, String dataInicio, String prazoFinal, String emailUsuario) {
        this.nomeTarefa = nomeTarefa;
        this.status = status;
        this.categoria = categoria;
        this.dataInicio = dataInicio;
        this.prazoFinal = prazoFinal;
        this.emailUsuario = emailUsuario;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa (String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria (String categoria) {
        this.categoria = categoria;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio (String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getPrazoFinal() {
        return prazoFinal;
    }

    public void setPrazoFinal (String prazoFinal) {
        this.prazoFinal = prazoFinal;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario (String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public void exibirTarefas() {
        System.out.printf("\nNome: %s; Status: %s; Categoria: %s; Data de Início: %s; Prazo Final: %s", nomeTarefa, status, categoria, dataInicio, prazoFinal);
    }

    @Override
    public String toString() {
    return nomeTarefa + ";" + status + ";" + categoria + ";" + dataInicio + ";" + prazoFinal + ";" + emailUsuario;
    }

    public String toCSV() {
        return String.join(";", nomeTarefa, status, categoria, dataInicio, prazoFinal, emailUsuario);
    }
}
