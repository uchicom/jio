// (C) 2012 uchicom
package com.uchicom.jio.action.window;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.ui.window.JournalBook;
import java.awt.event.ActionEvent;

/**
 * 買掛帳表示アクション.
 *
 * @author Uchiyama Shigeki
 */
public class AccountsPayAction extends ConfirmAction {
  /** */
  private static final long serialVersionUID = 1L;

  public AccountsPayAction(JournalBook journalBook) {
    super(journalBook);
  }

  /* (non-Javadoc)
   * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent e) {
    journalBook.showBook(journalBook.PROP_KEY_ACCOUNT_PAY_WINDOW);
  }
}
