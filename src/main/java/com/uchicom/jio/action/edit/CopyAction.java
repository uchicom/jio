// (C) 2012 uchicom
package com.uchicom.jio.action.edit;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.ui.table.ListTableModel;
import com.uchicom.jio.ui.window.JournalBook;
import java.awt.event.ActionEvent;

/** @author Uchiyama Shigeki */
public class CopyAction extends ConfirmAction {

  /** */
  private static final long serialVersionUID = 1L;

  public CopyAction(JournalBook journalBook) {
    super(journalBook);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.
   * ActionEvent)
   */
  @Override
  public void action(ActionEvent e) {
    ListTableModel model = journalBook.getModel();
    model.copy(journalBook.getSelectedRows());
  }
}
