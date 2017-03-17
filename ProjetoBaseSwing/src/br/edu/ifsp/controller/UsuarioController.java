package br.edu.ifsp.controller;

import br.edu.ifsp.dao.UsuarioDao;
import br.edu.ifsp.model.Permissao;
import br.edu.ifsp.model.Usuario;
import br.edu.ifsp.util.ExcecaoNegocial;
import br.edu.ifsp.util.Mensagens;
import java.sql.SQLException;

/**
 * Responsável pelas regras de negócio (entidade Usuario) e padronização das
 * exceções.
 *
 * @author Venilton FalvoJr
 */
public class UsuarioController {

    private static UsuarioController instancia;

    public static UsuarioController getInstancia() {
        if (UsuarioController.instancia == null) {
            UsuarioController.instancia = new UsuarioController();
        }
        return UsuarioController.instancia;
    }

    private UsuarioController() {
        super();
    }

    public void inserirCadastro(Usuario usuario) throws ExcecaoNegocial {
        if (validarObrigatorios(usuario)) {
            try {
                UsuarioDao.getInstancia().inserir(usuario);
            } catch (SQLException sqlException) {
                throw new ExcecaoNegocial(Mensagens.ERRO_SQL, sqlException);
            } catch (Exception exception) {
                String mensagem = String.format(Mensagens.ERRO_INESPERADO, "Usuário");
                throw new ExcecaoNegocial(mensagem, exception);
            }
        } else {
            throw new ExcecaoNegocial(Mensagens.ERRO_CAMPOS_OBRIGATORIOS);
        }
    }

    public void autenticar(Usuario usuario) throws ExcecaoNegocial {
        boolean isEmailValido = !usuario.getEmail().isEmpty();
        boolean isSenhaValida = !usuario.getSenha().isEmpty();
        if (isEmailValido && isSenhaValida) {
            try {
                boolean isAutenticado = UsuarioDao.getInstancia().autenticar(usuario);
                if (!isAutenticado) {
                    throw new ExcecaoNegocial(Mensagens.ERRO_AUTENTICACAO);
                }
            } catch (ExcecaoNegocial excecaoNegocial) {
                throw excecaoNegocial;
            } catch (SQLException sqlException) {
                throw new ExcecaoNegocial(Mensagens.ERRO_SQL, sqlException);
            } catch (Exception exception) {
                String mensagem = String.format(Mensagens.ERRO_INESPERADO, "Usuário");
                throw new ExcecaoNegocial(mensagem, exception);
            }
        } else {
            throw new ExcecaoNegocial(Mensagens.ERRO_CAMPOS_OBRIGATORIOS);
        }
    }

    private boolean validarObrigatorios(Usuario usuario) {
        boolean retorno = !usuario.getEmail().trim().isEmpty()
                && !usuario.getSenha().trim().isEmpty()
                && usuario.getPermissao() != null;
        return retorno;
    }
}
