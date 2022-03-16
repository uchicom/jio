// (C) 2013 uchicom
package com.uchicom.jio.ui.window;

import com.uchicom.jio.bean.Journal;
import com.uchicom.jio.ui.table.BalanceTableModel;
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
 * 損益計算書画面.
 *
 * @author Shigeki.Uchiyama
 */
public class ProfitBook extends JFrame {

  /** */
  private static final long serialVersionUID = 1L;

  BalanceTableModel balanceModel;

  public ProfitBook(List<Journal> journalList) {
    super("損益計算書");
    balanceModel = new BalanceTableModel(journalList);
    initComponents();
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
    TableColumn tableColumn = new TableColumn(0);
    TableCellEditor cellEditor = new SelectCellEditor();
    tableColumn.setHeaderValue("勘定科目");
    tableColumn.setIdentifier(0);
    tableColumn.setCellEditor(cellEditor);
    columnModel.addColumn(tableColumn);

    tableColumn = new TableColumn(1);
    tableColumn.setHeaderValue("期初");
    tableColumn.setIdentifier(1);
    tableColumn.setCellEditor(cellEditor);
    columnModel.addColumn(tableColumn);
    tableColumn = new TableColumn(1);
    tableColumn.setHeaderValue("期末");
    tableColumn.setIdentifier(1);
    tableColumn.setCellEditor(cellEditor);
    columnModel.addColumn(tableColumn);
    JTable table = new JTable(balanceModel, columnModel);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    getContentPane().add(new JScrollPane(table));
  }
}
