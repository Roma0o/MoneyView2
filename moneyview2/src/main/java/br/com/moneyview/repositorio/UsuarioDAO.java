package br.com.moneyview.repositorio;

import br.com.moneyview.modelo.Usuario;
import java.sql.*;


public class UsuarioDAO {
    
    public boolean salvar(Usuario usuario){
        String sql = "INSERT INTO usuarios (nome_usuario, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
            return false;
        }
    }
    
    public Usuario buscarPorEmailESenha(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        
        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome_usuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        
        return null;
        
    }
    
    public boolean emailJaExiste(String email) {
    String sql = "SELECT 1 FROM usuarios WHERE email = ?";
    try (Connection conn = ConexaoMySQL.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        return rs.next(); // true se já existe
    } catch (SQLException e) {
        System.err.println("Erro ao verificar email: " + e.getMessage());
        return false;
    }
}
    
}
