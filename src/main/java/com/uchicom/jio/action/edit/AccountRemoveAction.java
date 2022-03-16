// (C) 2013 uchicom
package com.uchicom.jio.action.edit;

import com.uchicom.jio.ui.window.AccountListBook;
import com.uchicom.jio.ui.window.JournalBook;
import com.uchicom.ui.action.AbstractResourceAction;
import java.awt.event.ActionEvent;

/** @author uchicom: Shigeki Uchiyama */
public class AccountRemoveAction extends AbstractResourceAction<JournalBook> {

  /** */
  private static final long serialVersionUID = 1L;

  private AccountListBook accountListBook;

  public AccountRemoveAction(JournalBook journalBook, AccountListBook accountListBook) {
    super(journalBook);
    putValue(NAME, "削除");
    this.accountListBook = accountListBook;
  }
  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    accountListBook.remove();
  }
}
