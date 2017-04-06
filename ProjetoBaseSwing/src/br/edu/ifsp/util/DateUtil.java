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

    public static final Locale BRASIL_LOCALE = new Locale("pt", "BR");
    public static final String BRASIL_PATTERN = "dd/MM/yyyy";
    public static final SimpleDateFormat BRASIL_FORMAT = new SimpleDateFormat(BRASIL_PATTERN, BRASIL_LOCALE);
    
    private DateUtil() {
        super();
    }
    
    public static boolean validarData(String data) { 
        boolean ehValida;
        try {
            BRASIL_FORMAT.parse(data);
            ehValida = true;
        } catch (ParseException ex) {
            ehValida = false;
        }
        return ehValida;
    }
}
