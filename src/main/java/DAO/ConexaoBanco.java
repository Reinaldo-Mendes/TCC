package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Ponto único de conexão com o banco de dados.
 *
 * As credenciais NÃO ficam no código: são lidas das variáveis de ambiente
 * DB_URL, DB_USER e DB_PASSWORD (veja o arquivo .env.example).
 */
public class ConexaoBanco {

    private static final String DRIVER = "org.postgresql.Driver";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Driver JDBC não encontrado: " + DRIVER);
        }
    }

    private static String variavelObrigatoria(String nome) {
        String valor = System.getenv(nome);
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalStateException("Variável de ambiente obrigatória não definida: " + nome);
        }
        return valor;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                variavelObrigatoria("DB_URL"),
                variavelObrigatoria("DB_USER"),
                variavelObrigatoria("DB_PASSWORD"));
    }
}
