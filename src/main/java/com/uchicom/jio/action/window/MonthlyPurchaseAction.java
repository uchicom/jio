// (C) 2016 uchicom
package com.uchicom.jio.action.window;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.ui.window.JournalBook;
import java.awt.event.ActionEvent;

/** @author uchicom: Shigeki Uchiyama */
public class MonthlyPurchaseAction extends ConfirmAction {

  /** */
  private static final long serialVersionUID = 1L;
  /** @param journalBook */
  public MonthlyPurchaseAction(JournalBook journalBook) {
    super(journalBook);
  }

  /* (非 Javadoc)
   * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent e) {
    journalBook.showMonthlyBook(journalBook.PROP_KEY_MONTHLY_PURCHASE_WINDOW);
  }
}
