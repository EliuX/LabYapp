/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.ui.list.ListModel;
import java.util.Hashtable;
import userclasses.common.FilterProxyListModel;

/**
 *
 * @author EliuX
 */
/**
 * Model for the exams list!
 */
public class ExamsModel extends FilterProxyListModel {

    //Fields� constants
    static public String FIELD_PRICE = "price";
    static public String FIELD_FULLNAME = "fullname";
    static public String FIELD_FULLNAME_CACHE = "fullname_cache";
    static public String FIELD_FREQUENCY = "freq";
    static public String FIELD_SELECTION = "selected";
    boolean normalized = false;

    public ExamsModel(ListModel model) {
        super(model);
    }

    @Override
    public String ItemtoString(Object item) {
        Hashtable<String, String> exam = ((Hashtable<String, String>) item);
        if (normalized) {
            return exam.get(FIELD_FULLNAME_CACHE);
        } else {    //Sino lo calculo y cacheo la primera vez
            String caption = Utils.selector(exam.get(FIELD_FULLNAME));
            exam.put(FIELD_FULLNAME_CACHE, caption);
            return caption;
        }
    }

    Hashtable<String, String> getItemSelected() {
        return (Hashtable<String, String>) getItemAt(getSelectedIndex());
    }

    @Override
    public void filter(String str) {
        super.filter(Utils.selector(str));
        normalized = true;  //Despues todo esta cacheado
    }
}
