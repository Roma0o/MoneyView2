
package br.com.moneyview.test.util;

import br.com.moneyview.util.CriptografiaUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class testCriptografia {
    
    @Test
    public void testMD5Correto() {
        String senha = "1234";
        String esperado = "81dc9bdb52d04dc20036dbd8313ed055"; // hash MD5 de "1234"

        String resultado = CriptografiaUtils.encriptarMD5(senha);

        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testNulo() {
        assertThrows(NullPointerException.class, () -> {
            CriptografiaUtils.encriptarMD5(null);
        });
    }
    
    @Test
    public void test32Caracteres() {
        String senha = "senhaSegura";
        String hash = CriptografiaUtils.encriptarMD5(senha);

        assertEquals(32, hash.length(), "O hash MD5 deve ter 32 caracteres hexadecimais");
    }  
}
