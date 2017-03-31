package br.edu.ifsp.util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Classe que centraliza as mensagens do sistema e prove métodos úteis.
 * 
 * @author Venilton FalvoJr
 */
public class Mensagens {
    public static String ERRO_CAMPOS_OBRIGATORIOS = "Todos os campos devem ser preenchidos.";
    public static String ERRO_SQL = "Ocorreu um erro na consulta ao banco de dados";
    public static String ERRO_INESPERADO = "Ocorreu um erro inesperado (%s).";
    public static String ERRO_SENHAS = "As senhas devem ser iguais.";
    public static String ERRO_AUTENTICACAO = "Usuario ou Senha inválidos.";
    
    public static String SUCESSO_AUTENTICACAO = "Usuario autenticado com sucesso!";
    public static String SUCESSO_USUARIO = "Usuario persistido com sucesso!";
    public static String SUCESSO_EXCLUSAO_USUARIO = "Usuario removido com sucesso!";
    
    private Mensagens() {
        super();
    }
    
    public static void mostrarErro(Component tela, ExcecaoNegocial excecao) {
        JOptionPane.showMessageDialog(tela, excecao.getMessage(),
                "Mensagem", JOptionPane.ERROR_MESSAGE);
    }
}
