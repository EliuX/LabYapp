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
    //Datos de la empresa
    static public String ENTERPRISE_MAIL = "consusaludempresarial@gmail.com";
    //Listado de Examenes
    static public String NO_EXAMS_SELECTED = "Seleccione 1 o m�s examenes que desee hacerse";
    static public String NO_EXAMS = "0";
    static public String EXAMS_SELECTED = "ex�men(es) seleccionados";
    static public String NEWLINE = "\n";
    static public String TABLINE = "\t";
    static public String NEWSEGMENT = NEWLINE + "----------------------------------------" + NEWLINE;

    static String print_money(String value) {
       return value + " " + modena_str;
    }
}
