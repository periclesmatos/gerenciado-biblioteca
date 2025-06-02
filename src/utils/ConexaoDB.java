package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados.
 * Utiliza JDBC para estabelecer a conexão com o MySQL.
 */
public class ConexaoDB {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca_db";
    private static final String USER = "root";
    private static final String PASS = "root";

    /**
     * Método estático para obter uma conexão com o banco de dados.
     *
     * @return Connection objeto de conexão com o banco de dados
     * @throws SQLException se ocorrer um erro ao estabelecer a conexão
     */
    public static Connection getConnection() throws SQLException, SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
