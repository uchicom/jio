// (C) 2013 uchicom
package com.uchicom.jio.ui.table;

import com.uchicom.jio.bean.Account;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/** @author uchicom: Shigeki Uchiyama */
public class AccountTableModel extends DefaultTableModel {

  /** */
  private static final long serialVersionUID = 1L;

  private static final Logger logger = Logger.getLogger(AccountTableModel.class.getCanonicalName());

  private List<Account> rowList;

  private List<Account> addList = new ArrayList<Account>();
  //    private List<Account> updateList = new ArrayList<Account>();
  private List<Account> deleteList = new ArrayList<Account>();

  private int columnCount;

  public AccountTableModel(List<Account> rowList, int columnCount) {
    this.rowList = rowList;
    this.columnCount = columnCount;
  }

  public Object getValueAt(int row, int col) {
    Account bean = rowList.get(row);
    String viewString = null;
    switch (col) {
      case 0:
        viewString = String.valueOf(bean.getId());
        break;
      case 1:
        viewString = bean.getCd();
        break;
      case 2:
        viewString = bean.getName();
        break;
      case 3:
        viewString = bean.getCategory();
        break;
      case 4:
        viewString = String.valueOf(bean.getSortKey());
        break;
      case 5:
        viewString = String.valueOf(bean.getRate());
        break;
      default:
        viewString = "";
    }
    return viewString;
  }

  public void setValueAt(Object value, int row, int col) {
    if (row < rowList.size()) {
      Account bean = rowList.get(row);

      switch (col) {
        case 0:
          bean.setId(Long.valueOf((String) value));
          break;
        case 1:
          bean.setCd((String) value);
          break;
        case 2:
          bean.setName((String) value);
          break;
        case 3:
          bean.setCategory((String) value);
          break;
        case 4:
          if (value != null) {
            bean.setSortKey(Integer.parseInt((String) value));
          }
          break;
        case 5:
          bean.setRate(Integer.valueOf((String) value));
          break;
        default:
          logger.info(String.valueOf(col));
      }
    } else {
      // 削除時の選択解除時にcancelcelleditingを実施したから問題ないはずだけど。。一応残しておくか。
      logger.info("row:" + row + ",col:" + col);
    }
  }

  public Class<?> getColumnClass(int columnIndex) {
    Class<?> classObj = null;
    switch (columnIndex) {
      case 0:
        classObj = Long.class;
        break;
      case 1:
        classObj = String.class;
        break;
      case 2:
        classObj = String.class;
        break;
      case 3:
        classObj = String.class;
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

  public void removeRows(int[] aRow) {
    for (int i = aRow.length - 1; i >= 0; i--) {
      logger.info(String.valueOf(aRow[i]));
      Account bean = rowList.get(aRow[i]);
      rowList.remove(bean);
      deleteList.add(bean);
      fireTableRowsDeleted(aRow[i], aRow[i]);
    }
    // 恐らく削除した跡に編集を始めようとしてしまうのが原因のようだが。。どうしたものか。
  }

  /**
   * 仕分け追加
   *
   * @param aRow
   */
  public void addRows(int[] aRow) {

    for (int i = aRow.length - 1; i >= 0; i--) {
      Account bean = new Account();
      rowList.add(aRow[i], bean);
      addList.add(bean);
      fireTableRowsInserted(aRow[i], aRow[i]);
    }
  }

  public List<Account> getRowList() {
    return rowList;
  }

  public void setRowList(List<Account> rowList) {
    this.rowList = rowList;
    fireTableStructureChanged();
  }
}
