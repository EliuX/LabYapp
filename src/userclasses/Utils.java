package userclasses;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author EliuX
 */
public class Utils {

    static public String modena_str = "USD";
    //Listado de Examenes
    static public String NO_EXAMS_SELECTED = "Seleccione 1 o m�s examenes que desee hacerse";
    static public String NO_EXAMS = "0";
    static public String EXAMS_SELECTED = "ex�men(es) seleccionados";
    static public String NEWLINE = "\n";
    static public String TABLINE = "\t";
    static public String NEWSEGMENT = NEWLINE + "----------------------------------------" + NEWLINE;

    /**
     * Elimina los prefijos, acentos y dobles espacios de la cadena que se
     * especifica
     *
     * @param nombre
     */
    public static String selector(String nombre) {
        String xnombre = nombre.toUpperCase();
        xnombre = xnombre.replace('�', 'N');
        xnombre = xnombre.replace('�', 'A');
        xnombre = xnombre.replace('�', 'E');
        xnombre = xnombre.replace('�', 'I');
        xnombre = xnombre.replace('�', 'O');
        xnombre = xnombre.replace('�', 'U');
        return xnombre;
    }
}
