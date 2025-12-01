package Menus;

import Classes.Usuario;
import Classes.Tarefa;
import Classes.Categoria;
import Servicos.ServicoCategoria;

import java.util.Scanner;
import java.util.List;

public class MenuCategoria {
    private Scanner teclado;
    private Usuario usuario;
    private ServicoCategoria servicoCategoria;

    public MenuCategoria (Scanner teclado, Usuario usuario) {
        this.teclado = teclado;
        this.usuario = usuario;
        this.servicoCategoria = new ServicoCategoria();
    }

    public void exibirPagina() {
        while (true) {
            System.out.println("\n-----Lista de Tarefas do Montanha-----\n\n---Menu de Categorias---\n\nDigite 1, 2, 3, 4, 5 ou 6 para:\n1 - Criar nova categoria\n2 - Listar categorias\n3 - Pesquisar categoria\n4 - Renomear categoria\n5 - Excluir categoria\n6 - Voltar para Menu Principal");
            String opcao = teclado.nextLine();
            try {
                switch (opcao) {
                    case "1": criarCategoria(); break;
                    case "2": listarCategorias(); break;
                    case "3": pesquisarCategoria(); break;
                    case "4": renomearCategoria(); break;
                    case "5": excluirCategoria(); break;
                    case "6": return;
                    default: System.out.println("Você deve digitar 1 (para criar uma categoria), 2 (para listar todas as suas categorias), 3 (para pesquisar alguma categoria), 4 (para renomear alguma categoria), 5 (para excluir alguma categoria) ou 6 (para voltar para o Menu Principal).");
                }
            } catch (Exception erro) {
                System.out.println("Erro! " + erro.getMessage());
            }
        }
    }

    private void criarCategoria() throws Exception {
        System.out.print("Nome da nova categoria: ");
        String nome = teclado.nextLine();
        servicoCategoria.criarCategoria(usuario, nome);
        System.out.println("Categoria criada.");
    }

    private void listarCategorias() {
        List<Categoria> cats = servicoCategoria.listarCategorias(usuario);
        if (cats.isEmpty()) {
            System.out.println("Nenhuma categoria.");
            return;
        }
        System.out.println("-----Categorias-----");
        cats.forEach(c -> System.out.println(c.getNomeCategoria()));
    }

    private void pesquisarCategoria() {
        System.out.print("Nome da categoria: ");
        String nome = teclado.nextLine();
        List<Tarefa> tarefas = servicoCategoria.tarefasDaCategoria(usuario, nome);
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa nessa categoria.");
            return;
        }
        System.out.println("-----Categoria " + nome + " - Tarefas: -----");
        for (Tarefa tarefa : tarefas) {
            tarefa.exibirTarefas();
        }
    }

    private void renomearCategoria() throws Exception {
        System.out.print("Categoria atual: ");
        String old = teclado.nextLine();
        System.out.print("Novo nome: ");
        String novo = teclado.nextLine();
        servicoCategoria.renomearCategoria(usuario, old, novo);
        System.out.println("Categoria renomeada.");
    }

    private void excluirCategoria() throws Exception {
        System.out.print("Categoria a excluir: ");
        String nome = teclado.nextLine();
        System.out.print("Deseja remover categoria deixando tarefas sem categoria? (SIM/N): ");
        String resp = teclado.nextLine();
        boolean moverNull = "SIM".equalsIgnoreCase(resp);
        servicoCategoria.excluirCategoria(usuario, nome, moverNull);
        System.out.println("Categoria excluída.");
    }
}

