package Repositorios;

import Classes.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios {
    
    private static final String ARQUIVO = "data/usuarios.txt";

    public List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;

            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    usuarios.add(new Usuario(partes[0], partes[1], partes[2], partes[3]));
                } 
            }

        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo de usuário criado.");
        } catch (Exception erro) {
            erro.printStackTrace();
        } 

        return usuarios;
    }

    public void salvarUsuarios (List<Usuario> usuarios) {
        try {
            File diretorio = new File("data");
            if (!diretorio.exists()) {
                diretorio.mkdirs(); 
            }
        
        
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Usuario usuario : usuarios) {
                escritor.write(usuario.toCSV());
                escritor.newLine();
            }
        }
        } catch (IOException erro) {
            System.out.println("Erro! Falha do sistema ao salvar usuários.");
        }
    }
}