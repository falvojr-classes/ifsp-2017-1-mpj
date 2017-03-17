package br.edu.ifsp.controller;

import br.edu.ifsp.dao.PermissaoDao;
import br.edu.ifsp.dao.UsuarioDao;
import br.edu.ifsp.model.Permissao;
import br.edu.ifsp.model.Usuario;
import br.edu.ifsp.util.ExcecaoNegocial;
import br.edu.ifsp.util.Mensagens;
import java.sql.SQLException;
import java.util.List;

/**
 * Responsável pelas regras de negócio (entidade Permissao) e padronização das
 * exceções.
 *
 * @author Venilton FalvoJr
 */
public class PermissaoController {

    private static PermissaoController instancia;

    public static PermissaoController getInstancia() {
        if (PermissaoController.instancia == null) {
            PermissaoController.instancia = new PermissaoController();
        }
        return PermissaoController.instancia;
    }

    private PermissaoController() {
        super();
    }

    public List<Permissao> listar() throws ExcecaoNegocial {
        try {
            return PermissaoDao.getInstancia().listar();
        } catch (SQLException sqlException) {
            throw new ExcecaoNegocial(Mensagens.ERRO_SQL, sqlException);
        } catch (Exception exception) {
            String mensagem = String.format(Mensagens.ERRO_INESPERADO, "Permissão");
            throw new ExcecaoNegocial(mensagem, exception);
        }
    }
}
