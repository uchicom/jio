// (C) 2012 uchicom
package com.uchicom.jio.action.window;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.window.JournalFrame;
import java.awt.event.ActionEvent;

/**
 * 貸借対照表画面表示アクション.
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class BalanceAction extends ConfirmAction {
  /** */
  private static final long serialVersionUID = 1L;

  public BalanceAction(JournalFrame journalFrame) {
    super(journalFrame);
  }

  /* (non-Javadoc)
   * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent arg0) {
    journalFrame.showBalanceBook();
  }
}
