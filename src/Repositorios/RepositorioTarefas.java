package Repositorios;

import Classes.Tarefa;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RepositorioTarefas {

    private static final String ARQUIVO = "tarefas.txt";

    public static List<Tarefa> carregarTarefasTotal() {
        List<Tarefa> tarefas = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 6) {
                    String nomeTarefa = partes [0];
                    String status = partes [1];
                    String categoria = partes [2];
                    String dataInicio = partes [3];
                    String prazoFinal = partes [4];
                    String emailUsuario = partes [5];

                    Tarefa tarefa = new Tarefa (nomeTarefa, status, categoria, dataInicio, prazoFinal, emailUsuario);
                    tarefas.add(tarefa);
                }
            }
        } catch (IOException erro) {
            System.out.println("Erro ao carregar tarefas.");
            erro.printStackTrace();
        }

        return tarefas;
    }

    public static List<Tarefa> carregarTarefasUsuario(String email) {
        return carregarTarefasTotal().stream()
            .filter(t -> t.getEmailUsuario().equalsIgnoreCase(email))
            .collect(Collectors.toList());
    }

    public static void salvarTarefasTotal (List<Tarefa> tarefas) {
        try {
            File diretorio = new File("data");
            if (!diretorio.exists()) {
                diretorio.mkdirs(); 
            }    
        
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARQUIVO))) {
                for (Tarefa tarefa : tarefas) {
                    escritor.write(tarefa.toCSV());
                    escritor.newLine();
                }
            }
            
            } catch (IOException erro) {
                System.out.println("Erro ao salvar tarefas.");
                erro.printStackTrace();
        }
    }

    public static void salvarTarefasUsuario (List<Tarefa> tarefasUsuario, String email) {
        List<Tarefa> tarefasTotal = carregarTarefasTotal()
            .stream()
            .filter(t -> !t.getEmailUsuario().equalsIgnoreCase(email))
            .collect(Collectors.toList());
        tarefasTotal.addAll(tarefasUsuario);
        salvarTarefasTotal(tarefasTotal);
    }
}