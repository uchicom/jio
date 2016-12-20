// (c) 2012 uchicom
package com.uchicom.shiwake.table;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.uchicom.shiwake.bean.Journal;
import com.uchicom.shiwake.bean.Transaction;
import com.uchicom.shiwake.enums.TransactionType;

/**
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class ListTableModel extends DefaultTableModel {

	public static final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** Creates a new instance of ListTableModel */
	public ListTableModel(List<Journal> rowList, int columnCount) {
		this.rowList = rowList;
		this.columnCount = columnCount;
	}

	public Object getValueAt(int row, int col) {
		Journal bean = rowList.get(row);
		String viewString = null;
		switch (col) {
		case 0:
			if (bean.getDealDay() == null) {
				viewString = "----/--/--";
			} else {
				viewString = format.format(bean.getDealDay());
			}
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

	@SuppressWarnings("unchecked")
    public void setValueAt(Object value, int row, int col) {
		if (row < rowList.size()) {
		Journal bean = rowList.get(row);

		switch (col) {
		case 0:
			bean.setDealDay((Date)value);
			break;
		case 1:
			bean.setSummary((String)value);
			break;
		case 2:
		    bean.setDebitList((List<Transaction>) value);
			break;
		case 3:
			bean.setCreditList((List<Transaction>) value);
			break;
		case 4:
			if (!"".equals((String)value)) {
				bean.setAmount(Long.valueOf((String)value));
			}
			break;
		default:
			System.out.println(col);
		}
		} else {
			//削除時の選択解除時にcancelcelleditingを実施したから問題ないはずだけど。。一応残しておくか。
			System.out.println("row:" + row + ",col:" + col);
		}
	}

	public Class<?> getColumnClass(int columnIndex) {
	    Class<?> classObj = null;
	    switch (columnIndex) {
        case 0:
            classObj = Date.class;
            break;
        case 1:
            classObj = String.class;
            break;
        case 2:
            classObj = List.class;
            break;
        case 3:
            classObj = List.class;
            break;
        case 4:
            classObj = Long.class;
            break;
        default:
            classObj = String.class;
        }
		return classObj;
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

	/**
	 * 文字列を検索して位置を返す
	 *
	 * @param target
	 * @param startRow
	 * @param startCol
	 * @return
	 */
	// public Point searchAndSelect(String target, int startRow, int startCol,
	// boolean contain) {
	//
	// //検索値からテーブルの中を検索する
	// boolean result = false;
	// for (int i = startRow; i < rowList.size(); i++) {
	// String[] cells = rowList.get(i);
	// for (int j = 0; j < cells.length; j++) {
	// if (!contain && i == startRow && j == 0) {
	// j = startCol + 1;
	// }
	// if (target.equals(cells[j])) {
	// return new Point(i,j);
	// }
	// }
	// }
	//
	// return new Point();
	// }

	public void removeRows(int[] aRow) {
		for (int i = aRow.length - 1; i >= 0; i--) {
			System.out.println(aRow[i]);
			Journal bean = rowList.get(aRow[i]);
			rowList.remove(bean);
			deleteList.add(bean);
			fireTableRowsDeleted(aRow[i], aRow[i]);
		}
		//恐らく削除した跡に編集を始めようとしてしまうのが原因のようだが。。どうしたものか。
	}
	public void addRow() {
	    Journal bean = new Journal(TransactionType.Credit,TransactionType.Debit);
        rowList.add(bean);
        addList.add(bean);
        fireTableRowsInserted(rowList.size() - 1, rowList.size() -1);
	}
	/**
	 * 仕分け追加
	 * @param aRow
	 */
	public void addRows(int[] aRow) {

		for (int i = aRow.length - 1; i >= 0; i--) {
			Journal bean = new Journal(TransactionType.Credit,TransactionType.Debit);
			rowList.add(aRow[i], bean);
			addList.add(bean);
			fireTableRowsInserted(aRow[i], aRow[i]);
		}
	}

	/**
	 * 借方勘定項目追加
	 * @param aRow
	 */
	public void addDebitRows(int[] aRow) {
        for (int i = aRow.length - 1; i >= 0; i--) {
            rowList.get(aRow[i]).add(new Transaction(TransactionType.Debit));
            fireTableRowsUpdated(aRow[i], aRow[i]);
        }
	}
	/**
	 * 貸方勘定科目追加
	 * @param aRow
	 */
	public void addCreditRows(int[] aRow) {
        for (int i = aRow.length - 1; i >= 0; i--) {
            rowList.get(aRow[i]).add(new Transaction(TransactionType.Credit));
            fireTableRowsUpdated(aRow[i], aRow[i]);
        }
	}

	/** データ格納リスト */
	private List<Journal> rowList;

	/** 列最大数 */
	private int columnCount = 0;

	private List<Journal> addList = new ArrayList<Journal>();
	private List<Journal> deleteList = new ArrayList<Journal>();
	private List<Journal> updateList = new ArrayList<Journal>();

    /**
     * addListを取得します。
     * @return addList
     */
    public List<Journal> getAddList() {
        return addList;
    }

    /**
     * addListを設定します。
     * @param addList
     */
    public void setAddList(List<Journal> addList) {
        this.addList = addList;
    }

    /**
     * deleteListを取得します。
     * @return deleteList
     */
    public List<Journal> getDeleteList() {
        return deleteList;
    }

    /**
     * deleteListを設定します。
     * @param deleteList
     */
    public void setDeleteList(List<Journal> deleteList) {
        this.deleteList = deleteList;
    }

    /**
     * updateListを取得します。
     * @return updateList
     */
    public List<Journal> getUpdateList() {
        return updateList;
    }

    /**
     * updateListを設定します。
     * @param updateList
     */
    public void setUpdateList(List<Journal> updateList) {
        this.updateList = updateList;
    }

    public List<Journal> getRowList() {
        return rowList;
    }
    public void setRowList(List<Journal> rowList) {
        this.rowList = rowList;
        fireTableStructureChanged();
    }
}
