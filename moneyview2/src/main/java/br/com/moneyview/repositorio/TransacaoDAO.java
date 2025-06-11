package br.com.moneyview.repositorio;

import br.com.moneyview.modelo.Usuario;
import br.com.moneyview.modelo.Categoria;
import br.com.moneyview.modelo.Transacao;
import br.com.moneyview.servicos.CategoriaServicos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {
    
    public boolean salvar(Transacao transacao) {
        String sql = "INSERT INTO transacoes (tipo, valor, descricao, data, categoria_id, usuario_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transacao.getTipo());
            stmt.setDouble(2, transacao.getValor());
            stmt.setString(3, transacao.getDescricao());
            stmt.setDate(4, Date.valueOf(transacao.getData()));
            stmt.setInt(5, transacao.getCategoria().getId());
            stmt.setInt(6, transacao.getUsuario().getId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar transação: " + e.getMessage());
            return false;
        }
    }
 
    public List<Transacao> listarPorUsuario(int usuarioId) {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT t.*, c.nome AS categoria_nome FROM transacoes t " +
                     "JOIN categorias c ON t.categoria_id = c.id " +
                     "WHERE t.usuario_id = ? ORDER BY t.data DESC";

        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transacao t = new Transacao();
                t.setId(rs.getInt("id"));
                t.setTipo(rs.getString("tipo"));
                t.setValor(rs.getDouble("valor"));
                t.setDescricao(rs.getString("descricao"));
                t.setData(rs.getDate("data").toLocalDate());

                Categoria c = new Categoria();
                c.setId(rs.getInt("categoria_id"));
                c.setNome(rs.getString("categoria_nome"));
                t.setCategoria(c);

                Usuario u = new Usuario();
                u.setId(usuarioId);
                t.setUsuario(u);

                transacoes.add(t);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar transações: " + e.getMessage());
        }

        return transacoes;
    }
    
    private List<Transacao> buscarPorTipo(int idUsuario, String sql) throws SQLException {
        List<Transacao> lista = new ArrayList<>();

        try (Connection conn = ConexaoMySQL.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transacao t = new Transacao();
                t.setId(rs.getInt("id"));
                t.setTipo(rs.getString("tipo"));
                t.setValor(rs.getDouble("valor"));
                t.setDescricao(rs.getString("descricao"));
                t.setData(rs.getDate("data").toLocalDate());
                
                Categoria c = new Categoria();
                c.setId(rs.getInt("categoria_id"));
                c.setNome(rs.getString("categoria_nome"));
                t.setCategoria(c);
                
                Usuario u = new Usuario();
                u.setId(idUsuario);
                t.setUsuario(u);
                
                lista.add(t);
            }
        }

        return lista;
    }

    public List<Transacao> buscarDespesasPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT t.*, c.nome AS categoria_nome " +
             "FROM transacoes t " +
             "JOIN categorias c ON t.categoria_id = c.id " +
             "WHERE t.usuario_id = ? AND t.tipo = 'Despesa'";
        return buscarPorTipo(idUsuario, sql);
    }
    
    public List<Transacao> buscarGanhosPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT t.*, c.nome AS categoria_nome " +
             "FROM transacoes t " +
             "JOIN categorias c ON t.categoria_id = c.id " +
             "WHERE t.usuario_id = ? AND t.tipo = 'Ganho'";
        return buscarPorTipo(idUsuario, sql);
    }

    public boolean atualizar(Transacao transacao) { // id tipo valor desc data categoria usuario
        String sql = "UPDATE transacoes SET descricao = ?, valor = ?, data = ?, tipo = ?, categoria_id = ? WHERE id = ?";
    
        try (Connection conn = ConexaoMySQL.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
         
            stmt.setString(1, transacao.getDescricao());
            stmt.setDouble(2, transacao.getValor());
            stmt.setDate(3, Date.valueOf(transacao.getData()));
            stmt.setString(4, transacao.getTipo());
            stmt.setInt(5, transacao.getCategoria().getId());
            stmt.setInt(6, transacao.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
