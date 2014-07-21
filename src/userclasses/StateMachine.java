/**
 * Your application code goes here
 */
package userclasses;

import com.codename1.components.WebBrowser;
import com.codename1.messaging.Message;
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
import com.codename1.ui.util.Resources;
import generated.StateMachineBase;
import java.util.Hashtable;
import java.util.Vector; 
import sun.security.pkcs11.wrapper.Constants;
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
    /**
     * Versiones locales de recursos: Optimizacion
     */
    Label lblstatus, lblmoney;
    Container footerBar;
    private String Hashtable;
    private boolean isLoaded = false;

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
        Vector vData = DataManager.getInstance(res).getData();
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
        lblmoney = findStatusMoney(f);
        footerBar = findFooterBar(f);
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

    @Override
    public Label findStatusExams() {
        if (lblstatus == null) {
            lblstatus = super.findStatusExams();
        }
        return lblstatus;
    }

    @Override
    public Container findFooterBar() {
        if (footerBar == null) {
            footerBar = super.findFooterBar();
        }
        return footerBar;
    }

    @Override
    public Label findStatusMoney() {
        if (lblmoney == null) {
            lblmoney = super.findStatusMoney();
        }
        return lblmoney;
    }

    @Override
    protected String getFirstFormName() {
        return "HomeView";
    }

    @Override
    protected void onMain_MultiListAction(Component c, ActionEvent event) {
        if (event.getSource() != null) {
            Hashtable<String, String> field = proxyModel.getItemSelected();
            Boolean accepted = ShowDataExam(field);
            DataManager.getInstance().toogleSelected(field, accepted);
            onDataSelectionChange();
        }
        event.consume();
    }

    /**
     * Muestra un mensaje con los datos de un examen
     *
     * @param field Datos del examen
     * @return
     */
    public Boolean ShowDataExam(Hashtable<String, String> field) {
        String txt = field.get(ExamsModel.FIELD_FULLNAME);  //Asi empieza
        String precio = field.get(ExamsModel.FIELD_PRICE);
        if (!DataManager.isEmpty(precio)){
            txt = txt.concat(Constants.NEWLINE + "Precio:"  + Constants.INDENT + Constants.INDENT + Utils.print_money(precio));   
        }else{
            txt = txt.concat(Constants.NEWLINE + "El precio es variable: Contactar por tel�fono" + Constants.NEWLINE );
        }
        String price_afiliado = field.get(ExamsModel.FIELD_PRICE_AFILIATED);
        if (!DataManager.isEmpty(price_afiliado)) {
            txt = txt.concat(Constants.NEWLINE + "Precio de afiliados:" + Constants.INDENT + Utils.print_money(price_afiliado));
        }
        String freq = field.get(ExamsModel.FIELD_FREQUENCY);
        if (!DataManager.isEmpty(freq)) {
            txt = txt.concat(Constants.NEWLINE + "D�as de proceso:" + Constants.INDENT + freq);
        }
        return Dialog.show("Examen de laboratorio", txt, BACK_COMMAND_ID, null, "S�", "No");
    }

    /**
     * TODO Usar hilos aqui: Esto puede mostrarse con delays al refresh del
     * sistema
     */
    protected void onDataSelectionChange() {
        Display.getInstance().callSerially(new Runnable() {
            public void run() {
                Label lblExams = findStatusExams();
                Label lblMoney = findStatusMoney();
                int count_exams = DataManager.getInstance().getSelection().size();
                int count_exams_non_payable = DataManager.getInstance().getSelectionNonPayable().size();
                if (lblExams != null) {
                    lblExams.setText(String.valueOf(count_exams));
                }
                if (lblMoney != null) {
                    lblMoney.setText((count_exams_non_payable>0 ? "> " : "") + "$" + DataManager.getInstance().getCountOfMoney());
                }
                toogleFooter(count_exams > 0);
            }
        });
    }

    /**
     * Shows or hides the footer
     *
     * @param visible If it's visible
     */
    protected void toogleFooter(boolean visible) {
        final Container footer = findFooterBar();
        Container father = footer.getParent();
        if (visible && !footer.isVisible()) {
            footer.setPreferredSize(null);
            father.animateLayout(800);
        } else if (!visible && footer.isVisible()) {
            footer.setPreferredH(0);
            father.animateLayoutAndWait(800);
        } 
        footer.setVisible(visible);
    }

    @Override
    protected boolean onMainSiguiente() {
        if (DataManager.getInstance().hasSelection()) {
            return false;
        } 
        Dialog.show("Error en la selecci�n", "Por favor seleccione uno o m�s ex�menes que desee hacerse en nuestro laboratorio", "Entend�", null);
        return true;
    }

    @Override
    protected void onMain_BtnResetAction(Component c, ActionEvent event) {
        event.consume();
        DataManager.getInstance().resetSelection();
        findMultiList().repaint();
        onDataSelectionChange();
    }

    @Override
    protected boolean initListModelListSelection(List cmp) {
        Vector<Hashtable<String, String>> list = DataManager.getInstance().getSelection();
        for (Hashtable<String, String> exam : list) {
            exam.put("LblFreq", "Frecuencia");
            exam.put("LblPrice", "Precio no afiliado");
        }
        cmp.setModel(new DefaultListModel(DataManager.getInstance().getSelection()));
        return true;
    }

    @Override
    protected void onFormRequest_BtnEnviarAction(Component c, ActionEvent event) {
        GatherData();
        Hashtable<String, Object> request = DataManager.getInstance().getSuccellFullRequest();
        if (request.isEmpty()) {
            return;
        }
        String body = "Nombre del solicitante:" + Utils.TABLINE + request.get("fullname") + Utils.NEWLINE
                + "Tel�fono del contacto:" + Utils.TABLINE + request.get("phone") + Utils.NEWLINE
                + (request.get("afiliation").equals(Boolean.FALSE) ? "No quiere" : "Quiere") + " afiliarse" + Utils.NEWLINE
                + (DataManager.isEmpty(request.get("comment")) ? "" : ("Comenta: " + Utils.NEWLINE + request.get("comment")));
        //Adjunto los servicios que desea
        Vector<Hashtable<String, String>> list = (Vector<Hashtable<String, String>>) request.get("exams");
        body += Utils.NEWSEGMENT + "Prueba(s) seleccionada(s)" + Utils.NEWLINE;
        for (Hashtable<String, String> exam : list) {
            body += "-" + exam.get(ExamsModel.FIELD_FULLNAME) + Utils.NEWLINE;
        }
        body += Utils.NEWSEGMENT + "Costo: " + Utils.print_money(String.valueOf(DataManager.getInstance().getCountOfMoney()));
        Display.getInstance().sendMessage(new String[]{Utils.ENTERPRISE_MAIL}, "Nueva solicitud de ex�men para <" + request.get("fullname") + ">", new Message(body));
    }

    public void GatherData() {
        Hashtable<String, Object> params = new Hashtable<String, Object>();
        String fullname = findUsername().getText();
        String phone = findUserphone().getText();
        String comment = findUsercomment().getText();
        String errorsMsg = "";
        //Introduzco los valores
        params.put("fullname", fullname);
        params.put("phone", findUserphone().getText());
        params.put("afiliation", findUserafiliation().getBoundPropertyValue("selected"));
        params.put("comment", comment);
        params.put("exams", DataManager.getInstance().getSelection());
        //Espero que todos los valores esten presentes 
        if (DataManager.isEmpty(params.get("fullname")) || DataManager.isEmpty(params.get("phone"))) {
            errorsMsg += "Faltan datos por especificar" + Utils.NEWLINE;
        }

        if (fullname.lastIndexOf(" ") < 3) {
            errorsMsg += "Por favor introduzca su nombre completo correctamente." + Utils.NEWLINE;
        }
        if (!findUserphone().validChar(phone)) {
            errorsMsg += "El tel�fono no tiene un valor v�lido." + Utils.NEWLINE;
        }
        if (DataManager.isEmpty(errorsMsg)) {
            DataManager.getInstance().setSuccellFullRequest(params);    //TODO Enviar
        } else {
            Dialog.show("Par�metros no v�lidos", errorsMsg, "Entiendo", null);
        }
    }

    @Override
    protected void postFormRequest(Form f) {
        Vector<Hashtable<String, String>> selectionNonPayable = DataManager.getInstance().getSelectionNonPayable();
        String prefix = selectionNonPayable.size() > 0 ? "> " : "";
        findTotalRequestCost(f).setText("Costo: " + prefix + Utils.print_money(String.valueOf(DataManager.getInstance().getCountOfMoney())) + " " + Utils.modena_str);
        findLblNP(f).setVisible(selectionNonPayable.size() > 0); //Si hay no pagables, entonces que se muestre
    } 
}
