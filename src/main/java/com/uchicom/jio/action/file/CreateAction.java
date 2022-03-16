// (C) 2012 uchicom
package com.uchicom.jio.action.file;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.ui.window.JournalBook;
import java.awt.event.ActionEvent;

/** @author Uchiyama Shigeki */
public class CreateAction extends ConfirmAction {
  /** */
  private static final long serialVersionUID = 1L;

  public CreateAction(JournalBook journalBook) {
    super(journalBook);
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent e) {
    journalBook.create();
  }
}
