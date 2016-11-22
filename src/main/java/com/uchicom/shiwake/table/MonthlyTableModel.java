// (c) 2012 uchicom
package com.uchicom.shiwake.table;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.uchicom.shiwake.bean.Journal;
import com.uchicom.shiwake.bean.Transaction;

public class MonthlyTableModel extends DefaultTableModel {

	List<String[]> monthlyList = new ArrayList<>();
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** Creates a new instance of ListTableModel */
	public MonthlyTableModel(List<Journal> rowList) {
		this.rowList = rowList;
	}

	public void setAccountName(String accountName) {
		System.out.println("rowList" + rowList.size());
		monthlyList.clear();
		Collections.sort(rowList, new Comparator<Journal>() {

			@Override
			public int compare(Journal o1, Journal o2) {
				if (o1.getDealDay() == null) {
					if (o2.getDealDay() == null) {
						return 0;
					}
					return -1;
				} else if (o2.getDealDay() == null) {
					return 1;
				}
				return o1.getDealDay().compareTo(o2.getDealDay());
			}

		});
		int tmpYearMonth = -1;
		String[] tmpRow = null;
		Calendar calendar = Calendar.getInstance();
		for (Journal journal : rowList) {
			calendar.setTime(journal.getDealDay());
			int yearMonth = calendar.get(Calendar.YEAR) * 100 + calendar.get(Calendar.MONTH);
			if (tmpYearMonth < 0 || tmpYearMonth != yearMonth) {
//				if (tmpDate != null) {
//				System.out.println("tmp:" + tmpDate.getTime() / (24 * 60 * 60 * 1000));
//				System.out.println("journal:" + journal.getDealDay().getTime() / (24 * 60 * 60 * 1000));
//				System.out.println("tmp:" + tmpDate);
//				System.out.println("journal:" + journal.getDealDay());
//				}
				tmpYearMonth = yearMonth;
				tmpRow = new String[]{format.format(journal.getDealDay()),
						"0"
					};
				monthlyList.add(tmpRow);
			}
			int creditAmount = 0;
			int debitAmount = 0;
			for (Transaction credit : journal.getCreditList()) {
				if (credit.getAccount() != null && accountName.equals(credit.getAccount().getName())) {
					if (journal.getCreditList().size() > 1) {
						creditAmount += credit.getAmount();
					} else {
						creditAmount += journal.getAmount();
					}
				}
			}
			for (Transaction debit : journal.getDebitList()) {
				if (debit.getAccount() != null && accountName.equals(debit.getAccount().getName())) {
					if (journal.getDebitList().size() > 1) {
						debitAmount += debit.getAmount();
					} else {
						debitAmount += journal.getAmount();
					}
				}
			}
			tmpRow[1] = String.valueOf(Integer.parseInt(tmpRow[1]) - debitAmount + creditAmount);


		}
		System.out.println("bookList:" + monthlyList.size());
	}

	public Object getValueAt(int row, int col) {
		String[] bean = monthlyList.get(row);
		return bean[col];
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		if (monthlyList != null) {
			return monthlyList.size();
		} else {
			return 0;
		}
	}

	/** データ格納リスト */
	private List<Journal> rowList;

}
