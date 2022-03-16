// (C) 2012 uchicom
package com.uchicom.jio.action;

import com.uchicom.jio.ui.window.JournalBook;
import com.uchicom.ui.action.AbstractResourceAction;
import java.awt.event.ActionEvent;

public abstract class ConfirmAction extends AbstractResourceAction<JournalBook> {

  /** */
  private static final long serialVersionUID = 1L;

  protected JournalBook journalBook;

  public ConfirmAction(JournalBook journalBook) {
    super(journalBook);
    this.journalBook = journalBook;
  }

  @Override
  public final void actionPerformed(ActionEvent e) {
    journalBook.confirm();
    action(e);
  }

  /**
   * 通常のアクション処理を記述する.
   *
   * @param e
   */
  public abstract void action(ActionEvent e);
}
