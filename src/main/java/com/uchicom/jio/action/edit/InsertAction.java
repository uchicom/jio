// (C) 2012 uchicom
package com.uchicom.jio.action.edit;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.ui.table.ListTableModel;
import com.uchicom.jio.ui.window.JournalBook;
import com.uchicom.ui.util.DialogUtil;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 * 行を追加する
 *
 * @author Uchiyama Shigeki
 */
public class InsertAction extends ConfirmAction {
  /** */
  private static final long serialVersionUID = 1L;

  public InsertAction(JournalBook journalBook) {
    super(journalBook);
  }

  /* (non-Javadoc)
   * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent arg0) {
    if (JOptionPane.OK_OPTION
        == DialogUtil.showConfirmDialog(
            uiStore.getMainComponent(), uiStore.getResourceBundle().getString("message.add"))) {
      ListTableModel model = journalBook.getModel();
      int[] rowIndexs = journalBook.getSelectedRows();
      if (rowIndexs.length == 0) {
        model.addRow();
      } else {
        model.addRows(rowIndexs);
      }
    }
  }
}
