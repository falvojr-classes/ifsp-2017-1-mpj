package br.edu.ifsp.dao;

import br.edu.ifsp.model.Permissao;
import br.edu.ifsp.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de persistência para a entidade Usuario. Essa classe implementa os
 * padrões Singleton e Strategy.
 *
 * @author Venilton FalvoJr
 *
 * @since 11/10/2016
 */
public class UsuarioDao extends BaseDao implements IUsuarioDao {

    private static UsuarioDao instancia;

    public static UsuarioDao getInstancia() {
        if (UsuarioDao.instancia == null) {
            UsuarioDao.instancia = new UsuarioDao();
        }
        return UsuarioDao.instancia;
    }

    private UsuarioDao() {
        super();
    }

    @Override
    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (email, senha, ativo, id_permissao) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = super.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, usuario.getEmail());
        stmt.setString(2, usuario.getSenha());
        stmt.setBoolean(3, usuario.isAtivo());
        stmt.setLong(4, usuario.getPermissao().getId());
        stmt.execute();

        usuario.setId(super.getChaveGerada(stmt));

        stmt.close();
    }

    @Override
    public void alterar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET email=?, senha=?, ativo=?, id_permissao=? WHERE id=?";
        PreparedStatement stmt = super.getConexao().prepareStatement(sql);
        stmt.setString(1, usuario.getEmail());
        stmt.setString(2, usuario.getSenha());
        stmt.setBoolean(3, usuario.isAtivo());
        stmt.setLong(4, usuario.getPermissao().getId());
        stmt.setLong(5, usuario.getId());
        stmt.execute();
        stmt.close();
    }

        @Override
    public void excluir(Usuario usuario) throws SQLException {
        
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "select u.id, u.email, u.ativo, u.id_permissao, p.descricao as desc_permissao"
                + " from usuario u"
                + " inner join permissao p on u.id_permissao = p.id";
        PreparedStatement stmt = super.getConexao().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("id"));
            usuario.setEmail(rs.getString("email"));
            usuario.setAtivo(rs.getInt("ativo") == 1);
            Permissao permissao = new Permissao();
            permissao.setId(rs.getLong("id_permissao"));
            permissao.setDescricao(rs.getString("desc_permissao"));
            usuario.setPermissao(permissao);

            usuarios.add(usuario);
        }
        rs.close();
        stmt.close();
        return usuarios;
    }
    
    @Override
    public boolean autenticar(Usuario usuario) throws SQLException {
        boolean retorno = false;
        String sql = "select count(*) from usuario where email = ? and senha = ?";
        PreparedStatement stmt = super.getConexao().prepareStatement(sql);
        stmt.setString(1, usuario.getEmail());
        stmt.setString(2, usuario.getSenha());
        ResultSet rs = stmt.executeQuery();
        rs.next();
        Long count = rs.getLong("count(*)");
        if (count == 1) {
            retorno = true;
        }
        stmt.close();
        return retorno;
    }

}
