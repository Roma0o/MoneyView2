package br.com.moneyview.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/moneyview";
    private static final String USUARIO = "root";
    private static final String SENHA = "Bpersch0n123@";
    
    public static Connection getConexao() throws SQLException{
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    
}
