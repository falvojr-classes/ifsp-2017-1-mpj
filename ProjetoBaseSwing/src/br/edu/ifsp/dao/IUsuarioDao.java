package br.edu.ifsp.dao;

import br.edu.ifsp.model.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 * Strategy utilizada pelo UsuarioDao.
 *
 * @author Venilton FalvoJr
 *
 * @since 11/10/2016
 */
public interface IUsuarioDao {

    public void inserir(Usuario usuario) throws SQLException;
    
    public void alterar(Usuario usuario) throws SQLException;
    
    public void excluir(Usuario usuario) throws SQLException;
    
    public List<Usuario> listar() throws SQLException;
    
    public boolean autenticar(Usuario usuario) throws SQLException;
}
