// (C) 2012 uchicom
package com.uchicom.jio.action.window;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.ui.window.JournalBook;
import java.awt.event.ActionEvent;

/**
 * 損益計算書画面表示アクション.
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class ProfitAction extends ConfirmAction {

  /** */
  private static final long serialVersionUID = 1L;

  public ProfitAction(JournalBook journalBook) {
    super(journalBook);
  }

  @Override
  public void action(ActionEvent e) {
    journalBook.showProfitBook();
  }
}
