/**
 * This class contains generated code from the Codename One Designer, DO NOT MODIFY!
 * This class is designed for subclassing that way the code generator can overwrite it
 * anytime without erasing your changes which should exist in a subclass!
 * For details about this file and how it works please read this blog post:
 * http://codenameone.blogspot.com/2010/10/ui-builder-class-how-to-actually-use.html
*/
package generated;

import com.codename1.ui.*;
import com.codename1.ui.util.*;
import com.codename1.ui.plaf.*;
import java.util.Hashtable;
import com.codename1.ui.events.*;

public abstract class StateMachineBase extends UIBuilder {
    private Container aboutToShowThisContainer;
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    /**
    * @deprecated use the version that accepts a resource as an argument instead
    
**/
    protected void initVars() {}

    protected void initVars(Resources res) {}

    public StateMachineBase(Resources res, String resPath, boolean loadTheme) {
        startApp(res, resPath, loadTheme);
    }

    public Container startApp(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("MapComponent", com.codename1.maps.MapComponent.class);
        UIBuilder.registerCustomComponent("InfiniteProgress", com.codename1.components.InfiniteProgress.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("ComponentGroup", com.codename1.ui.ComponentGroup.class);
        UIBuilder.registerCustomComponent("Tabs", com.codename1.ui.Tabs.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("MultiButton", com.codename1.components.MultiButton.class);
        UIBuilder.registerCustomComponent("CheckBox", com.codename1.ui.CheckBox.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("MultiList", com.codename1.ui.list.MultiList.class);
        UIBuilder.registerCustomComponent("SpanLabel", com.codename1.components.SpanLabel.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    if(resPath.endsWith(".res")) {
                        res = Resources.open(resPath);
                        System.out.println("Warning: you should construct the state machine without the .res extension to allow theme overlays");
                    } else {
                        res = Resources.openLayered(resPath);
                    }
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        if(res != null) {
            setResourceFilePath(resPath);
            setResourceFile(res);
            initVars(res);
            return showForm(getFirstFormName(), null);
        } else {
            Form f = (Form)createContainer(resPath, getFirstFormName());
            initVars(fetchResourceFile());
            beforeShow(f);
            f.show();
            postShow(f);
            return f;
        }
    }

    protected String getFirstFormName() {
        return "Main";
    }

    public Container createWidget(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("MapComponent", com.codename1.maps.MapComponent.class);
        UIBuilder.registerCustomComponent("InfiniteProgress", com.codename1.components.InfiniteProgress.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("ComponentGroup", com.codename1.ui.ComponentGroup.class);
        UIBuilder.registerCustomComponent("Tabs", com.codename1.ui.Tabs.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("MultiButton", com.codename1.components.MultiButton.class);
        UIBuilder.registerCustomComponent("CheckBox", com.codename1.ui.CheckBox.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("MultiList", com.codename1.ui.list.MultiList.class);
        UIBuilder.registerCustomComponent("SpanLabel", com.codename1.components.SpanLabel.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    res = Resources.openLayered(resPath);
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        return createContainer(resPath, "Main");
    }

    protected void initTheme(Resources res) {
            String[] themes = res.getThemeResourceNames();
            if(themes != null && themes.length > 0) {
                UIManager.getInstance().setThemeProps(res.getTheme(themes[0]));
            }
    }

    public StateMachineBase() {
    }

    public StateMachineBase(String resPath) {
        this(null, resPath, true);
    }

    public StateMachineBase(Resources res) {
        this(res, null, true);
    }

    public StateMachineBase(String resPath, boolean loadTheme) {
        this(null, resPath, loadTheme);
    }

    public StateMachineBase(Resources res, boolean loadTheme) {
        this(res, null, loadTheme);
    }

    public com.codename1.ui.Container findContainer3(Component root) {
        return (com.codename1.ui.Container)findByName("Container3", root);
    }

    public com.codename1.ui.Container findContainer3() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container3", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container3", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findStatusMoney(Component root) {
        return (com.codename1.ui.Label)findByName("StatusMoney", root);
    }

    public com.codename1.ui.Label findStatusMoney() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("StatusMoney", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("StatusMoney", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer2(Component root) {
        return (com.codename1.ui.Container)findByName("Container2", root);
    }

    public com.codename1.ui.Container findContainer2() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container2", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container2", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer1(Component root) {
        return (com.codename1.ui.Container)findByName("Container1", root);
    }

    public com.codename1.ui.Container findContainer1() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findStatusExams(Component root) {
        return (com.codename1.ui.Label)findByName("StatusExams", root);
    }

    public com.codename1.ui.Label findStatusExams() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("StatusExams", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("StatusExams", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findBodyHome(Component root) {
        return (com.codename1.ui.Container)findByName("BodyHome", root);
    }

    public com.codename1.ui.Container findBodyHome() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("BodyHome", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("BodyHome", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findTxtSearch(Component root) {
        return (com.codename1.ui.TextField)findByName("txtSearch", root);
    }

    public com.codename1.ui.TextField findTxtSearch() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("txtSearch", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("txtSearch", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLblUserName(Component root) {
        return (com.codename1.ui.Label)findByName("lblUserName", root);
    }

    public com.codename1.ui.Label findLblUserName() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("lblUserName", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("lblUserName", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findUsername(Component root) {
        return (com.codename1.ui.TextField)findByName("username", root);
    }

    public com.codename1.ui.TextField findUsername() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("username", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("username", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findUserphone(Component root) {
        return (com.codename1.ui.TextField)findByName("userphone", root);
    }

    public com.codename1.ui.TextField findUserphone() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("userphone", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("userphone", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findFooterBar(Component root) {
        return (com.codename1.ui.Container)findByName("FooterBar", root);
    }

    public com.codename1.ui.Container findFooterBar() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("FooterBar", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("FooterBar", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel1(Component root) {
        return (com.codename1.ui.Label)findByName("Label1", root);
    }

    public com.codename1.ui.Label findLabel1() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Tabs findRequestTab(Component root) {
        return (com.codename1.ui.Tabs)findByName("RequestTab", root);
    }

    public com.codename1.ui.Tabs findRequestTab() {
        com.codename1.ui.Tabs cmp = (com.codename1.ui.Tabs)findByName("RequestTab", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Tabs)findByName("RequestTab", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.SpanLabel findSpanLabel(Component root) {
        return (com.codename1.components.SpanLabel)findByName("SpanLabel", root);
    }

    public com.codename1.components.SpanLabel findSpanLabel() {
        com.codename1.components.SpanLabel cmp = (com.codename1.components.SpanLabel)findByName("SpanLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.SpanLabel)findByName("SpanLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findUsercomment(Component root) {
        return (com.codename1.ui.TextArea)findByName("usercomment", root);
    }

    public com.codename1.ui.TextArea findUsercomment() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("usercomment", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("usercomment", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findFullname(Component root) {
        return (com.codename1.ui.Label)findByName("fullname", root);
    }

    public com.codename1.ui.Label findFullname() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("fullname", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("fullname", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer(Component root) {
        return (com.codename1.ui.Container)findByName("Container", root);
    }

    public com.codename1.ui.Container findContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findTabMainAbout(Component root) {
        return (com.codename1.ui.Container)findByName("TabMainAbout", root);
    }

    public com.codename1.ui.Container findTabMainAbout() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("TabMainAbout", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("TabMainAbout", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComponentGroup findComponentGroup1(Component root) {
        return (com.codename1.ui.ComponentGroup)findByName("ComponentGroup1", root);
    }

    public com.codename1.ui.ComponentGroup findComponentGroup1() {
        com.codename1.ui.ComponentGroup cmp = (com.codename1.ui.ComponentGroup)findByName("ComponentGroup1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComponentGroup)findByName("ComponentGroup1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findTabMapAbout(Component root) {
        return (com.codename1.ui.Container)findByName("TabMapAbout", root);
    }

    public com.codename1.ui.Container findTabMapAbout() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("TabMapAbout", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("TabMapAbout", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.InfiniteProgress findInfiniteProgress(Component root) {
        return (com.codename1.components.InfiniteProgress)findByName("InfiniteProgress", root);
    }

    public com.codename1.components.InfiniteProgress findInfiniteProgress() {
        com.codename1.components.InfiniteProgress cmp = (com.codename1.components.InfiniteProgress)findByName("InfiniteProgress", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.InfiniteProgress)findByName("InfiniteProgress", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findFooterBarRequest(Component root) {
        return (com.codename1.ui.Container)findByName("FooterBarRequest", root);
    }

    public com.codename1.ui.Container findFooterBarRequest() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("FooterBarRequest", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("FooterBarRequest", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findTabPersonalData(Component root) {
        return (com.codename1.ui.Container)findByName("TabPersonalData", root);
    }

    public com.codename1.ui.Container findTabPersonalData() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("TabPersonalData", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("TabPersonalData", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findBtnReset(Component root) {
        return (com.codename1.ui.Button)findByName("BtnReset", root);
    }

    public com.codename1.ui.Button findBtnReset() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("BtnReset", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("BtnReset", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findTotalRequestCost(Component root) {
        return (com.codename1.ui.Label)findByName("TotalRequestCost", root);
    }

    public com.codename1.ui.Label findTotalRequestCost() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("TotalRequestCost", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("TotalRequestCost", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findBtnSolicitar(Component root) {
        return (com.codename1.ui.Button)findByName("btnSolicitar", root);
    }

    public com.codename1.ui.Button findBtnSolicitar() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("btnSolicitar", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("btnSolicitar", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComponentGroup findUserData(Component root) {
        return (com.codename1.ui.ComponentGroup)findByName("UserData", root);
    }

    public com.codename1.ui.ComponentGroup findUserData() {
        com.codename1.ui.ComponentGroup cmp = (com.codename1.ui.ComponentGroup)findByName("UserData", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComponentGroup)findByName("UserData", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findHomeFooter(Component root) {
        return (com.codename1.ui.Container)findByName("HomeFooter", root);
    }

    public com.codename1.ui.Container findHomeFooter() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("HomeFooter", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("HomeFooter", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.CheckBox findUserafiliation(Component root) {
        return (com.codename1.ui.CheckBox)findByName("userafiliation", root);
    }

    public com.codename1.ui.CheckBox findUserafiliation() {
        com.codename1.ui.CheckBox cmp = (com.codename1.ui.CheckBox)findByName("userafiliation", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.CheckBox)findByName("userafiliation", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findTabExamsSelected(Component root) {
        return (com.codename1.ui.Container)findByName("TabExamsSelected", root);
    }

    public com.codename1.ui.Container findTabExamsSelected() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("TabExamsSelected", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("TabExamsSelected", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findPrice(Component root) {
        return (com.codename1.ui.Label)findByName("price", root);
    }

    public com.codename1.ui.Label findPrice() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("price", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("price", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Tabs findTabs(Component root) {
        return (com.codename1.ui.Tabs)findByName("Tabs", root);
    }

    public com.codename1.ui.Tabs findTabs() {
        com.codename1.ui.Tabs cmp = (com.codename1.ui.Tabs)findByName("Tabs", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Tabs)findByName("Tabs", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.MultiButton findMultiButton2(Component root) {
        return (com.codename1.components.MultiButton)findByName("MultiButton2", root);
    }

    public com.codename1.components.MultiButton findMultiButton2() {
        com.codename1.components.MultiButton cmp = (com.codename1.components.MultiButton)findByName("MultiButton2", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.MultiButton)findByName("MultiButton2", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findSelectionRenderer(Component root) {
        return (com.codename1.ui.Container)findByName("SelectionRenderer", root);
    }

    public com.codename1.ui.Container findSelectionRenderer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("SelectionRenderer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("SelectionRenderer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.MultiButton findMultiButton1(Component root) {
        return (com.codename1.components.MultiButton)findByName("MultiButton1", root);
    }

    public com.codename1.components.MultiButton findMultiButton1() {
        com.codename1.components.MultiButton cmp = (com.codename1.components.MultiButton)findByName("MultiButton1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.MultiButton)findByName("MultiButton1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findBtnAbout(Component root) {
        return (com.codename1.ui.Button)findByName("btnAbout", root);
    }

    public com.codename1.ui.Button findBtnAbout() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("btnAbout", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("btnAbout", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("MultiList", root);
    }

    public com.codename1.ui.list.MultiList findMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("MultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("MultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.MultiButton findMultiButton4(Component root) {
        return (com.codename1.components.MultiButton)findByName("MultiButton4", root);
    }

    public com.codename1.components.MultiButton findMultiButton4() {
        com.codename1.components.MultiButton cmp = (com.codename1.components.MultiButton)findByName("MultiButton4", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.MultiButton)findByName("MultiButton4", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLogoHome(Component root) {
        return (com.codename1.ui.Label)findByName("LogoHome", root);
    }

    public com.codename1.ui.Label findLogoHome() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("LogoHome", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("LogoHome", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel(Component root) {
        return (com.codename1.ui.Label)findByName("Label", root);
    }

    public com.codename1.ui.Label findLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findBtnEnviar(Component root) {
        return (com.codename1.ui.Button)findByName("BtnEnviar", root);
    }

    public com.codename1.ui.Button findBtnEnviar() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("BtnEnviar", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("BtnEnviar", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findListSelection(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("ListSelection", root);
    }

    public com.codename1.ui.list.MultiList findListSelection() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("ListSelection", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("ListSelection", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.maps.MapComponent findLabMap(Component root) {
        return (com.codename1.maps.MapComponent)findByName("LabMap", root);
    }

    public com.codename1.maps.MapComponent findLabMap() {
        com.codename1.maps.MapComponent cmp = (com.codename1.maps.MapComponent)findByName("LabMap", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.maps.MapComponent)findByName("LabMap", aboutToShowThisContainer);
        }
        return cmp;
    }

    public static final int COMMAND_HomeViewAcercaDeNuestroLaboratorio = 2;
    public static final int COMMAND_MainSiguiente = 1;

    protected boolean onHomeViewAcercaDeNuestroLaboratorio() {
        return false;
    }

    protected boolean onMainSiguiente(Command cmd) {
        return false;
    }

    protected void processCommand(ActionEvent ev, Command cmd) {
        switch(cmd.getId()) {
            case COMMAND_HomeViewAcercaDeNuestroLaboratorio:
                if(onHomeViewAcercaDeNuestroLaboratorio()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_MainSiguiente:
                if(onMainSiguiente(cmd)) {
                    ev.consume();
                    return;
                }
                break;

        }
        if(ev.getComponent() != null) {
            handleComponentAction(ev.getComponent(), ev);
        }
    }

    protected void exitForm(Form f) {
        if("HomeView".equals(f.getName())) {
            exitHomeView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("FormAboutConSuSalud".equals(f.getName())) {
            exitFormAboutConSuSalud(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("FormRequest".equals(f.getName())) {
            exitFormRequest(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            exitMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SelectionRenderer".equals(f.getName())) {
            exitSelectionRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MainSplash".equals(f.getName())) {
            exitMainSplash(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void exitHomeView(Form f) {
    }


    protected void exitFormAboutConSuSalud(Form f) {
    }


    protected void exitFormRequest(Form f) {
    }


    protected void exitMain(Form f) {
    }


    protected void exitSelectionRenderer(Form f) {
    }


    protected void exitMainSplash(Form f) {
    }

    protected void beforeShow(Form f) {
    aboutToShowThisContainer = f;
        if("HomeView".equals(f.getName())) {
            beforeHomeView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("FormAboutConSuSalud".equals(f.getName())) {
            beforeFormAboutConSuSalud(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("FormRequest".equals(f.getName())) {
            beforeFormRequest(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            beforeMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SelectionRenderer".equals(f.getName())) {
            beforeSelectionRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MainSplash".equals(f.getName())) {
            beforeMainSplash(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeHomeView(Form f) {
    }


    protected void beforeFormAboutConSuSalud(Form f) {
    }


    protected void beforeFormRequest(Form f) {
    }


    protected void beforeMain(Form f) {
    }


    protected void beforeSelectionRenderer(Form f) {
    }


    protected void beforeMainSplash(Form f) {
    }

    protected void beforeShowContainer(Container c) {
        aboutToShowThisContainer = c;
        if("HomeView".equals(c.getName())) {
            beforeContainerHomeView(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("FormAboutConSuSalud".equals(c.getName())) {
            beforeContainerFormAboutConSuSalud(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("FormRequest".equals(c.getName())) {
            beforeContainerFormRequest(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            beforeContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SelectionRenderer".equals(c.getName())) {
            beforeContainerSelectionRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("MainSplash".equals(c.getName())) {
            beforeContainerMainSplash(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeContainerHomeView(Container c) {
    }


    protected void beforeContainerFormAboutConSuSalud(Container c) {
    }


    protected void beforeContainerFormRequest(Container c) {
    }


    protected void beforeContainerMain(Container c) {
    }


    protected void beforeContainerSelectionRenderer(Container c) {
    }


    protected void beforeContainerMainSplash(Container c) {
    }

    protected void postShow(Form f) {
        if("HomeView".equals(f.getName())) {
            postHomeView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("FormAboutConSuSalud".equals(f.getName())) {
            postFormAboutConSuSalud(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("FormRequest".equals(f.getName())) {
            postFormRequest(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            postMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SelectionRenderer".equals(f.getName())) {
            postSelectionRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MainSplash".equals(f.getName())) {
            postMainSplash(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postHomeView(Form f) {
    }


    protected void postFormAboutConSuSalud(Form f) {
    }


    protected void postFormRequest(Form f) {
    }


    protected void postMain(Form f) {
    }


    protected void postSelectionRenderer(Form f) {
    }


    protected void postMainSplash(Form f) {
    }

    protected void postShowContainer(Container c) {
        if("HomeView".equals(c.getName())) {
            postContainerHomeView(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("FormAboutConSuSalud".equals(c.getName())) {
            postContainerFormAboutConSuSalud(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("FormRequest".equals(c.getName())) {
            postContainerFormRequest(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            postContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SelectionRenderer".equals(c.getName())) {
            postContainerSelectionRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("MainSplash".equals(c.getName())) {
            postContainerMainSplash(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postContainerHomeView(Container c) {
    }


    protected void postContainerFormAboutConSuSalud(Container c) {
    }


    protected void postContainerFormRequest(Container c) {
    }


    protected void postContainerMain(Container c) {
    }


    protected void postContainerSelectionRenderer(Container c) {
    }


    protected void postContainerMainSplash(Container c) {
    }

    protected void onCreateRoot(String rootName) {
        if("HomeView".equals(rootName)) {
            onCreateHomeView();
            aboutToShowThisContainer = null;
            return;
        }

        if("FormAboutConSuSalud".equals(rootName)) {
            onCreateFormAboutConSuSalud();
            aboutToShowThisContainer = null;
            return;
        }

        if("FormRequest".equals(rootName)) {
            onCreateFormRequest();
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(rootName)) {
            onCreateMain();
            aboutToShowThisContainer = null;
            return;
        }

        if("SelectionRenderer".equals(rootName)) {
            onCreateSelectionRenderer();
            aboutToShowThisContainer = null;
            return;
        }

        if("MainSplash".equals(rootName)) {
            onCreateMainSplash();
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void onCreateHomeView() {
    }


    protected void onCreateFormAboutConSuSalud() {
    }


    protected void onCreateFormRequest() {
    }


    protected void onCreateMain() {
    }


    protected void onCreateSelectionRenderer() {
    }


    protected void onCreateMainSplash() {
    }

    protected Hashtable getFormState(Form f) {
        Hashtable h = super.getFormState(f);
        if("HomeView".equals(f.getName())) {
            getStateHomeView(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("FormAboutConSuSalud".equals(f.getName())) {
            getStateFormAboutConSuSalud(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("FormRequest".equals(f.getName())) {
            getStateFormRequest(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Main".equals(f.getName())) {
            getStateMain(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("SelectionRenderer".equals(f.getName())) {
            getStateSelectionRenderer(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("MainSplash".equals(f.getName())) {
            getStateMainSplash(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

            return h;
    }


    protected void getStateHomeView(Form f, Hashtable h) {
    }


    protected void getStateFormAboutConSuSalud(Form f, Hashtable h) {
    }


    protected void getStateFormRequest(Form f, Hashtable h) {
    }


    protected void getStateMain(Form f, Hashtable h) {
    }


    protected void getStateSelectionRenderer(Form f, Hashtable h) {
    }


    protected void getStateMainSplash(Form f, Hashtable h) {
    }

    protected void setFormState(Form f, Hashtable state) {
        super.setFormState(f, state);
        if("HomeView".equals(f.getName())) {
            setStateHomeView(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("FormAboutConSuSalud".equals(f.getName())) {
            setStateFormAboutConSuSalud(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("FormRequest".equals(f.getName())) {
            setStateFormRequest(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            setStateMain(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("SelectionRenderer".equals(f.getName())) {
            setStateSelectionRenderer(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("MainSplash".equals(f.getName())) {
            setStateMainSplash(f, state);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void setStateHomeView(Form f, Hashtable state) {
    }


    protected void setStateFormAboutConSuSalud(Form f, Hashtable state) {
    }


    protected void setStateFormRequest(Form f, Hashtable state) {
    }


    protected void setStateMain(Form f, Hashtable state) {
    }


    protected void setStateSelectionRenderer(Form f, Hashtable state) {
    }


    protected void setStateMainSplash(Form f, Hashtable state) {
    }

    protected boolean setListModel(List cmp) {
        String listName = cmp.getName();
        if("MultiList".equals(listName)) {
            return initListModelMultiList(cmp);
        }
        if("ListSelection".equals(listName)) {
            return initListModelListSelection(cmp);
        }
        return super.setListModel(cmp);
    }

    protected boolean initListModelMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelListSelection(List cmp) {
        return false;
    }

    protected void handleComponentAction(Component c, ActionEvent event) {
        Container rootContainerAncestor = getRootAncestor(c);
        if(rootContainerAncestor == null) return;
        String rootContainerName = rootContainerAncestor.getName();
        Container leadParentContainer = c.getParent().getLeadParent();
        if(leadParentContainer != null && leadParentContainer.getClass() != Container.class) {
            c = c.getParent().getLeadParent();
        }
        if(rootContainerName == null) return;
        if(rootContainerName.equals("HomeView")) {
            if("btnSolicitar".equals(c.getName())) {
                onHomeView_BtnSolicitarAction(c, event);
                return;
            }
            if("btnAbout".equals(c.getName())) {
                onHomeView_BtnAboutAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("FormAboutConSuSalud")) {
            if("MultiButton4".equals(c.getName())) {
                onFormAboutConSuSalud_MultiButton4Action(c, event);
                return;
            }
            if("MultiButton1".equals(c.getName())) {
                onFormAboutConSuSalud_MultiButton1Action(c, event);
                return;
            }
            if("MultiButton2".equals(c.getName())) {
                onFormAboutConSuSalud_MultiButton2Action(c, event);
                return;
            }
        }
        if(rootContainerName.equals("FormRequest")) {
            if("username".equals(c.getName())) {
                onFormRequest_UsernameAction(c, event);
                return;
            }
            if("userphone".equals(c.getName())) {
                onFormRequest_UserphoneAction(c, event);
                return;
            }
            if("userafiliation".equals(c.getName())) {
                onFormRequest_UserafiliationAction(c, event);
                return;
            }
            if("usercomment".equals(c.getName())) {
                onFormRequest_UsercommentAction(c, event);
                return;
            }
            if("ListSelection".equals(c.getName())) {
                onFormRequest_ListSelectionAction(c, event);
                return;
            }
            if("BtnEnviar".equals(c.getName())) {
                onFormRequest_BtnEnviarAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Main")) {
            if("MultiList".equals(c.getName())) {
                onMain_MultiListAction(c, event);
                return;
            }
            if("BtnReset".equals(c.getName())) {
                onMain_BtnResetAction(c, event);
                return;
            }
            if("txtSearch".equals(c.getName())) {
                onMain_TxtSearchAction(c, event);
                return;
            }
        }
    }

      protected void onHomeView_BtnSolicitarAction(Component c, ActionEvent event) {
      }

      protected void onHomeView_BtnAboutAction(Component c, ActionEvent event) {
      }

      protected void onFormAboutConSuSalud_MultiButton4Action(Component c, ActionEvent event) {
      }

      protected void onFormAboutConSuSalud_MultiButton1Action(Component c, ActionEvent event) {
      }

      protected void onFormAboutConSuSalud_MultiButton2Action(Component c, ActionEvent event) {
      }

      protected void onFormRequest_UsernameAction(Component c, ActionEvent event) {
      }

      protected void onFormRequest_UserphoneAction(Component c, ActionEvent event) {
      }

      protected void onFormRequest_UserafiliationAction(Component c, ActionEvent event) {
      }

      protected void onFormRequest_UsercommentAction(Component c, ActionEvent event) {
      }

      protected void onFormRequest_ListSelectionAction(Component c, ActionEvent event) {
      }

      protected void onFormRequest_BtnEnviarAction(Component c, ActionEvent event) {
      }

      protected void onMain_MultiListAction(Component c, ActionEvent event) {
      }

      protected void onMain_BtnResetAction(Component c, ActionEvent event) {
      }

      protected void onMain_TxtSearchAction(Component c, ActionEvent event) {
      }

}
