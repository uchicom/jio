// (C) 2012 uchicom
package com.uchicom.jio.action.file;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.window.JournalFrame;
import java.awt.event.ActionEvent;

/** @author Uchiyama Shigeki */
public class SaveAction extends ConfirmAction {

  /** */
  private static final long serialVersionUID = 1L;

  private JournalFrame journalFrame = null;

  public SaveAction(JournalFrame journalFrame) {
    super(journalFrame);
    putValue(NAME, "保存");
    this.journalFrame = journalFrame;
  }
  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent e) {
    journalFrame.save();
  }
}
