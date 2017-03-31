package br.edu.ifsp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Classe com metodos uteis para o tipo Date.
 * 
 * @author falvojr
 */
public final class DateUtil {

    public static final String PADRAO_DATA = "dd/MM/yyyy";
    public static final Locale BRASIL = new Locale("pt", "BR");
    
    private DateUtil() {
        super();
    }
    
    public static boolean validarData(String data) { 
        boolean ehValida;
        try {
            new SimpleDateFormat(PADRAO_DATA, BRASIL).parse(data);
            ehValida = true;
        } catch (ParseException ex) {
            ehValida = false;
        }
        return ehValida;
    }
}
