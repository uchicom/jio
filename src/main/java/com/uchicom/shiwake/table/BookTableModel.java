// (c) 2012 uchicom
package com.uchicom.shiwake.table;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.uchicom.shiwake.bean.Journal;
import com.uchicom.shiwake.bean.SubJournal;
import com.uchicom.shiwake.bean.Transaction;

public class BookTableModel extends DefaultTableModel {

	List<SubJournal> bookList = new ArrayList<>();
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** Creates a new instance of ListTableModel */
	public BookTableModel(List<Journal> rowList) {
		this.rowList = rowList;
	}

	public void setAccountName(String accountName) {
		System.out.println("rowList" + rowList.size());
		bookList.clear();
		for (Journal journal : rowList) {
			for (Transaction credit : journal.getCreditList()) {
				if (credit.getAccount() != null && accountName.equals(credit.getAccount().getName())) {
					if (journal.getCreditList().size() == 1) {
						bookList.add(new SubJournal(journal.getDealDay(), journal.getSummary(),
							journal.getAmount(), journal.getDebitList()));
					} else {

					}
				}
			}
			for (Transaction debit : journal.getDebitList()) {
				if (debit.getAccount() != null && accountName.equals(debit.getAccount().getName())) {
					//bookList.add(journal);
				}
			}
		}
		System.out.println("bookList:" + bookList.size());
	}

	public Object getValueAt(int row, int col) {
		Journal bean = rowList.get(row);
		String viewString = null;
		switch (col) {
		case 0:
			viewString = format.format(bean.getDealDay());
			break;
		case 1:
			viewString = bean.getSummary();
			break;
		case 2:
			return bean.getDebitList();

		case 3:
			return bean.getCreditList();

		case 4:
			if (bean.getAmount() == null) {
				viewString = "";
			} else {
				viewString = String.valueOf(bean.getAmount());
			}
			break;
		default:
			viewString = "";
		}
		return viewString;
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public int getColumnCount() {
		return 6;
	}

	public int getRowCount() {
		if (bookList != null) {
			return bookList.size();
		} else {
			return 0;
		}
	}

	/** データ格納リスト */
	private List<Journal> rowList;

}
