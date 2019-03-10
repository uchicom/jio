// (c) 2013 uchicom
package com.uchicom.jio.window;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import com.uchicom.jio.bean.Journal;
import com.uchicom.jio.table.BalanceTableModel;
import com.uchicom.jio.table.SelectCellEditor;

/**
 * 貸借対照表画面.
 * 
 * @author shige
 *
 */
public class BalanceBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BalanceTableModel balanceModel;

	public BalanceBook(List<Journal> journalList) {
		super("貸借対照表");
		balanceModel = new BalanceTableModel(journalList);
		initComponents();
	}

	// 現金出納帳を表示する。
	private void initComponents() {
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
		getContentPane().add(new JScrollPane(table));
	}

	@Override
	public void setVisible(boolean visible) {
		balanceModel.reflesh();
		balanceModel.fireTableDataChanged();
		super.setVisible(visible);
	}
}
