package br.edu.ifsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe base para os DAO's.
 *
 * @author Venilton FalvoJr
 * 
 * @since 20/09/2016
 */
public class BaseDao {

    /**
     * Método que recupera uma instância única de Connection usando a classe
     * ConnectionManager.
     *
     * @return instância única de Connection.
     */
    protected Connection getConexao() throws SQLException {
        return ConnectionManager.getInstancia().getConexao();
    }

    /**
     * Método útil para a recuperação da chave primária gerada a partir de um
     * PreparedStatement.
     *
     * @param statement PreparedStatement criado usando a constante
     * Statement.RETURN_GENERATED_KEYS.
     *
     * @return Long que representa a chave primária gerada.
     * 
     * @throws java.sql.SQLException
     */
    protected Long getChaveGerada(PreparedStatement statement) throws SQLException {
        ResultSet rs = statement.getGeneratedKeys();
        rs.next();
        return rs.getLong(1);
    }
}
