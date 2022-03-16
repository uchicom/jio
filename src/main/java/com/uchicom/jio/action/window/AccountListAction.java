// (C) 2013 uchicom
package com.uchicom.jio.action.window;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.window.JournalFrame;
import java.awt.event.ActionEvent;

/**
 * 勘定科目一覧画面表示アクション.
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class AccountListAction extends ConfirmAction {
  /** */
  private static final long serialVersionUID = 1L;

  public AccountListAction(JournalFrame journalFrame) {
    super(journalFrame);
  }

  /* (non-Javadoc)
   * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent arg0) {
    journalFrame.showAccountList();
  }
}
