// (C) 2013 uchicom
package com.uchicom.jio.action.window;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.ui.window.JournalBook;
import java.awt.event.ActionEvent;

/**
 * 勘定科目一覧画面表示アクション.
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class AccountListAction extends ConfirmAction {
  /** */
  private static final long serialVersionUID = 1L;

  public AccountListAction(JournalBook journalBook) {
    super(journalBook);
  }

  /* (non-Javadoc)
   * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent arg0) {
    journalBook.showAccountList();
  }
}
