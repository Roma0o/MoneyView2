
package br.com.moneyview.servicos;

import br.com.moneyview.modelo.Transacao;
import java.time.LocalDate;



public class TransacaoValidador {
    
    public static void validar(Transacao transacao) throws IllegalArgumentException {
        if (transacao == null) {
            throw new IllegalArgumentException("A transação não pode ser nula.");
        }

        if (transacao.getTipo() == null ) { // || transacao.getTipo().isBlank()
            throw new IllegalArgumentException("O tipo da transação deve ser informado.");
        }

        if (transacao.getValor() <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser maior que zero.");
        }

        if (transacao.getDescricao() == null ) { //  || transacao.getDescricao().isBlank()
            throw new IllegalArgumentException("A descrição da transação deve ser informada.");
        }

        if (transacao.getData() == null) {
            throw new IllegalArgumentException("A data da transação deve ser informada.");
        }

        if (transacao.getData().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data da transação não pode ser no futuro.");
        }

        if (transacao.getCategoria() == null) {
            throw new IllegalArgumentException("A categoria da transação deve ser informada.");
        }

        if (transacao.getUsuario() == null) {
            throw new IllegalArgumentException("A transação deve estar associada a um usuário.");
        }
    }

}
