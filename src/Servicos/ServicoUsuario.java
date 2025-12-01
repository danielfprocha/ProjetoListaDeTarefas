package Servicos;

import Classes.Usuario;
import Repositorios.RepositorioUsuarios;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;

public class ServicoUsuario {
    
    private RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();

    public Usuario login(String email, String senha) throws Exception {
        List<Usuario> usuarios = repositorioUsuarios.carregarUsuarios();
        String senhaHash = criptografarSenha(senha);

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email) && usuario.getSenhaCriptografada().equals(senhaHash)) {
                return usuario;
            }
        }

        throw new Exception("Email ou senha incorretos.");
    }

    public void cadastrarUsuario(String nome, String email, String senha) throws Exception {
        List<Usuario> usuarios = repositorioUsuarios.carregarUsuarios();

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                throw new Exception("Já existe um usuário com esse email.");
            }
        }

        String senhaHash = criptografarSenha(senha);
        Usuario novoUsuario = new Usuario(nome, email, senhaHash, "Alpes");
        
        usuarios.add(novoUsuario);
        repositorioUsuarios.salvarUsuarios(usuarios);
    }

    private String criptografarSenha(String senha) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(senha.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(hash);
    }

    public void atualizarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Unimplemented method 'atualizarUsuario'");
    }

    public void excluirConta(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'excluirConta'");
    }

}