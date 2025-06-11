
package br.com.moneyview.test.servicos;

import br.com.moneyview.modelo.*;
import br.com.moneyview.servicos.TransacaoValidador;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class testTransacaoValidador {
    
    private Transacao transacaoValida(){
        Transacao t = new Transacao();
        t.setTipo("expense");
        t.setValor(100.00);
        t.setDescricao("Compra");
        t.setData(LocalDate.now());
        t.setCategoria(new Categoria(1, "Alimentação"));
        Usuario u = new Usuario();
        u.setId(1);
        t.setUsuario(u);
        return t;
    }
    
    @Test
    public void testTransacaoValida() {
        assertDoesNotThrow(() -> TransacaoValidador.validar(transacaoValida()));
    }
    
    @Test
    public void testValorInvalido(){
        Transacao t = transacaoValida();
        t.setValor(0.0);

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            TransacaoValidador.validar(t);
        });

        assertEquals("O valor da transação deve ser maior que zero.", e.getMessage());
    }
    
    @Test
    public void testDataFutura() {
        Transacao t = transacaoValida();
        t.setData(LocalDate.now().plusDays(1));

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            TransacaoValidador.validar(t);
        });

        assertEquals("A data da transação não pode ser no futuro.", e.getMessage());
    }
    
    @Test
    public void testTipoNulo() {
        Transacao t = transacaoValida();
        t.setTipo(null);

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            TransacaoValidador.validar(t);
        });

        assertEquals("O tipo da transação deve ser informado.", e.getMessage());
    }
    
    @Test
    public void testDescricaoNula() {
        Transacao t = transacaoValida();
        t.setDescricao(null);

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            TransacaoValidador.validar(t);
        });

        assertEquals("A descrição da transação deve ser informada.", e.getMessage());
    }
}
