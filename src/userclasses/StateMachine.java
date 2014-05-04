/**
 * Your application code goes here
 */
package userclasses;

import com.codename1.maps.MapComponent;
import com.codename1.messaging.Message;
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
        Vector vData = DataManager.getInstance(res).getData(ExamsModel.EXAMS_FILE);
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
        return "MainSplash";
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
        String txt = field.get(ExamsModel.FIELD_FULLNAME)
                + Constants.NEWLINE + "Precio:" + Constants.INDENT + Constants.INDENT + Constants.INDENT
                + Utils.print_money(field.get(ExamsModel.FIELD_PRICE));
        String price_afiliado = field.get(ExamsModel.FIELD_PRICE_AFILIATED);
        if (!DataManager.isEmpty(price_afiliado)) {
            txt = txt.concat(Constants.NEWLINE + "Precio de afiliados:" + Constants.INDENT + Utils.print_money(price_afiliado));
        }
        String freq = field.get(ExamsModel.FIELD_FREQUENCY);
        if (!DataManager.isEmpty(freq)) {
            txt = txt.concat(Constants.NEWLINE + "Frecuencia:" + Constants.INDENT + freq);
        }
        return Dialog.show("Examen de laboratorio", txt, BACK_COMMAND_ID, null, "Sí", "No");
    }

    @Override
    protected void onHomeView_BtnSolicitarAction(Component c, ActionEvent event) {
        Dialog.show("Exámenes de laboratorio", "Seleccione 1 o más exámenes que desee hacerse en el laboratorio de CONSUSALUD\n¡Todas las visitas a domicilio son GRATIS y sin prepago!", "Entiendo", null);
        showForm("Main", null);
    }

    /**
     * TODO Usar hilos aqui: Esto puede mostrarse con delays al refresh del
     * sistema
     */
    protected void onDataSelectionChange() {
        Label lblExams = findStatusExams();
        Label lblMoney = findStatusMoney();
        int count_exams = DataManager.getInstance().getSelection().size();
        if (lblExams != null) {
            lblExams.setText(String.valueOf(count_exams));
        }
        if (lblMoney != null) {
            lblMoney.setText("$" + DataManager.getInstance().getCountOfMoney());
        }
    }

    /**
     * Shows or hides the footer
     *
     * @param visible If it's visible
     */
    protected void toogleFooter(boolean visible) {
        Container footer = findFooterBar();
        if (footer != null) {
            footer.setVisible(true);
            footer.setVisible(visible);
            footer.repaint();
            if (!visible) {
                footer.getParent().repaint();
            }
        }
    }

    @Override
    protected boolean onMainSiguiente(Command cmd) {
        if (DataManager.getInstance().hasSelection()) {
            return false;
        }
        Dialog.show("Error en la selección", "Por favor seleccione uno o más exámenes que desee hacerse en nuestro laboratorio", "Entendí", null);
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
    protected void postFormAboutConSuSalud(Form f) {
        MapComponent map = findLabMap(f);
        map.setPropertyValue("latitude", 3.415828);
        map.setPropertyValue("longitude", -76.5278743);
        map.setPropertyValue("zoom", 5);
    }

    @Override
    protected void onFormRequest_BtnEnviarAction(Component c, ActionEvent event) {
        GatherData();
        Hashtable<String, Object> request = DataManager.getInstance().getSuccellFullRequest();
        if (request.isEmpty()) {
            return;
        }
        String body = "Nombre del solicitante:" + Utils.TABLINE + request.get("fullname") + Utils.NEWLINE
                + "Teléfono del contacto:" + Utils.TABLINE + request.get("phone") + Utils.NEWLINE
                + (request.get("afiliation").equals(Boolean.FALSE) ? "No quiere" : "Quiere") + " afiliarse" + Utils.NEWLINE
                + (DataManager.isEmpty(request.get("comment")) ? "" : ("Comenta: " + Utils.NEWLINE + request.get("comment")));
        //Adjunto los servicios que desea
        Vector<Hashtable<String, String>> list = (Vector<Hashtable<String, String>>) request.get("exams");
        body += Utils.NEWSEGMENT + "Prueba(s) seleccionada(s)" + Utils.NEWLINE;
        for (Hashtable<String, String> exam : list) {
            body += "-" + exam.get(ExamsModel.FIELD_FULLNAME) + Utils.NEWLINE;
        }
        body += Utils.NEWSEGMENT + "Costo: " + Utils.print_money(String.valueOf(DataManager.getInstance().getCountOfMoney()));
        Display.getInstance().sendMessage(new String[]{Utils.ENTERPRISE_MAIL}, "Nueva solicitud de exámen para <" + request.get("fullname") + ">", new Message(body));
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
            errorsMsg += "El teléfono no tiene un valor válido." + Utils.NEWLINE;
        }
        if (DataManager.isEmpty(errorsMsg)) {
            DataManager.getInstance().setSuccellFullRequest(params);    //TODO Enviar
        } else {
            Dialog.show("Parámetros no válidos", errorsMsg, "Entiendo", null);
        }
    }

    @Override
    protected void postFormRequest(Form f) {
        findTotalRequestCost(f).setText("Costo: " + Utils.print_money(String.valueOf(DataManager.getInstance().getCountOfMoney())));
    }
}
