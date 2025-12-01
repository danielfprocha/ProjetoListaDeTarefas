package Menus;

import Classes.Usuario;
import Servicos.ServicoUsuario;
import Classes.Hash;

import java.util.Scanner;

public class MenuConfig {
    private Scanner teclado;
    private Usuario usuario;
    private ServicoUsuario servicoUsuario;

    public MenuConfig (Scanner teclado, Usuario usuario) {
        this.teclado = teclado;
        this.usuario = usuario;
        this.servicoUsuario = new ServicoUsuario();
    }

     public void exibirPagina() {
        while (true) {
            System.out.println("\nNome: " + usuario.getNomeUsuario() + "\nEmail: " + usuario.getEmail()  + "\nPlano: " + usuario.getPlano());
            System.out.println("\n-----Lista de Tarefas do Montanha-----\n\n---Menu de Configurações---\n\nDigite 1, 2, 3, 4, 5 ou 6 para:\n1 - Editar Nome\n2 - Editar Email\n3 - Editar Senha\n4 - Trocar Plano\n5 - Excluir Conta\n6 - Voltar para Menu Principal");
            String opcao = teclado.nextLine();
            
            try {
                switch (opcao) {
                    case "1": editarNome(); break;
                    case "2": editarEmail(); break;
                    case "3": editarSenha(); break;
                    case "4": trocarPlano(); break;
                    case "5": excluirConta(); return;
                    case "6": return;
                    default: System.out.println("Você deve digitar 1 (para editar seu nome de usuário), 2 (para editar seu email da plataforma), 3 (para editar sua senha da plataforma), 4 (para editar seu plano da plataforma), 5 (para excluir permanentemente sua conta) ou 6 (para voltar para o Menu Principal).");
                }
            } catch (Exception erro) {
                System.out.println("Erro! " + erro.getMessage());
            }
        }
    }

    private void editarNome() {
        System.out.print("Novo nome: ");
        String novo = teclado.nextLine();
        usuario.setNomeUsuario(novo);
        servicoUsuario.atualizarUsuario(usuario);
        System.out.println("Nome atualizado.");
    }

    private void editarEmail() {
        System.out.print("Novo email: ");
        String novo = teclado.nextLine();
        usuario.setEmail(novo);
        servicoUsuario.atualizarUsuario(usuario);
        System.out.println("Email atualizado. Faça logout e login novamente se necessário.");
    }

    private void editarSenha() throws Exception {
        System.out.print("Senha atual: ");
        String atual = teclado.nextLine();
        if (!Hash.verificar(atual, usuario.getSenhaCriptografada())) {
            System.out.println("Senha atual incorreta.");
            return;
        }
        System.out.print("Nova senha: ");
        String nova = teclado.nextLine();
        usuario.setSenhaCriptografada(Hash.sha256Base64(nova));
        servicoUsuario.atualizarUsuario(usuario);
        System.out.println("Senha alterada.");
    }

    private void trocarPlano() {
        System.out.println("Plano atual: " + usuario.getPlano());
        System.out.println("1 - ALPES (grátis)");
        System.out.println("2 - EVEREST (pago)");
        System.out.print("Escolha: ");
        String opt = teclado.nextLine();
        if (opt.equals("1")) usuario.setPlano("ALPES");
        else if (opt.equals("2")) usuario.setPlano("EVEREST");
        servicoUsuario.atualizarUsuario(usuario);
        System.out.println("Plano alterado.");
    }

    private void excluirConta() {
        System.out.print("Digite SIM para confirmar exclusão da sua conta: ");
        String conf = teclado.nextLine();
        if ("SIM".equalsIgnoreCase(conf)) {
            servicoUsuario.excluirConta(usuario.getEmail());
            System.out.println("Conta excluída.");
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}
