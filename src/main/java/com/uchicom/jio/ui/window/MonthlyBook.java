// (C) 2013 uchicom
package com.uchicom.jio.ui.window;

import com.uchicom.jio.bean.Journal;
import com.uchicom.jio.ui.table.MonthlyTableModel;
import com.uchicom.jio.ui.table.SelectCellEditor;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 * 月別勘定一覧画面.
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class MonthlyBook extends JFrame {

  /** */
  private static final long serialVersionUID = 1L;

  MonthlyTableModel monthlyModel;

  public MonthlyBook(List<Journal> journalList) {
    monthlyModel = new MonthlyTableModel(journalList);
    initComponents();
  }

  public void setAccountName(String accountName, String viewName) {
    this.setTitle(viewName);
    monthlyModel.setAccountName(accountName);
    //		table = new JTable(model, new String[]{"日付", "科目", "摘要", "収入", "支出", "差引残高"});
    monthlyModel.fireTableDataChanged();
  }

  // 現金出納帳を表示する。
  private void initComponents() {
    // アイコン
    setIconImage(
        new ImageIcon(getClass().getClassLoader().getResource("com/uchicom/jio/icon.png"))
            .getImage());

    // 画面作成
    initView();
  }

  private void initView() {

    DefaultTableColumnModel columnModel = new DefaultTableColumnModel();
    TableCellEditor cellEditor = new SelectCellEditor();
    TableColumn tableColumn = new TableColumn(0);

    tableColumn.setHeaderValue("年月度");
    tableColumn.setIdentifier(0);
    tableColumn.setCellEditor(cellEditor);
    columnModel.addColumn(tableColumn);

    tableColumn = new TableColumn(1);
    tableColumn.setHeaderValue("金額");
    tableColumn.setIdentifier(1);
    tableColumn.setCellEditor(cellEditor);
    columnModel.addColumn(tableColumn);
    JTable table = new JTable(monthlyModel, columnModel);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    getContentPane().add(new JScrollPane(table));
  }
}
