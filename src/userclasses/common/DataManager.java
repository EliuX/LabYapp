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
import java.util.ArrayList;
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
    ArrayList<Hashtable<String, String>> selection = new ArrayList<Hashtable<String, String>>();
    static Resources res;
    private static final DataManager INSTANCE = new DataManager();
     static public DataManager getInstance() {
        return INSTANCE;
    }

    static public DataManager getInstance(Resources resources) {
        res = resources;
        return INSTANCE;
    }   
    
    public Vector getData(String filename) {
        if(response==null || response.isEmpty()){
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
     * @param index
     * @return 
     */
    public ArrayList<Hashtable<String, String>> toogleSelected(Hashtable<String, String> index) {  
        if(selection.contains(index))
        {
            selection.remove(index);
        }else{
            selection.add(index);
        }
        return selection;
    }
    
    /**
     * Cantidad de examenes seleccionados
     * @return cantidad de examenes seleccionados
     */
    public int getSelectedCount(){
        return selection.size();
    }
    
    
    public int getCountOfMoney(){
        int count_money = 0; 
        for(Hashtable<String, String> exam : selection){
          String price = exam.get(ExamsModel.FIELD_PRICE);
          count_money+= Float.valueOf(price.substring(1));
        } 
        return count_money;
    }
}
