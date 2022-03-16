// (C) 2012 uchicom
package com.uchicom.jio.action.window;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.ui.window.JournalBook;
import java.awt.event.ActionEvent;

/**
 * 月別売上画面表示アクション.
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class MonthlySalesAction extends ConfirmAction {
  /** */
  private static final long serialVersionUID = 1L;

  public MonthlySalesAction(JournalBook journalBook) {
    super(journalBook);
  }

  /* (non-Javadoc)
   * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
   */
  @Override
  public void action(ActionEvent arg0) {
    journalBook.showMonthlyBook(journalBook.PROP_KEY_MONTHLY_SALES_WINDOW);
  }
}
