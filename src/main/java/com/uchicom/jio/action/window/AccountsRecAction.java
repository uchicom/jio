// (C) 2012 uchicom
package com.uchicom.jio.action.window;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.window.JournalFrame;
import java.awt.event.ActionEvent;

/**
 * 売掛帳表示アクション.
 *
 * @author Uchiyama Shigeki
 */
public class AccountsRecAction extends ConfirmAction {
  /** */
  private static final long serialVersionUID = 1L;

  public AccountsRecAction(JournalFrame journalFrame) {
    super(journalFrame);
  }

  /* (non-Javadoc)
   * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent e) {
    journalFrame.showBook(JournalFrame.PROP_KEY_ACCOUNT_REC_WINDOW);
  }
}
