// (C) 2012 uchicom
package com.uchicom.jio.action.tool;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.ui.window.JournalBook;
import java.awt.event.ActionEvent;

/**
 * オプション設定アクション.
 *
 * @author Uchiyama Shigeki
 */
public class OptionAction extends ConfirmAction {

  /** */
  private static final long serialVersionUID = 1L;

  public OptionAction(JournalBook journalBook) {
    super(journalBook);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.
   * ActionEvent)
   */
  @Override
  public void action(ActionEvent arg0) {}
}
