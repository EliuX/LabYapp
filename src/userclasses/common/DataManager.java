/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses.common;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;

import java.io.*;
import java.util.Hashtable;
import java.util.Vector;
import userclasses.ExamsModel;
import userclasses.Utils;

/**
 *
 * @author EliuX
 */
public class DataManager {

    Hashtable response = new Hashtable();
    JSONParser parser = new JSONParser();
    Vector<Hashtable<String, String>> selection;
    Vector<Hashtable<String, String>> selection_non_payable;    //Lo seleccionado pero que no se puede pagar
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

    public Vector getData() {
        if (response == null || response.isEmpty()) {
            /*try{
                if(Utils.is_production && Utils.isNecessaryUpdateFromCloud()){
                    Vector saved_data = getDataFromStorage();
                    if(saved_data!=null){   //TODO Comprobar fecha de ultima actualizacion para saber si los datos son buenos.
                        return saved_data;
                    }
                    //Si no tiene datos salvado entonces esta es su primera vez
                    Vector res = getDataFromCloud();
                    return res;
                }else{
                    return getDataFromFile();
                }
            }catch (IOException e){
                Dialog.show(Utils.ERR_TITLE, "No se pudo cargar los datos de los exámenes.\nRevise su conexión a internet o reinicie la aplicación por favor.", "Ok", null);
                return null;
            }*/
            return getDataFromFile();    
        }
        return (Vector) response.get("root");
    }

    public Vector getDataFromFile(){
        if (response == null || response.isEmpty()) {
            try {
                InputStream istream = res.getData(ExamsModel.EXAMS_FILE);
                InputStreamReader reader = new InputStreamReader(istream);
                response = parser.parse(reader);
            } catch (IOException ex) {
                Log.e(ex);
            }
        }
        return (Vector) response.get("root");
    }

    /**
     * Descarga los datos de la nube
     * @return
     */
    public Vector getDataFromCloud() throws IOException{
        if (response == null || response.isEmpty()) {
            ConnectionRequest request = new ConnectionRequest() {
                protected void handleException(Exception err) {
                   showConnErr();
                }
            };
            request.setUrl(ExamsModel.EXAMS_FILE_CLOUD);
            InfiniteProgress iProgress = new InfiniteProgress();
            Dialog dlg = iProgress.showInifiniteBlocking(); //Muestro el cartel de cargando
            request.setDisposeOnCompletion(dlg);
            NetworkManager.getInstance().addToQueueAndWait(request);
            byte[] cloud_response_bytes = request.getResponseData();
            if(cloud_response_bytes==null){
                showConnErr();
                return null;
            }
            
            InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(cloud_response_bytes));
            response = parser.parse(reader);
            Storage s = Storage.getInstance(); //Hago la salva pertinente en la base de datos
            s.writeObject(ExamsModel.EXAMS_STORAGE, response); //Lo cacheo para para la proxima vez
            s.writeObject(ExamsModel.EXAMS_STORAGE_LAST_UPDATE, Utils.getDate());   //Fecha de la ultima actualizacion
        }
        return (Vector) response.get("root");
    }
    
    private void showConnErr(){
        Dialog.show(Utils.ERR_TITLE, "No se puede establecer la conexion con \"consusalud.co\" ¿Esta usted conectado a Internet?\nRevise su conexión por favor", "Ok", null);       
    }

    /**
     * Devuelve los examenes almacenados en la DB interna de la App
     * @return
     */
    public Vector getDataFromStorage() throws IOException{
        Storage s = Storage.getInstance();
        response = (Hashtable)s.readObject(ExamsModel.EXAMS_STORAGE);
        if(response != null){
           return (Vector) response.get("root");
        }
        return null;
    }

    public void saveDataIntoStorage(String index,String data){
        Storage s = Storage.getInstance();
        s.writeObject(index, data);
    }

    public Object readOrCreate(String index,String... def){ 
        Object response = Storage.getInstance().readObject(index);
        if(response==null){
            if(def.length>0){
                saveDataIntoStorage(index,def[0]);
            }
            return def;
        }
        return response;
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
                if(exam.get(ExamsModel.FIELD_PRICE).length()==0){ //Entonces es un no pagable
                    toogleSelectedNonPayable(exam,true); 
                }
            }
        } else {      //Deseleccionado
            if (selection.contains(exam)) {
                selection.remove(exam);
                if(exam.get(ExamsModel.FIELD_PRICE).length()==0){ //Entonces es un no pagable y lo quito
                    toogleSelectedNonPayable(exam,false); 
                }
            }
        }
        exam.put(ExamsModel.FIELD_SELECTION, selected.toString());
    }
    
    /**
     * Similar a toogleSelected pero para los no pagables
     * @param exam
     * @param selected 
     */
    public void toogleSelectedNonPayable(Hashtable<String, String> exam, Boolean selected) {
        if (selected) //Seleccionado
        {
            if (!selection_non_payable.contains(exam)) {
                selection_non_payable.add(exam);
            }
        } else {      //Deseleccionado
            if (selection_non_payable.contains(exam)) {
                selection_non_payable.remove(exam);
            }
        } 
    }

    synchronized public void resetSelection() {
        if (selection == null) {
            selection = new Vector<Hashtable<String, String>>();
            selection_non_payable = new Vector<Hashtable<String, String>>();
        } else {
            for (Hashtable<String, String> exam : selection) {
                exam.put(ExamsModel.FIELD_SELECTION, Boolean.FALSE.toString());
            }
            selection.clear();
            selection_non_payable.clear();
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
    
    /**
     * Devuelve el Listado de los elementos seleccionables que no tienen indicado precio aun
     * @return 
     */
    public Vector<Hashtable<String, String>> getSelectionNonPayable() {
        return selection_non_payable;
    } 

    public int getCountOfMoney() {
        int count_money = 0;
        for (Hashtable<String, String> exam : selection) {
            String price = exam.get(ExamsModel.FIELD_PRICE);
            if(price.length()>1){ 
                count_money += Float.valueOf(price.substring(1));
            }     
        }
        return count_money;
    } 
    
    public void setSuccellFullRequest(Hashtable<String, Object> request){
        last_request = request;
    }
    
    public Hashtable<String, Object> getSuccellFullRequest(){
        return last_request;
    }
    

    /**
     * Elimina los prefijos, acentos y dobles espacios de la cadena que se
     * especifica
     *
     * @param nombre
     * @return La cadena sin caracteres latinos
     */
    public static String selector(String nombre) {
        String xnombre = nombre.toUpperCase();
        xnombre = xnombre.replace('Ñ', 'N');
        xnombre = xnombre.replace('Á', 'A');
        xnombre = xnombre.replace('É', 'E');
        xnombre = xnombre.replace('Í', 'I');
        xnombre = xnombre.replace('Ó', 'O');
        xnombre = xnombre.replace('Ú', 'U');
        return xnombre;
    }
    
   public static boolean isEmpty(Object value){
        String val = String.valueOf(value);
        return val==null || val.length()<1;
    }
}
