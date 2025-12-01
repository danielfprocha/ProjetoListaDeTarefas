package Menus;

import java.util.List;
import java.util.Scanner;

import Classes.Tarefa;
import Classes.Usuario;
import Servicos.ServicoTarefa;

public class MenuTarefa {
    private Scanner teclado;
    private Usuario usuario;
    private ServicoTarefa servicoTarefa;

    public MenuTarefa (Scanner teclado, Usuario usuario) {
        this.teclado = teclado;
        this.usuario = usuario;
        this.servicoTarefa = new ServicoTarefa();
    }

    public void exibirPagina() {
        while (true) {
            System.out.println("\n-----Lista de Tarefas do Montanha-----\n\n---Menu de Tarefas---\n\nDigite 1, 2, 3, 4, 5 ou 6 para:\n1 - Criar nova tarefa\n2 - Listar tarefas\n3 - Pesquisar tarefa\n4 - Editar tarefa\n5 - Excluir tarefa\n6 - Voltar para Menu Principal\n");
            String opcao = teclado.nextLine();

            try {
                switch (opcao) {
                    case "1":
                    criarTarefa();
                    break;

                    case "2":
                    listarTarefas();
                    break;

                    case "3":
                    pesquisarTarefa();
                    break;

                    case "4":
                    editarTarefa();
                    break;

                    case "5":
                    excluirTarefa();
                    break;

                    case "6":
                    return;

                    default: 
                        System.out.println("\nVocê deve digitar 1 (para criar uma tarefa), 2 (para listar todas as suas tarefas), 3 (para pesquisar alguma tarefa), 4 (para editar alguma tarefa), 5 para excluir alguma tarefa) ou 6 (para voltar para o Menu Principal).");
                }

            } catch (Exception erro) {
                System.out.println("Erro! " + erro.getMessage());
            }
        }
    } 
    
    private void criarTarefa() throws Exception {
        System.out.print("\nNome da tarefa: ");
        String nomeTarefa = teclado.nextLine();
        System.out.print("\nStatus da tarefa (pendente ou terminada): ");
        String statusTarefa = teclado.nextLine();
        System.out.print("\nCategoria da tarefa: ");
        String categoriaTarefa = teclado.nextLine();
        System.out.print("\nData de início da tarefa (dd/mm/aaaa): ");
        String dataInicioTarefa = teclado.nextLine();
        System.out.print("\nPrazo final da tarefa (dd/mm/aaaa): ");
        String prazoFinal = teclado.nextLine();

        Tarefa novaTarefa = new Tarefa(nomeTarefa, statusTarefa, categoriaTarefa, dataInicioTarefa, prazoFinal, usuario.getEmail());
        List <Tarefa> tarefasUsuario = servicoTarefa.listarTarefas(usuario.getEmail());
        servicoTarefa.criarTarefa(tarefasUsuario, novaTarefa, usuario.getEmail());
        System.out.println("\nTarefa criada!");
    }

    private void listarTarefas() throws Exception {
        List<Tarefa> tarefas = servicoTarefa.listarTarefas(usuario.getEmail());
        
        if (tarefas.isEmpty()) {
            System.out.println("\nNão há nenhuma tarefa.");
            return;
        }

        System.out.println("-----Tarefas-----");
        for (Tarefa tarefa : tarefas) {
            tarefa.exibirTarefas();
        }
    }

    private void pesquisarTarefa() throws Exception {
        System.out.print("\n---Métodos de Pesquisa---\n\nDigite 1, 2, 3, 4, 5 ou 6 para:\n1 - Pesquisar pelo nome da tarefa\n2 - Pesquilar pelo status (pendente ou teminada) da tarefa\n3 - Pesquisar pela categoria da tarefa\n4 - Pesquisar pela data de início da tarefa\n5 - Pesquisar pelo prazo final da tarefa\n6 - Sair do Modo Pesquisa");
        String opcao = teclado.nextLine();
        List <Tarefa> tarefas = servicoTarefa.listarTarefas(usuario.getEmail());

        if (tarefas.isEmpty()) {
            System.out.println("\nNão há nenhuma tarefa cadastrada.");
            return;
        }

        List<Tarefa> tarefasFiltradas = null;

        switch (opcao) {
            case "1":
            System.out.print("\nDigite o nome da tarefa: ");
            String nome = teclado.nextLine();
            Tarefa tarefaEncontrada = servicoTarefa.pesquisarTarefa(usuario.getEmail(), nome);
            if (tarefaEncontrada == null) System.out.println("\nNenhuma tarefa encontrada.");
            else {
                System.out.println("\nTarefa encontrada!");
                tarefaEncontrada.exibirTarefas();
            }
            return;    

            case "2":
            System.out.print("\nDigite o status (pendente ou terminada): ");
            String status = teclado.nextLine();
            tarefasFiltradas = servicoTarefa.filtrarPorStatus(usuario.getEmail(), status);
            break;

            case "3":
            System.out.print("\nDigite a categoria: ");
            String categoria = teclado.nextLine();
            tarefasFiltradas = servicoTarefa.filtrarPorCategoria(usuario.getEmail(), categoria);
            break;

            case "4":
            System.out.print("\nDigite a data de início (dd/mm/aaaa): ");
            String dataInicio = teclado.nextLine();
            tarefasFiltradas = servicoTarefa.filtrarPorDataInicio(usuario.getEmail(), dataInicio);
            break;

            case "5":
            System.out.print("\nDigite o prazo final (dd/mm/aaaa): ");
            String prazo = teclado.nextLine();
            tarefasFiltradas = servicoTarefa.filtrarPorPrazo(usuario.getEmail(), prazo);
            break;

            case "6":
            return;
        
            default:
            System.out.println("Você deve digitar 1 (para pesquisar por nome), 2 (para pesquisar por status), 3 (para pesquisar por categoria), 4 (para pesquisar por data de início), 5 (para pesquisar por prazo final) ou 6 (para sair do Modo Pesquisa).");
            return;
        }

        if (tarefasFiltradas == null || tarefasFiltradas.isEmpty()) {
            System.out.println("\nNenhuma tarefa encontrada.");
        } else {
            System.out.println("\n-----Tarefas-----");
        for (Tarefa tarefa : tarefasFiltradas) {
            tarefa.exibirTarefas();
        }
        }
        
    }

    private void editarTarefa() throws Exception {
        System.out.print("\nDigite o nome da tarefa que você quer editar: ");
        String nomeTarefa = teclado.nextLine();

        Tarefa tarefa = servicoTarefa.pesquisarTarefa(usuario.getEmail(), nomeTarefa);
        if (tarefa == null) {
            System.out.println("\nNenhuma tarefa encontrada.");
            return;
        }

        System.out.print("\nDigite o novo nome da tarefa (para manter o nome original, apenas clique Enter): ");
        String nomeNovo = teclado.nextLine();
        if (nomeNovo.isEmpty()) nomeNovo = tarefa.getNomeTarefa();

        System.out.print("\nDigite o novo status da tarefa, entre 'pendente' e 'terminada' (para manter o status original, apenas clique Enter): ");
        String statusNovo = teclado.nextLine();
        if (statusNovo.isEmpty()) statusNovo = tarefa.getStatus();

        System.out.print("\nDigite a nova categoria da tarefa (para manter a categoria original, apenas clique Enter): ");
        String categoriaNova = teclado.nextLine();
        if (categoriaNova.isEmpty()) categoriaNova = tarefa.getCategoria();

        System.out.print("\nDigite a nova data de início da tarefa (para manter a data original, apenas clique Enter): ");
        String dataNova = teclado.nextLine();
        if (dataNova.isEmpty()) dataNova = tarefa.getDataInicio();

        System.out.print("\nDigite o novo prazo final da tarefa (para manter o prazo original, apenas clique Enter): ");
        String prazoNovo = teclado.nextLine();
        if (prazoNovo.isEmpty()) prazoNovo = tarefa.getPrazoFinal();

        Tarefa update = new Tarefa(nomeTarefa, statusNovo, categoriaNova, dataNova, prazoNovo, usuario.getEmail());
        servicoTarefa.EditarTarefa(usuario.getEmail(), nomeTarefa, update);
        System.out.println("\nTarefa atualizada!");
    }

    private void excluirTarefa() throws Exception {
        System.out.print("\nDigite o nome da tarefa que você quer excluir: ");
        String nomeTarefa = teclado.nextLine();

        System.out.println("\nDigite 'SIM' para confirmar a exclusão da tarefa.");
        String sim = teclado.nextLine();

        if ("SIM".equalsIgnoreCase(sim)) {
            servicoTarefa.excluirTarefa(usuario.getEmail(), nomeTarefa);
            System.out.println("\nTarefa excluída!");
        } else {
            System.out.println("\nExclusão da tarefa cancelada.");
        }
    }
}
