package br.edu.ifsp.util;

/**
 * Exceção customizada para manipulação de mensagens de negócio.
 * 
 * @author Venilton FalvoJr
 */
public class ExcecaoNegocial extends Exception {

    public ExcecaoNegocial(String message) {
        super(message);
    }

    public ExcecaoNegocial(String message, Throwable cause) {
        super(message, cause);
    }  
    
}
