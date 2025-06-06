package br.com.moneyview.servicos;

import java.sql.SQLException;
import br.com.moneyview.modelo.Categoria;
import br.com.moneyview.repositorio.CategoriaDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaServicos {
    private final CategoriaDAO categoriaDAO;
    
    public CategoriaServicos() {
        this.categoriaDAO = new CategoriaDAO();
    }
    
    public boolean adicionarCategoria(String nome) {
        if (nome == null || nome.isBlank()) {
            System.out.println("Nome da categoria não pode ser vazio");
            return false;
        }
        
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        return categoriaDAO.salvar(categoria);
        
    }
    
    public int buscarId(String nomeCategoria) throws SQLException{
        try {
            return categoriaDAO.buscarId(nomeCategoria);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaServicos.class.getName()).log(Level.SEVERE, null, ex);
        }
        adicionarCategoria(nomeCategoria);
        return categoriaDAO.buscarId(nomeCategoria);
    }
    
    public List<Categoria> listarCategorias() {
        return categoriaDAO.listarTodas();
    }
    
}
