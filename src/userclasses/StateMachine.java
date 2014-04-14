/**
 * Your application code goes here
 */
package userclasses;
  
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.list.DefaultListModel; 
import com.codename1.ui.list.ListModel;
import com.codename1.ui.list.MultiList;
import generated.StateMachineBase;
import com.codename1.ui.util.Resources;
import java.util.Hashtable;
import java.util.Vector;
import userclasses.common.DataManager;
import userclasses.common.FilterProxyListModel;

/**
 *
 * @author Your name here
 */
public class StateMachine extends StateMachineBase {

    DefaultListModel examsModel;
    ExamsModel proxyModel;
    TextField btnSearch = null;
    MultiList listMain = null;
    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }

    /**
     * this method should be used to initialize variables instead of the
     * constructor/class scope to avoid race conditions
     */
    @Override
    protected void initVars(Resources res) {
        Hashtable<String, Vector> data = DataManager.getInstance(res).getData("exams.json");
        Vector vData = data.get("root");
        examsModel = new DefaultListModel(vData);
        proxyModel = new ExamsModel(examsModel);
    } 

    @Override
    protected boolean initListModelMultiList(List cmp) {
        cmp.setModel(examsModel);
        return true;
    }  
    
    @Override
    protected void postMain(Form f) {
        MultiList m = findMultiList(); 
        findTxtSearch().addDataChangeListener(new DataChangedListener() {
           public void dataChanged(int type, int index) {
               proxyModel.filter(findTxtSearch().getText()); 
           }
        });
    }

    @Override
    public TextField findTxtSearch() {
        if(btnSearch==null){
            btnSearch = super.findTxtSearch(); //To change body of generated methods, choose Tools | Templates.  
        }
         return btnSearch;
    }

    @Override
    public MultiList findMultiList() { 
         if(listMain==null){
            listMain = super.findMultiList(); //To change body of generated methods, choose Tools | Templates.  
        }
         return listMain;        
    }
    
    

    /**
     * Model for the exams list!
     */
    private static class ExamsModel extends FilterProxyListModel{
        public ExamsModel(ListModel model) {
            super(model);
        }

        @Override
        public String ItemtoString(Object item) {
            return ((Hashtable<String,String>) item).get("fullname"); 
        }
    }

    @Override
    protected void onMain_MultiListAction(Component c, ActionEvent event) {
        if(event.getSource()!=null){
            
        }
    } 
   
}
