package userclasses;

import com.codename1.io.Storage;
import java.util.Date;

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
    static public boolean is_production = false;
    static public int days_without_update_from_cloud = 2;   //Cantidad de dias validos sin actualizar desde la nube
    //Datos de la empresa
    static public String ENTERPRISE_MAIL = "clientes@consusalud.co";
    static public String modena_str = "pesos";
    //Listado de Examenes
    static public String NO_EXAMS_SELECTED = "Seleccione 1 o m�s examenes que desee hacerse";
    static public String NO_EXAMS = "0";
    static public String EXAMS_SELECTED = "ex�men(es) seleccionados";
    static public String NEWLINE = "\n";
    static public String TABLINE = "\t";
    static public String INDENT = TABLINE;
    static public String NEWSEGMENT = NEWLINE + "----------------------------------------" + NEWLINE;
    static public String URL_MAP_LOCATION = "https://www.google.com/maps?t=m&sll=3.415978,-76.528115&sspn=0.0082251,0.0109864&q=Consusalud+Servicio+Integral+de+Salud&cid=0xa5f3dcc37be13776&output=classic&dg=ntvo";
    static public String ERR_TITLE = "Ooh!";
    static String print_money(String value) {
       return value;
    }
    
    public static Object getDate(){
        Date fecha = new Date();
        return fecha.getTime();
    }
    
    /**
     * Devuelve la diferencia en dias de hoy al dia indicado
     * @param dia Dia indicado de referencia
     * @return cantidad de dias de diferencia
     */
    static public int DiffInDays(long dia){ 
        Date fecha = new Date();
        long dif = (fecha.getTime() - (dia)); 
        return (int)(dif / (24 * 60 * 60 * 1000));
    }
    
    /**
     * Hay que actualizar desde la nube 
     * @return TRUE | FALSE si la app debera conectarse a internet a actualizar los datos
     */
    static public boolean isNecessaryUpdateFromCloud(){
        Object last_update_date = Storage.getInstance().readObject(String.valueOf(ExamsModel.EXAMS_STORAGE_LAST_UPDATE));
        return last_update_date == null || DiffInDays((Long)last_update_date) >=days_without_update_from_cloud;
    }
}
