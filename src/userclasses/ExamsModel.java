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
    //Fields´ constants
    static public String FIELD_PRICE = "price";
    static public String FIELD_FULLNAME = "fullname";
    static public String FIELD_FREQUENCY = "freq";

    public ExamsModel(ListModel model) {
        super(model);  
    }

    @Override
    public String ItemtoString(Object item) {
        return ((Hashtable<String, String>) item).get("fullname");
    }

    Hashtable<String, String> getItemSelected() {
        return (Hashtable<String, String>) getItemAt(getSelectedIndex());
    }
} 
