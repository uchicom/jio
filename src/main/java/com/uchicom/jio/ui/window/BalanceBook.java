// (C) 2013 uchicom
package com.uchicom.jio.ui.window;

import com.uchicom.jio.bean.Journal;
import com.uchicom.jio.ui.table.BalanceTableModel;
import com.uchicom.jio.ui.table.SelectCellEditor;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 * 貸借対照表画面.
 *
 * @author shige
 */
public class BalanceBook extends JFrame {

  /** */
  private static final long serialVersionUID = 1L;

  BalanceTableModel balanceModel;

  public BalanceBook(List<Journal> journalList) {
    super("貸借対照表");
    balanceModel = new BalanceTableModel(journalList);
    initComponents();
  }

  // 現金出納帳を表示する。
  private void initComponents() {
    setIconImage(
        new ImageIcon(getClass().getClassLoader().getResource("com/uchicom/jio/icon.png"))
            .getImage());

    // 画面作成
    initView();
  }

  private void initView() {

    // 資産の部
    JPanel leftPanel = new JPanel(new BorderLayout());
    leftPanel.add(new JLabel("資産の部"), BorderLayout.NORTH);
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
    tableColumn = new TableColumn(2);
    tableColumn.setHeaderValue("期末");
    tableColumn.setIdentifier(2);
    tableColumn.setCellEditor(cellEditor);
    columnModel.addColumn(tableColumn);
    JTable table = new JTable(balanceModel, columnModel);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    leftPanel.add(new JScrollPane(table), BorderLayout.CENTER);
    JSplitPane splitPane = new JSplitPane();
    splitPane.setLeftComponent(leftPanel);

    // 資本・負債の部
    JPanel rightPanel = new JPanel(new BorderLayout());
    rightPanel.add(new JLabel("資本・負債の部"), BorderLayout.NORTH);
    columnModel = new DefaultTableColumnModel();
    tableColumn = new TableColumn(0);
    cellEditor = new SelectCellEditor();
    tableColumn.setHeaderValue("勘定科目");
    tableColumn.setIdentifier(0);
    tableColumn.setCellEditor(cellEditor);
    columnModel.addColumn(tableColumn);

    tableColumn = new TableColumn(1);
    tableColumn.setHeaderValue("期初");
    tableColumn.setIdentifier(1);
    tableColumn.setCellEditor(cellEditor);
    columnModel.addColumn(tableColumn);
    tableColumn = new TableColumn(2);
    tableColumn.setHeaderValue("期末");
    tableColumn.setIdentifier(2);
    tableColumn.setCellEditor(cellEditor);
    columnModel.addColumn(tableColumn);
    table = new JTable(balanceModel, columnModel);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    rightPanel.add(new JScrollPane(table), BorderLayout.CENTER);
    splitPane.setRightComponent(rightPanel);
    getContentPane().add(splitPane);
  }

  @Override
  public void setVisible(boolean visible) {
    balanceModel.reflesh();
    balanceModel.fireTableDataChanged();
    super.setVisible(visible);
  }
}
