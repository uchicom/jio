// (c) 2013 uchicom
package com.uchicom.jio.table;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class TextAreaCellEditor implements TableCellEditor {

	private static final Logger logger = Logger.getLogger(TextAreaCellEditor.class.getCanonicalName());
    private JTextArea textArea;

    private List<CellEditorListener> listenerList = new LinkedList<CellEditorListener>();

    public TextAreaCellEditor(JTextArea textArea) {
        this.textArea = textArea;
    }

    /* (non-Javadoc)
     * @see javax.swing.CellEditor#addCellEditorListener(javax.swing.event.CellEditorListener)
     */
    @Override
    public void addCellEditorListener(CellEditorListener arg0) {
        // TODO Auto-generated method stub
        logger.info("addCellEditorListener");
        listenerList.add(arg0);

    }

    /* (non-Javadoc)
     * @see javax.swing.CellEditor#cancelCellEditing()
     */
    @Override
    public void cancelCellEditing() {
        // TODO Auto-generated method stub
        logger.info("cancelCellEditing");
        List<CellEditorListener> cancelList = new ArrayList<CellEditorListener>();
        cancelList.addAll(listenerList);
        for (CellEditorListener listener : cancelList) {
            listener.editingStopped(new ChangeEvent(textArea));
        }

    }

    /* (non-Javadoc)
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    @Override
    public Object getCellEditorValue() {
        logger.info("getCellEditorValue");


        return textArea.getText();
    }
    KeyEvent keyEvent;
    /* (non-Javadoc)
     * @see javax.swing.CellEditor#isCellEditable(java.util.EventObject)
     */
    @Override
    public boolean isCellEditable(EventObject eventObject) {
        logger.info("isCellEditable" + eventObject);
        if (eventObject instanceof MouseEvent) {
            MouseEvent mouseEvent = (MouseEvent) eventObject;
            keyEvent = null;
            return mouseEvent.getClickCount() >= 2;
        } else if (eventObject instanceof KeyEvent) {
            keyEvent = (KeyEvent) eventObject;
        }
        return textArea.isEditable();
    }

    /* (non-Javadoc)
     * @see javax.swing.CellEditor#removeCellEditorListener(javax.swing.event.CellEditorListener)
     */
    @Override
    public void removeCellEditorListener(CellEditorListener arg0) {
        logger.info("removeCellEditorListener");
        listenerList.remove(arg0);

    }

    /* (non-Javadoc)
     * @see javax.swing.CellEditor#shouldSelectCell(java.util.EventObject)
     */
    @Override
    public boolean shouldSelectCell(EventObject arg0) {
        logger.info("shouldSelectCell");

        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see javax.swing.CellEditor#stopCellEditing()
     */
    @Override
    public boolean stopCellEditing() {
        logger.info("stopCellEditing");
        List<CellEditorListener> stopList = new ArrayList<CellEditorListener>();
        stopList.addAll(listenerList);
        for (CellEditorListener listener : stopList) {
            listener.editingStopped(new ChangeEvent(textArea));
        }
        return true;
    }


    /* (non-Javadoc)
     * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        logger.info("getTableCellEditorComponent");
        textArea.setText((String)value);
        return textArea;
    }

}
