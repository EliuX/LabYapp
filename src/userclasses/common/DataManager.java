/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses.common;

import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Vector;
import userclasses.ExamsModel;

/**
 *
 * @author EliuX
 */
public class DataManager {

    Hashtable response = new Hashtable();
    JSONParser parser = new JSONParser();
    Vector<Hashtable<String, String>> selection;
    Hashtable<String, Object> last_request;
    static Resources res;
    private static final DataManager INSTANCE = new DataManager();

    private DataManager() {
        last_request = new Hashtable<String, Object>();
        resetSelection();
    }

    static public DataManager getInstance() {
        return INSTANCE;
    }

    static public DataManager getInstance(Resources resources) {
        res = resources;
        return INSTANCE;
    }

    public Vector getData(String filename) {
        if (response == null || response.isEmpty()) {
            try {
                InputStream istream = res.getData(filename);
                InputStreamReader reader = new InputStreamReader(istream);
                response = parser.parse(reader);
            } catch (IOException ex) {
                Log.e(ex);
            }
        }
        return (Vector) response.get("root");
    }

    /**
     * Switchea una seleccion del modelo de datos
     *
     * @param exam Elementos seleccionado instancia de Hashtable
     * @param selected TRUE | FALSE Si se selecciono el elemento
     */
    public void toogleSelected(Hashtable<String, String> exam, Boolean selected) {
        if (selected) //Seleccionado
        {
            if (!selection.contains(exam)) {
                selection.add(exam);
            }
        } else {      //Deseleccionado
            if (selection.contains(exam)) {
                selection.remove(exam);
            }
        }
        exam.put(ExamsModel.FIELD_SELECTION, selected.toString());
    }

    synchronized public void resetSelection() {
        if (selection == null) {
            selection = new Vector<Hashtable<String, String>>();
        } else {
            for (Hashtable<String, String> exam : selection) {
                exam.put(ExamsModel.FIELD_SELECTION, Boolean.FALSE.toString());
            }
            selection.clear();
        }
    }

    public boolean hasSelection() {
        return (selection != null && selection.size() > 0);
    }

    /**
     * Cantidad de examenes seleccionados
     *
     * @return cantidad de examenes seleccionados
     */
    public Vector<Hashtable<String, String>> getSelection() {
        return selection;
    }

    public int getCountOfMoney() {
        int count_money = 0;
        for (Hashtable<String, String> exam : selection) {
            String price = exam.get(ExamsModel.FIELD_PRICE);
            count_money += Float.valueOf(price.substring(1));
        }
        return count_money;
    } 
    
    public void setSuccellFullRequest(Hashtable<String, Object> request){
        last_request = request;
    }
    
    public Hashtable<String, Object> getSuccellFullRequest(){
        return last_request;
    }
    
    public boolean isEmpty(Object value){
        return value.toString().length()<1;
    }
}
