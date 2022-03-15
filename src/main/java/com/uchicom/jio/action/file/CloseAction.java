// (C) 2012 uchicom
package com.uchicom.jio.action.file;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.window.JournalFrame;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/** @author Uchiyama Shigeki */
public class CloseAction extends ConfirmAction {

  /** */
  private static final long serialVersionUID = 1L;

  public CloseAction(JournalFrame journalFrame) {
    super(journalFrame);
  }
  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent e) {
    // 画面を閉じる
    journalFrame.dispatchEvent(new WindowEvent(journalFrame, WindowEvent.WINDOW_CLOSING));
  }
}
