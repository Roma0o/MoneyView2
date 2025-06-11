package br.com.moneyview.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografiaUtils {

    public static String encriptarMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] mensagemDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, mensagemDigest);
            String hashText = no.toString(16);
            // Preenche com zeros à esquerda se necessário
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao encriptar a senha: " + e.getMessage());
        }
    }
}