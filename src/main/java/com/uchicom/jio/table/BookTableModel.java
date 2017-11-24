// (c) 2012 uchicom
package com.uchicom.jio.table;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.uchicom.jio.bean.Account;
import com.uchicom.jio.bean.Journal;
import com.uchicom.jio.bean.SubJournal;
import com.uchicom.jio.bean.Transaction;

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
		int balance = 0;
		for (Journal journal : rowList) {
			for (Transaction credit : journal.getCreditList()) {
				if (credit.getAccount() != null && accountName.equals(credit.getAccount().getName())) {
					if (journal.getCreditList().size() == 1) {
						if (journal.getDebitList().size() == 1) {
							balance -= journal.getAmount();
							bookList.add(new SubJournal(journal.getDealDay(), journal.getSummary(),
									-journal.getAmount(), journal.getDebitList().get(0).getAccount(), balance));
						} else {
							for (Transaction debit : journal.getDebitList()) {
								balance -= debit.getAmount();
								bookList.add(new SubJournal(journal.getDealDay(), journal.getSummary(),
										-debit.getAmount(), debit.getAccount(), balance));
							}
						}
					} else if (journal.getDebitList().size() == 1) {
						balance -= credit.getAmount();
						bookList.add(new SubJournal(journal.getDealDay(), journal.getSummary(),
							-credit.getAmount(), journal.getDebitList().get(0).getAccount(), balance));
					} else {
						balance -= credit.getAmount();
						bookList.add(new SubJournal(journal.getDealDay(), journal.getSummary(),
								-credit.getAmount(), null, balance));
					}
				}
			}
			for (Transaction debit : journal.getDebitList()) {
				if (debit.getAccount() != null && accountName.equals(debit.getAccount().getName())) {
					if (journal.getDebitList().size() == 1) {
						if (journal.getCreditList().size() == 1) {
							balance += journal.getAmount();
							bookList.add(new SubJournal(journal.getDealDay(), journal.getSummary(),
									journal.getAmount(), journal.getCreditList().get(0).getAccount(), balance));
						} else {
							for (Transaction credit : journal.getCreditList()) {
								balance += journal.getAmount();
								bookList.add(new SubJournal(journal.getDealDay(), journal.getSummary(), journal.getAmount(), credit.getAccount(), balance));
							}
						}
					} else if (journal.getCreditList().size() == 1) {
						balance += debit.getAmount();
						bookList.add(new SubJournal(journal.getDealDay(), journal.getSummary(),
							debit.getAmount(), journal.getCreditList().get(0).getAccount(), balance));
					} else {
						balance += debit.getAmount();
						bookList.add(new SubJournal(journal.getDealDay(), journal.getSummary(),
							debit.getAmount(), null, balance));
					}
				}
			}
		}
		System.out.println("bookList:" + bookList.size());
	}

	public Object getValueAt(int row, int col) {
		SubJournal bean = bookList.get(row);
		String viewString = null;
		switch (col) {
		case 0:	//日付
			viewString = format.format(bean.getDealDay());
			break;
		case 1:	//科目
			Account account = bean.getAccount();
			viewString = account == null ? "" : account.getName();
			break;
		case 2:	//摘要
			viewString = bean.getSummary();
			break;

		case 3:	//収入
			if (bean.getAmount() != null && bean.getAmount() > 0) {
				viewString = String.valueOf(bean.getAmount());
			} else {
				viewString = "";
			}
			break;
		case 4:	//支出
			if (bean.getAmount() != null && bean.getAmount() < 0) {
				viewString = String.valueOf(-bean.getAmount());
			} else {
				viewString = "";
			}
			break;
		case 5:	//差引残高
			viewString = String.valueOf(bean.getBalance());
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
