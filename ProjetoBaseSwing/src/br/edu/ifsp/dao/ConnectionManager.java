package br.edu.ifsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que gerencia uma conexão única com o banco de dados. Para isso, a
 * classe mantem um atributo com a Connection e implementa o padrão de projetos
 * Singleton.
 *
 * @author Venilton FalvoJr
 *
 * @since 31/08/2016
 */
public final class ConnectionManager {

    private static ConnectionManager instancia;

    public static ConnectionManager getInstancia() {
        if (ConnectionManager.instancia == null) {
            ConnectionManager.instancia = new ConnectionManager();
        }
        return ConnectionManager.instancia;
    }

    private ConnectionManager() {
        super();
    }

    private Connection conexao;

    public Connection getConexao() throws SQLException {
        if (this.conexao == null) {
            this.conexao = this.newConnection();
        }
        return this.conexao;
    }

    /**
     * Basta invocar esse método para recebermos uma NOVA conexão pronta para
     * uso. Por isso, limitamos essa chamada ao construtor da classe
     * ConnectionManager, que implementa o padrão de projeto Singleton.
     *
     * @return conexão do tipo Connection.
     */
    private Connection newConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1/mpj", "root", "select*from0");
    }
}
