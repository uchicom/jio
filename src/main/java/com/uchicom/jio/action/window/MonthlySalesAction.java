// (c) 2012 uchicom
package com.uchicom.jio.action.window;

import java.awt.event.ActionEvent;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.window.JournalFrame;

/**
 * 月別売上画面表示アクション.
 *
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class MonthlySalesAction extends ConfirmAction {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public MonthlySalesAction(JournalFrame journalFrame) {
		super(journalFrame);
	}

	/* (non-Javadoc)
	 * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent arg0) {
		journalFrame.showMonthlyBook(JournalFrame.PROP_KEY_MONTHLY_SALES_WINDOW);
	}

}
