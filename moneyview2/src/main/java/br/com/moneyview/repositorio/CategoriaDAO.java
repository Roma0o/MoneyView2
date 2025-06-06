package br.com.moneyview.repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.com.moneyview.modelo.Categoria;

public class CategoriaDAO {
    
    public boolean salvar(Categoria categoria){
        String sql = "INSERT INTO categorias (nome) VALUES (?)";
        
        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar categoria: " + e.getMessage());
            return false;
        }
        
    }
    
    public int buscarId(String nomeCategoria) throws SQLException {
        String sql = "SELECT id FROM categorias WHERE nome = ?";
        
        try (Connection conexao = ConexaoMySQL.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setString(1, nomeCategoria);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return -1;
                }
            }
        }
    }
    
    public List<Categoria> listarTodas() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                categorias.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }

        return categorias;
    }
    
}
