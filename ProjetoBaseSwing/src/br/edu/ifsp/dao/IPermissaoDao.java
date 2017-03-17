package br.edu.ifsp.dao;

import br.edu.ifsp.model.Permissao;
import java.sql.SQLException;
import java.util.List;

/**
 * Strategy utilizada pelo PermissaoDao.
 *
 * @author Venilton FalvoJr
 *
 * @since 11/10/2016
 */
public interface IPermissaoDao {

    public List<Permissao> listar() throws SQLException;
}
