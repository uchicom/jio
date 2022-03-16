// (C) 2012 uchicom
package com.uchicom.jio.action.file;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.ui.window.JournalBook;
import java.awt.event.ActionEvent;

/** @author Uchiyama Shigeki */
public class ExportAccountAction extends ConfirmAction {

  /** */
  private static final long serialVersionUID = 1L;

  public ExportAccountAction(JournalBook frame) {
    super(frame);
  }
  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent arg0) {
    journalBook.create(file -> journalBook.exportAccountCsv(file));
  }
}
