// (C) 2012 uchicom
package com.uchicom.jio.action.edit;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.bean.Journal;
import com.uchicom.jio.table.ListTableModel;
import com.uchicom.jio.window.JournalFrame;
import com.uchicom.ui.util.DialogUtil;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class AddCreditAction extends ConfirmAction {
  private static final long serialVersionUID = 1L;

  public AddCreditAction(JournalFrame journalFrame) {
    super(journalFrame);
  }

  /* (non-Javadoc)
   * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent arg0) {
    ListTableModel model = journalFrame.getModel();
    int[] rowIndexs = journalFrame.getSelectedRows();
    if (rowIndexs.length > 0) {
      for (int index = 0; index < rowIndexs.length; index++) {
        int rowIndex = rowIndexs[index];
        Journal bean = model.getRowList().get(rowIndex);
        if (bean.getDebitList().size() > 1) {
          DialogUtil.showMessageDialog(journalFrame, "多対多の複合仕分けは対応していません。");
          Arrays.copyOfRange(rowIndexs, rowIndex, rowIndexs.length - 1);
        } else {
          model.addCreditRows(rowIndexs);
        }
      }
    } else {
      // そもそもメニューで選択できないようにしたい。TODO
      DialogUtil.showMessageDialog(journalFrame, "仕訳を選択してください.");
    }
  }
}
