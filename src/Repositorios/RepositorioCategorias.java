package Repositorios;

import Classes.Categoria;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RepositorioCategorias {
    
    private static final String ARQUIVO = "data/categorias.txt";

    public static List<Categoria> carregarCategoriasTotal() {
        List<Categoria> categorias = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    String nomeCategoria = partes [0];
                    String emailUsuario = partes [1];

                    Categoria categoria = new Categoria(nomeCategoria, emailUsuario);
                    categorias.add(categoria);
                }
            }
        } catch (Exception erro) {
            System.out.println("Erro ao carregar categorias.");
            erro.printStackTrace();
        }

        return categorias;
    }

    public static List<Categoria> carregarCategoriasUsuario(String email) {
        return carregarCategoriasTotal().stream()
            .filter(t -> t.getEmailUsuario().equalsIgnoreCase(email))
            .collect(Collectors.toList());
    }

    public static void salvarCategoriasTotal (List<Categoria> categorias) {
        try {
            File diretorio = new File("data");
            if (!diretorio.exists()) {
                diretorio.mkdirs(); 
            }
            
        
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Categoria categoria : categorias) {
                escritor.write(categoria.toCSV());
                escritor.newLine();
            }
        }
            } catch (IOException erro) {
                System.out.println("Erro ao salvar categorias.");
                erro.printStackTrace();
            }
    }

    public static void salvarCategoriasUsuario (List<Categoria> categoriasUsuario, String email) {
        List<Categoria> categoriasTotal = carregarCategoriasTotal()
            .stream()
            .filter(t -> !t.getEmailUsuario().equalsIgnoreCase(email))
            .collect(Collectors.toList());
        categoriasTotal.addAll(categoriasUsuario);
        salvarCategoriasTotal(categoriasTotal);
    }
}
