// (C) 2012 uchicom
package com.uchicom.jio.action.window;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.window.JournalFrame;
import java.awt.event.ActionEvent;

/**
 * 現金出納帳画面表示アクション.
 *
 * @author Uchiyama Shigeki
 */
public class CashAction extends ConfirmAction {
  /** */
  private static final long serialVersionUID = 1L;

  public CashAction(JournalFrame journalFrame) {
    super(journalFrame);
  }

  /* (non-Javadoc)
   * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent arg0) {
    journalFrame.showBook(JournalFrame.PROP_KEY_CASHBOOK_WINDOW);
  }
}
