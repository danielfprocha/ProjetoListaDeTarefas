package Servicos;

import Classes.Tarefa;
import Repositorios.RepositorioTarefas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicoTarefa {
    
    public void criarTarefa (List<Tarefa> tarefas, Tarefa novaTarefa, String emailUsuario) throws Exception {
        novaTarefa.setEmailUsuario(emailUsuario);

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getNomeTarefa().equalsIgnoreCase(novaTarefa.getNomeTarefa())) {
                throw new Exception("Erro! Já existe uma tarefa com esse nome.");   
            }
        }

        tarefas.add(novaTarefa);
        RepositorioTarefas.salvarTarefasUsuario(tarefas, emailUsuario);
    }

    public List<Tarefa> listarTarefas (String emailUsuario) {
        return RepositorioTarefas.carregarTarefasTotal().stream()
                .filter(t -> t.getEmailUsuario().equalsIgnoreCase(emailUsuario))
                .collect(Collectors.toList());
    }

    public Tarefa pesquisarTarefa (String emailUsuario, String nomeTarefa) {
        List<Tarefa> tarefasTotal = RepositorioTarefas.carregarTarefasTotal();
        return tarefasTotal.stream()
        .filter(t -> t.getEmailUsuario().equalsIgnoreCase(emailUsuario))
        .filter(t -> t.getNomeTarefa().equalsIgnoreCase(nomeTarefa))
        .findFirst()
        .orElse(null);
    }
    
    public List<Tarefa> pesquisarTarefasExtra (String emailUsuario, String nomeTarefa, String status, String categoria, String dataInicio, String prazoFinal) {
        return RepositorioTarefas.carregarTarefasTotal().stream()
        .filter(t -> t.getEmailUsuario().equalsIgnoreCase(emailUsuario))
        .filter(t -> nomeTarefa == null || t.getNomeTarefa().equalsIgnoreCase(nomeTarefa))
        .filter(t -> status == null || t.getStatus().equalsIgnoreCase(status))
        .filter(t -> categoria == null || t.getCategoria().equalsIgnoreCase(categoria))
        .filter(t -> dataInicio == null || t.getDataInicio().equalsIgnoreCase(dataInicio))
        .filter(t -> prazoFinal == null || t.getPrazoFinal().equalsIgnoreCase(prazoFinal))
        .collect(Collectors.toList());
    }

    public List<Tarefa> filtrarPorStatus(String emailUsuario, String status) throws Exception {
        List<Tarefa> tarefas = listarTarefas(emailUsuario);
        List<Tarefa> resultado = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getStatus().equalsIgnoreCase(status)) {
                resultado.add(tarefa);
            }
        }

        return resultado;
    }

    public List<Tarefa> filtrarPorCategoria(String emailUsuario, String categoria) throws Exception {
        List<Tarefa> tarefas = listarTarefas(emailUsuario);
        List<Tarefa> resultado = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(tarefa);
            }
        }

        return resultado;
    }

    public List<Tarefa> filtrarPorDataInicio(String emailUsuario, String dataInicio) throws Exception {
        List<Tarefa> tarefas = listarTarefas(emailUsuario);
        List<Tarefa> resultado = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getDataInicio().equalsIgnoreCase(dataInicio)) {
                resultado.add(tarefa);
            }
        }

        return resultado;
    }

    public List<Tarefa> filtrarPorPrazo(String emailUsuario, String prazoFinal) throws Exception {
        List<Tarefa> tarefas = listarTarefas(emailUsuario);
        List<Tarefa> resultado = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getPrazoFinal().equalsIgnoreCase(prazoFinal)) {
            resultado.add(tarefa);
            }
        }

        return resultado;
    }

    public void EditarTarefa (String emailUsuario, String nomeTarefa, Tarefa update) throws Exception {
        List<Tarefa> tarefas = RepositorioTarefas.carregarTarefasTotal();

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getNomeTarefa().equalsIgnoreCase(nomeTarefa)) {

                tarefa.setNomeTarefa(update.getNomeTarefa());
                tarefa.setStatus(update.getStatus());
                tarefa.setCategoria(update.getCategoria());
                tarefa.setDataInicio(update.getDataInicio());
                tarefa.setPrazoFinal(update.getPrazoFinal());

                RepositorioTarefas.salvarTarefasUsuario(tarefas, emailUsuario);
                return;
            }
        }
        throw new Exception("Tarefa não encontrada!");
    } 

    public void excluirTarefa (String emailUsuario, String nomeTarefa) throws Exception {
        List<Tarefa> tarefasUsuario = RepositorioTarefas.carregarTarefasTotal().stream()
        .filter(t -> t.getEmailUsuario().equalsIgnoreCase(emailUsuario))
        .collect(Collectors.toList());

        boolean tarefaRemovida = tarefasUsuario.removeIf(t -> t.getEmailUsuario().equalsIgnoreCase(emailUsuario) && t.getNomeTarefa().equalsIgnoreCase(nomeTarefa));
        if (!tarefaRemovida) throw new Exception("Nenhuma tarefa encontrada.");

        RepositorioTarefas.salvarTarefasUsuario(tarefasUsuario, emailUsuario);
    }
}