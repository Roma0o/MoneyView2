package br.com.moneyview.servicos;

import br.com.moneyview.modelo.Transacao;
import br.com.moneyview.repositorio.TransacaoDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class TransacaoServicos {
    private final TransacaoDAO transacaoDAO;
    
    public TransacaoServicos(){
        this.transacaoDAO = new TransacaoDAO();
    }
    
    public boolean registrarTransacao(Transacao transacao) {
        TransacaoValidador.validar(transacao);
        return transacaoDAO.salvar(transacao);
    }
    
    public List<Transacao> listarTransacoesDoUsuario(int usuarioId) {
        return transacaoDAO.listarPorUsuario(usuarioId);
    }
    
    public List<Transacao> listarTransacoesPorTipo(int usuarioId, String tipo) throws SQLException {
        if ("Despesa".equals(tipo)) {
            List<Transacao> despesas = transacaoDAO.buscarDespesasPorUsuario(usuarioId);
            return despesas;
        }if ("Ganho".equals(tipo)) {
            List<Transacao> ganhos = transacaoDAO.buscarGanhosPorUsuario(usuarioId);
            return ganhos;
        }
        return null;
    }
    
    public boolean atualizarTransacao(Transacao transacao) {
        try {
            TransacaoValidador.validar(transacao);
            return transacaoDAO.atualizar(transacao);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
}
