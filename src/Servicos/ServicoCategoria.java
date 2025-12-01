package Servicos;

import Classes.Categoria;
import Classes.Tarefa;
import Classes.Usuario;
import Repositorios.RepositorioCategorias;
import Repositorios.RepositorioTarefas;

import java.util.List;
import java.util.stream.Collectors;

public class ServicoCategoria {

    public void criarCategoria (Usuario usuario, String nomeCategoria) throws Exception {
        List<Categoria> categorias = RepositorioCategorias.carregarCategoriasUsuario(usuario.getEmail());
        
        if ("Alpes".equalsIgnoreCase(usuario.getPlano()) && categorias.size() >= 5) {
            throw new Exception("Limite de 5 categorias atingido para o Plano Alpes. Mude para o Plano Everest ou continue com no máximo 5 categorias.");
        }
        
        for (Categoria categoria : categorias) {
            if (categoria.getNomeCategoria().equalsIgnoreCase(nomeCategoria)) throw new Exception("Já existe uma categoria com esse nome. Escolha outro nome.");
        }

        categorias.add(new Categoria(nomeCategoria, usuario.getEmail()));
        RepositorioCategorias.salvarCategoriasUsuario(categorias, usuario.getEmail());
    }

    
    public List<Categoria> listarCategorias (Usuario usuario) {
        return RepositorioCategorias.carregarCategoriasUsuario(usuario.getEmail());
    }

    public List<Tarefa> pesquisarCategoria (Usuario usuario, String nomeCategoria) {
        return RepositorioTarefas.carregarTarefasUsuario(usuario.getEmail()).stream()
        .filter(t -> t.getCategoria().equalsIgnoreCase(nomeCategoria))
        .collect(Collectors.toList());
    }

    public void renomearCategoria (Usuario usuario, String nomeCategoria, String nomeCategoriaAtualizado) throws Exception {
        List<Categoria> categorias = RepositorioCategorias.carregarCategoriasUsuario(usuario.getEmail());
        Categoria encontrada = categorias.stream()
        .filter(c -> c.getNomeCategoria().equalsIgnoreCase(nomeCategoria))
        .findFirst()
        .orElse(null);
        if (encontrada == null) throw new Exception("Nenhuma categoria encontrada.");

        if (categorias.stream().anyMatch(c -> c.getNomeCategoria().equalsIgnoreCase(nomeCategoriaAtualizado))) throw new Exception("Já existe uma categoria com esse nome. Escolha outro nome.");
        encontrada.setNomeCategoria(nomeCategoriaAtualizado);

        List<Tarefa> tarefas = RepositorioTarefas.carregarTarefasUsuario(usuario.getEmail());
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getCategoria().equalsIgnoreCase(nomeCategoria)) tarefa.setCategoria(nomeCategoriaAtualizado);
        }

        RepositorioCategorias.salvarCategoriasUsuario(categorias, usuario.getEmail());
        RepositorioTarefas.salvarTarefasUsuario(tarefas, usuario.getEmail());
    }

    public void excluirCategoria (Usuario usuario, String nomeCategoria, boolean excluir) throws Exception {
        List<Categoria> categorias = RepositorioCategorias.carregarCategoriasUsuario(usuario.getEmail());
        boolean categoriaRemovida = categorias.removeIf(c -> c.getNomeCategoria().equalsIgnoreCase(nomeCategoria));
        if (!categoriaRemovida) throw new Exception("Nenhuma categoria encontrada.");

        List<Tarefa> tarefas = RepositorioTarefas.carregarTarefasUsuario(usuario.getEmail());
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getCategoria().equalsIgnoreCase(nomeCategoria)) {
                if (excluir) tarefa.setCategoria("");
            }
        }

        RepositorioCategorias.salvarCategoriasUsuario(categorias, usuario.getEmail());
        RepositorioTarefas.salvarTarefasUsuario(tarefas, usuario.getEmail());
    }


    public List<Tarefa> tarefasDaCategoria(Usuario usuario, String nome) {
        throw new UnsupportedOperationException("Unimplemented method 'tarefasDaCategoria'");
    }
}
