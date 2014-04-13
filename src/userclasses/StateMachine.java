/**
 * Your application code goes here
 */

package userclasses;

import com.codename1.ui.List;
import com.codename1.ui.list.DefaultListModel;
import generated.StateMachineBase;
import com.codename1.ui.util.Resources;
import com.consusalud.labyapp.DataManager;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Your name here
 */
public class StateMachine extends StateMachineBase {
    DefaultListModel examsModel;
    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }
    
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    @Override
    protected void initVars(Resources res) {
        Hashtable<String,Vector> data = DataManager.getInstance(res).getData("exams.json");
        Vector vData = data.get("root");
        examsModel = new com.codename1.ui.list.DefaultListModel(vData);       
    }


    @Override
    protected boolean initListModelMultiList(List cmp) {
        cmp.setModel(examsModel);
        return true;
    }
}
