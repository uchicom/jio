// (c) 2012 uchicom
package com.uchicom.jio.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.table.DefaultTableModel;

import com.uchicom.jio.bean.Account;
import com.uchicom.jio.bean.Journal;
import com.uchicom.jio.bean.Transaction;

public class BalanceTableModel extends DefaultTableModel {

	List<Entry<Account, Long>> balanceList = new ArrayList<>();
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** Creates a new instance of ListTableModel */
	public BalanceTableModel(List<Journal> rowList) {
		this.rowList = rowList;
	}

	public void reflesh() {
		System.out.println("rowList" + rowList.size());
		balanceList.clear();
		Map<Account, Long> accountTotalMap = new HashMap<>();
		for (Journal journal : rowList) {
			//debit
			for (Transaction debit : journal.getDebitList()) {
				if (debit.getAccount() == null) continue;
				Long total = 0L;
				if (accountTotalMap.containsKey(debit.getAccount())) {
					total = accountTotalMap.get(debit.getAccount());
				}
				if (journal.getDebitList().size() == 1 && journal.getAmount() != null) {
					total += journal.getAmount();
				} else if (debit.getAmount() != null) {
					total += debit.getAmount();
				}
				accountTotalMap.put(debit.getAccount(), total);
			}
			for (Transaction credit : journal.getCreditList()) {
				if (credit.getAccount() == null) continue;//TODO 本当は不明な勘定もひょうじしないとな
				Long total = 0L;
				if (accountTotalMap.containsKey(credit.getAccount())) {
					total = accountTotalMap.get(credit.getAccount());

				}

				if (journal.getCreditList().size() == 1 && journal.getAmount() != null) {
					total -= journal.getAmount();
				} else if (credit.getAmount() != null) {
					total -= credit.getAmount();
				}
				accountTotalMap.put(credit.getAccount(), total);
			}
		}
		for (Entry<Account, Long> entry : accountTotalMap.entrySet()) {
			balanceList.add(entry);
		}
		Collections.sort(balanceList, new Comparator<Entry<Account, Long>>() {

			@Override
			public int compare(Entry<Account, Long> o1, Entry<Account, Long> o2) {
				if (o1.getKey() == null) {
					if (o2.getKey() == null) {
						return 0;
					} else {
						return 1;
					}
				} else if (o2.getKey() == null) {
					return -1;

				} else {
					return o1.getKey().compareTo(o2.getKey());
				}
			}

		});

	}

	public Object getValueAt(int row, int col) {
		Entry<Account, Long> bean = balanceList.get(row);
		Object returnObject = null;
		switch (col) {
		case 0:
			returnObject = bean.getKey().getName();
			break;
		case 1:
			returnObject = "未実装";
			break;
		case 2:
			returnObject = bean.getValue();
			break;
		}
		return returnObject;
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		if (balanceList != null) {
			return balanceList.size();
		} else {
			return 0;
		}
	}

	/** データ格納リスト */
	private List<Journal> rowList;

}
