/**
 * Your application code goes here
 */
package userclasses;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import generated.StateMachineBase;
import com.codename1.ui.util.Resources;
import java.util.Vector;
import userclasses.common.DataManager;

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
    SpanLabel lblstatus, lblmoney; 
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
        Vector vData = DataManager.getInstance(res).getData("exams.json");
        examsModel = new DefaultListModel(vData);
        proxyModel = new ExamsModel(examsModel);
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
        lblstatus = findStatusExams(f);
        onDataSelectionChange();
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
            listMain = super.findMultiList();
        }
        return listMain;
    }

    public SpanLabel findStatusExams() {
        if (lblstatus == null) {
            lblstatus = super.findStatusExams();
        }
        return lblstatus;
    }

    public SpanLabel findStatusMoney() {
        if (lblmoney == null) {
            lblmoney = super.findStatusMoney();
        }
        return lblmoney;
    }

    @Override
    protected String getFirstFormName() {
        return "MainSplash";
    }

    @Override
    protected void onMain_MultiListAction(Component c, ActionEvent event) {
        if (event.getSource() != null) {
            DataManager.getInstance().toogleSelected(proxyModel.getItemSelected());
            onDataSelectionChange();
        }
    }

    @Override
    protected void onHomeView_BtnSolicitarAction(Component c, ActionEvent event) {
        Dialog.show("Exámenes de laboratorio", "Seleccione 1 o más exámenes que desee hacerse en el laboratorio de CONSUSALUD\n¡Todas las visitas a domicilio son GRATIS!", "Entiendo", null);
        showForm("Main", null);
    }

    /**
     * TODO Usar hilos aqui: Esto puede mostrarse con delays al refresh del
     * sistema
     */
    protected void onDataSelectionChange() {
        SpanLabel lblExams = findStatusExams();
        SpanLabel lblMoney = findStatusMoney();
        int count_exams = DataManager.getInstance().getSelectedCount();
        Container footer = findFooterBar();
        if (footer != null) {
            footer.setVisible(count_exams > 0);
        }
        if (lblExams != null) {
            lblExams.setText(String.valueOf(count_exams));
        }
        if (lblMoney != null) {
            lblMoney.setText("$" + DataManager.getInstance().getCountOfMoney());
        }
    }
}
