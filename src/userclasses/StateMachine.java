/**
 * Your application code goes here
 */
package userclasses;
 
import com.codename1.io.Log;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.list.MultiList;
import generated.StateMachineBase;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import sun.security.pkcs11.wrapper.Constants;
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
    Command aceptExam;
    Image iconExam;

    public StateMachine(String resFile) {
        super(resFile);
    }

    /**
     * this method should be used to initialize variables instead of the
     * constructor/class scope to avoid race conditions
     *
     * @param res
     */
    @Override
    protected void initVars(Resources res) {
        Hashtable<String, Vector> data = DataManager.getInstance(res).getData("exams.json");
        Vector vData = data.get("root");
        examsModel = new DefaultListModel(vData);
        proxyModel = new ExamsModel(examsModel);
        try {
            iconExam = Image.createImage("/exam.png");
        } catch (IOException ex) {
            Log.e(ex);
        }
    }

    @Override
    protected boolean initListModelMultiList(List cmp) {
        cmp.setModel(proxyModel);
        return true;
    }

    @Override
    protected void postMain(Form f) {
        findTxtSearch(f).addDataChangeListener(new DataChangedListener() {
            public void dataChanged(int type, int index) {
                Display.getInstance().invokeAndBlock(new Runnable() {
                    public void run() {
                        proxyModel.filter(findTxtSearch().getText());
                    }
                });
            }
        });
    }

    @Override
    protected void onCreateMain() {

    }

    @Override
    public TextField findTxtSearch() {
        if (btnSearch == null) {
            btnSearch = super.findTxtSearch(); //To change body of generated methods, choose Tools | Templates.  
        }
        return btnSearch;
    }

    @Override
    public MultiList findMultiList() {
        if (listMain == null) {
            listMain = super.findMultiList(); //To change body of generated methods, choose Tools | Templates.  
        }
        return listMain;
    }

    @Override
    protected String getFirstFormName() {
        return "MainSplash";
    }     

    @Override
    protected void onMain_MultiListAction(Component c, ActionEvent event) {
        if (event.getSource() != null) {
            Hashtable<String, String> field = proxyModel.getItemSelected();
            String txt = field.get(ExamsModel.FIELD_FULLNAME) 
                    + Constants.NEWLINE + "Precio:" + Constants.INDENT + Constants.INDENT + Constants.INDENT
                    + field.get(ExamsModel.FIELD_PRICE) + " " + Utils.modena_str;
            String freq = field.get(ExamsModel.FIELD_FREQUENCY);
            if (freq != null && freq.trim().length() > 0) {
                txt = txt.concat(Constants.NEWLINE + "Frecuencia:" + Constants.INDENT + field.get(ExamsModel.FIELD_FREQUENCY));
            }
            boolean accepted = Dialog.show("Examen de laboratorio", txt, BACK_COMMAND_ID, iconExam, "Incluir", "Cancelar");
            findMultiList().getSelectedButton().setSelected(accepted);
        }
    }


    @Override
    protected void onHomeView_BtnSolicitarAction(Component c, ActionEvent event) {
        showForm("Main", null);
    }  
    
    /**
     * Model for the exams list!
     */
    private static class ExamsModel extends FilterProxyListModel {
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
}
