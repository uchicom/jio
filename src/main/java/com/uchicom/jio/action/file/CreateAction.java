// (C) 2012 uchicom
package com.uchicom.jio.action.file;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.window.JournalFrame;
import java.awt.event.ActionEvent;

/** @author Uchiyama Shigeki */
public class CreateAction extends ConfirmAction {
  /** */
  private static final long serialVersionUID = 1L;

  public CreateAction(JournalFrame journalFrame) {
    super(journalFrame);
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent e) {
    journalFrame.create();
  }
}
