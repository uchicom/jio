// (C) 2012 uchicom
package com.uchicom.jio.action.window;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.window.JournalFrame;
import java.awt.event.ActionEvent;

/**
 * 損益計算書画面表示アクション.
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class ProfitAction extends ConfirmAction {

  /** */
  private static final long serialVersionUID = 1L;

  public ProfitAction(JournalFrame journalFrame) {
    super(journalFrame);
  }

  @Override
  public void action(ActionEvent e) {
    journalFrame.showProfitBook();
  }
}
