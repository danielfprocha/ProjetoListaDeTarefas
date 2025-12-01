package Menus;
import java.util.Scanner;

import Classes.Usuario;
import Servicos.ServicoUsuario;

public class MenuLogin {
    
    private Scanner teclado;
    private ServicoUsuario servicoUser;

    public MenuLogin(Scanner teclado) {
        this.teclado = teclado;
        this.servicoUser = new ServicoUsuario();
    }

    public Usuario login() {
        while (true) {
            System.out.println("\n-----Lista de Tarefas do Montanha-----\n\n---Menu de Login---\n\nDigite 1 ou 2 para entrar no sistema, e 3 para fechar o programa:\n1 - Fazer Login\n2 - Fazer Cadastro\n3 - Fechar Programa");
            String opcao = teclado.nextLine();

        
            switch (opcao) {
                case "1": 
                Usuario usuario = fazerLogin();
                if (usuario != null) return usuario;
                break;

                case "2": 
                fazerCadastro();
                break;

                case "3":
                System.out.println("\nFim do Programa!");
                return null;

                default: 
                System.out.println("Você digitar 1 (para fazer login), 2 (para fazer cadastro) ou 3 (para fechar o programa!");
            }
        }
    }

    private Usuario fazerLogin() {
        System.out.println("\nEmail: ");
        String email = teclado.nextLine();
        System.out.println("\nSenha: ");
        String senha = teclado.nextLine();

        try {
            Usuario usuario = servicoUser.login(email, senha);
            System.out.println("\nBem-vindo de volta, " + usuario.getNomeUsuario() + "!");
            return usuario;

        } catch (Exception erro) {
            System.out.println(erro.getMessage());
            return null;
        }
    }

    private void fazerCadastro() {
        try {
            System.out.println("\nDigite seu nome: ");
            String nome = teclado.nextLine();
            System.out.println("\nDigite seu melhor email: ");
            String email = teclado.nextLine();
            System.out.println("\nEscolha sua senha: ");
            String senha = teclado.nextLine();

            servicoUser.cadastrarUsuario(nome, email, senha);
            System.out.println("\nCadastro realizado no sistema!");

        } catch (Exception erro) {
            System.out.println("Erro ao realizar cadastro! " + erro.getMessage());
        }
    }
}
