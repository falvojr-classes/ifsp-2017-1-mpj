package br.edu.ifsp.view;

import br.edu.ifsp.model.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Modelo responsável pela listagem de elementos na JTable de Usuários.
 * 
 * @author falvojr
 * 
 * @see http://stackoverflow.com/a/20527374/3072570
 */
public class UsuarioTableModel extends AbstractTableModel {

    private List<Usuario> usuarios;
    private final String[] colunas;

    public UsuarioTableModel(List<Usuario> usuarios) {
        this.colunas = new String[] { "Id", "Email", "Permissão", "Status" };
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Usuario usuario = usuarios.get(linha);
        switch(coluna) {
          case 0: 
              return usuario.getId();
          case 1: 
              return usuario.getEmail();
          case 2: 
              return usuario.getPermissao().getDescricao();
          case 3: 
              return usuario.isAtivo() ? "Ativo" : "Inativo";    
          default: 
              return null;
        }
    }
    
    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna] ;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (usuarios.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }
}
