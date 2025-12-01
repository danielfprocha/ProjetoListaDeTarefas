package Menus;

import Classes.Usuario;

import java.util.Scanner;

public class MenuPrincipal {
    private Scanner teclado;
    private Usuario usuario;

    public MenuPrincipal (Scanner teclado, Usuario usuario) {
        this.teclado = teclado;
        this.usuario = usuario;
    }

    public void exibirPaginaPrincipal() {
        MenuTarefa menuTarefa = new MenuTarefa(teclado, usuario);
        MenuCategoria menuCategoria = new MenuCategoria(teclado, usuario);
        MenuConfig menuConfig = new MenuConfig(teclado, usuario);

        while (true) {
            System.out.println("\n-----Lista de Tarefas do Montanha-----\n\n---Menu Principal---\n\nDigite 1, 2, 3, 4 ou 5 para:\n1 - Tarefas\n2 - Categorias\n3 - Configurações\n4 - Sair da Conta\n5 - Fechar Programa");
            String opcao = teclado.nextLine();

            switch (opcao) {
                case "1":
                menuTarefa.exibirPagina();
                break;

                case "2":
                menuCategoria.exibirPagina();
                break;

                case "3":
                menuConfig.exibirPagina();
                break;

                case "4":
                System.out.println("\nSaindo da conta...");
                return;

                case "5":
                System.out.println("\nFim do Programa.");
                System.exit(0);

                default: 
                System.out.println("Você deve digitar 1 (para entrar no Menu de Tarefas), 2 (para entrar no Menu de Categorias), 3 (para entrar no Menu de Configurações), 4 (para sair da conta) ou 5 (para fechar o programa).");
            }
        }
    }
}
