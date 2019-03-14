// (c) 2013 uchicom
package com.uchicom.jio.window;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import com.uchicom.jio.bean.Journal;
import com.uchicom.jio.table.BookTableModel;
import com.uchicom.jio.table.SelectCellEditor;

/**
 * 現金出納帳画面.
 * 
 * @author uchiyamas
 *
 */
public class AccountBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BookTableModel bookModel;

	public AccountBook(List<Journal> journalList) {
		bookModel = new BookTableModel(journalList);
		initComponents();
	}

	public void setAccountName(String accountName, String viewName) {
		this.setTitle(viewName);
		bookModel.setAccountName(accountName);
		bookModel.fireTableDataChanged();
	}

	// 現金出納帳を表示する。
	private void initComponents() {
		setIconImage(new ImageIcon(getClass().getClassLoader().getResource("com/uchicom/jio/icon.png")).getImage());

		// 画面作成
		initView();
	}

	private void initView() {

		DefaultTableColumnModel columnModel = new DefaultTableColumnModel();
		TableColumn tableColumn = new TableColumn(0);

		TableCellEditor cellEditor = new SelectCellEditor();
		tableColumn.setHeaderValue("日付");
		tableColumn.setIdentifier(0);
		tableColumn.setCellEditor(cellEditor);
		columnModel.addColumn(tableColumn);

		tableColumn = new TableColumn(1);
		tableColumn.setHeaderValue("科目");
		tableColumn.setIdentifier(1);
		tableColumn.setCellEditor(cellEditor);
		columnModel.addColumn(tableColumn);

		tableColumn = new TableColumn(2);
		tableColumn.setHeaderValue("摘要");
		tableColumn.setIdentifier(2);
		tableColumn.setCellEditor(cellEditor);
		columnModel.addColumn(tableColumn);

		tableColumn = new TableColumn(3);
		tableColumn.setHeaderValue("収入");
		tableColumn.setIdentifier(3);
		tableColumn.setCellEditor(cellEditor);
		columnModel.addColumn(tableColumn);

		tableColumn = new TableColumn(4);
		tableColumn.setHeaderValue("支出");
		tableColumn.setIdentifier(4);
		tableColumn.setCellEditor(cellEditor);
		columnModel.addColumn(tableColumn);

		tableColumn = new TableColumn(5);
		tableColumn.setHeaderValue("差引残高");
		tableColumn.setIdentifier(5);
		tableColumn.setCellEditor(cellEditor);
		columnModel.addColumn(tableColumn);
		JTable table = new JTable(bookModel, columnModel);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getContentPane().add(new JScrollPane(table));

	}
}
