/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses.common;

import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.list.ListModel;
import java.util.Vector;

/**
 *
 * @author EliuX
 */
public abstract class FilterProxyListModel implements ListModel, DataChangedListener {

    private final ListModel underlying;
    private Vector filter;
    private final Vector listeners = new Vector();

    public FilterProxyListModel(ListModel underlying) {
        this.underlying = underlying;
        underlying.addDataChangedListener(this);
    }

    private int getFilterOffset(int index) {
        if (filter == null) {
            return index;
        }
        if (filter.size() > index) {
            return ((Integer) filter.elementAt(index)).intValue();
        }
        return -1;
    }

    private int getUnderlyingOffset(int index) {
        if (filter == null) {
            return index;
        }
        return filter.indexOf(new Integer(index));
    }

    public void filter(String str) {
        filter = new Vector();
        str = str.toUpperCase();
        for (int iter = 0; iter < underlying.getSize(); iter++) {
            String element = ItemtoString(underlying.getItemAt(iter));
            if (element.toUpperCase().indexOf(str) > -1) {
                filter.addElement(new Integer(iter)); 
            }
        }
        dataChanged(DataChangedListener.CHANGED, -1);
    }

    public Object getItemAt(int index) {
        return underlying.getItemAt(getFilterOffset(index));
    }

    public int getSize() {
        if (filter == null) {
            return underlying.getSize();
        }
        return filter.size();
    }

    public int getSelectedIndex() {
        return Math.max(0, getUnderlyingOffset(underlying.getSelectedIndex()));
    }

    public void setSelectedIndex(int index) {
        underlying.setSelectedIndex(getFilterOffset(index));
    }

    public void addDataChangedListener(DataChangedListener l) {
        listeners.addElement(l);
    }

    public void removeDataChangedListener(DataChangedListener l) {
        listeners.removeElement(l);
    }

    public void addSelectionListener(SelectionListener l) {
        underlying.addSelectionListener(l);
    }

    public void removeSelectionListener(SelectionListener l) {
        underlying.removeSelectionListener(l);
    }

    public void addItem(Object item) {
        underlying.addItem(item);
    }

    public void removeItem(int index) {
        underlying.removeItem(index);
    }

    public void dataChanged(int type, int index) {
        if (index > -1) {
            index = getUnderlyingOffset(index);
            if (index < 0) {
                return;
            }
        }
        for (int iter = 0; iter < listeners.size(); iter++) {

            ((DataChangedListener) listeners.elementAt(iter)).dataChanged(type, index);
        }
    }

    /**
     * No matter the item's type it get it's text equivalent to be compared with
     * the entered text
     *
     * @param item Item's which the list is made of!
     * @return The item'String equivalent to be compared with the entered text
     */
    abstract public String ItemtoString(Object item);
}
