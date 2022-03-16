// (C) 2012 uchicom
package com.uchicom.jio.ui.table;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

public class SelectCellEditor implements TableCellEditor {

  @Override
  public Object getCellEditorValue() {
    // TODO 自動生成されたメソッド・スタブ
    return null;
  }

  @Override
  public boolean isCellEditable(EventObject anEvent) {
    // TODO 自動生成されたメソッド・スタブ
    return false;
  }

  @Override
  public boolean shouldSelectCell(EventObject anEvent) {
    // TODO 自動生成されたメソッド・スタブ
    return true;
  }

  @Override
  public boolean stopCellEditing() {
    // TODO 自動生成されたメソッド・スタブ
    return false;
  }

  @Override
  public void cancelCellEditing() {
    // TODO 自動生成されたメソッド・スタブ

  }

  @Override
  public void addCellEditorListener(CellEditorListener l) {
    // TODO 自動生成されたメソッド・スタブ

  }

  @Override
  public void removeCellEditorListener(CellEditorListener l) {
    // TODO 自動生成されたメソッド・スタブ

  }

  @Override
  public Component getTableCellEditorComponent(
      JTable table, Object value, boolean isSelected, int row, int column) {
    // TODO 自動生成されたメソッド・スタブ
    return null;
  }
}
