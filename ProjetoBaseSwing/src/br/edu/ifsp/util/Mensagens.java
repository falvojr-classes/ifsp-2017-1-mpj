package br.edu.ifsp.util;

import java.awt.Component;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
    public static String ERRO_JR = "Ocorreu um erro no JasperReports.";
    
    public static String SUCESSO_AUTENTICACAO = "Usuario autenticado com sucesso!";
    public static String SUCESSO_USUARIO = "Usuario persistido com sucesso!";
    public static String SUCESSO_EXCLUSAO_USUARIO = "Usuario removido com sucesso!";
    public static String SUCESSO_JR = "Relatório gerado com sucesso! Verifique a pasta relatorios.";
    
    private Mensagens() {
        super();
    }
    
    private static final Log LOGGER = LogFactory.getLog(Mensagens.class);
    
    public static void mostrarErro(Component tela, ExcecaoNegocial excecao) {
        if (excecao.getCause() != null) {
            Mensagens.LOGGER.debug(excecao.getMessage(), excecao.getCause());
        }
        JOptionPane.showMessageDialog(tela, excecao.getMessage(), "Mensagem", JOptionPane.ERROR_MESSAGE);
    }
}
