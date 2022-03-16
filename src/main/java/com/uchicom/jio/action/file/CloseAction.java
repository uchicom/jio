// (C) 2012 uchicom
package com.uchicom.jio.action.file;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.ui.window.JournalBook;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/** @author Uchiyama Shigeki */
public class CloseAction extends ConfirmAction {

  /** */
  private static final long serialVersionUID = 1L;

  public CloseAction(JournalBook journalBook) {
    super(journalBook);
  }
  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent e) {
    // 画面を閉じる
    journalBook.dispatchEvent(new WindowEvent(journalBook, WindowEvent.WINDOW_CLOSING));
  }
}
