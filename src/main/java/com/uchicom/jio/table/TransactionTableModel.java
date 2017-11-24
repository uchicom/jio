// (c) 2013 uchicom
package com.uchicom.jio.table;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.uchicom.jio.bean.Account;
import com.uchicom.jio.bean.Transaction;

public class TransactionTableModel extends DefaultTableModel {

	/**
     *
     */
    private static final long serialVersionUID = 1L;

    /** データ格納リスト */
	private List<Transaction> rowList;

	/** 列最大数 */
	private int columnCount = 0;

	/**
	 *
	 * @param columnCount
	 */
	public TransactionTableModel() {
		this.columnCount = 2;
	}
	/**
	 *
	 * @param rowList
	 * @param columnCount
	 */
	public TransactionTableModel(List<Transaction> rowList, int columnCount) {
		this.rowList = rowList;
		this.columnCount = columnCount;
	}

	public Object getValueAt(int row, int col) {
		Transaction bean = rowList.get(row);
		String value = null;
		switch (col) {
		case 0:
		    if (bean.getAccount() == null
		     || bean.getAccount().getName() == null) {
		        value = "";
		    } else {
		        value = bean.getAccount().getName();
		    }
			break;
		case 1:
		    if (bean.getAmount() == null) {
		        value = "";
		    } else {
		        value = String.valueOf(bean.getAmount());
		    }
			break;
		default:
			value = "";
				break;
		}
//		System.out.println("listValue:" + value);
		return value;
	}

	public void setValueAt(Object value, int row, int col) {
	    System.out.println(value);
		if (row < rowList.size()) {
			Transaction bean = rowList.get(row);
			if (value instanceof Account) {
				bean.setAccount((Account) value);
			} else {
				String viewString = (String) value;

			switch (col) {
			case 0:
			    if("".equals(viewString)) {
			        bean.setAccount(null);
			    } else {
			        bean.setAccount(new Account(null, viewString, null));
			    }
				break;
			case 1:
                if("".equals(viewString)) {
                    bean.setAmount(null);
                } else {
                    bean.setAmount(Long.parseLong(viewString));
                }
				break;
			default:
				System.out.println(col);
			}
			}
		} else {
			//削除時の選択解除時にcancelcelleditingを実施したから問題ないはずだけど。。一応残しておくか。
			System.out.println("row:" + row + ",col:" + col);
		}
	}

	public Class<?> getColumnClass(int columnIndex) {
	    Class<?> returnClass = null;
	    switch (columnIndex) {
	    case 0:
	        returnClass = String.class;
	        break;
	    case 1:
	        returnClass = String.class;
	        break;
	    }
		return returnClass;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getRowCount() {
		if (rowList != null) {
			return rowList.size();
		} else {
			return 0;
		}
	}

	public void setRowList(List<Transaction> rowList) {
		this.rowList = rowList;
	}
    public List<Transaction> getRowList() {
        return rowList;
    }

}
