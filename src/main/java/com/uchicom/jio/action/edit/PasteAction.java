// (C) 2012 uchicom
package com.uchicom.jio.action.edit;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.table.ListTableModel;
import com.uchicom.jio.window.JournalFrame;
import java.awt.event.ActionEvent;

/** @author Uchiyama Shigeki */
public class PasteAction extends ConfirmAction {

  /** */
  private static final long serialVersionUID = 1L;

  public PasteAction(JournalFrame journalFrame) {
    super(journalFrame);
  }

  /* (non-Javadoc)
   * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent e) {
    ListTableModel model = journalFrame.getModel();
    model.insert(journalFrame.getSelectedRows());
  }
}
