// (c) 2016 uchicom
package com.uchicom.jio.action.window;

import java.awt.event.ActionEvent;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.window.JournalFrame;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class MonthlyPurchaseAction extends ConfirmAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param journalFrame
	 */
	public MonthlyPurchaseAction(JournalFrame journalFrame) {
		super(journalFrame);
	}

	/* (非 Javadoc)
	 * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent e) {
		journalFrame.showMonthlyBook(JournalFrame.PROP_KEY_MONTHLY_PURCHASE_WINDOW);
	}

}
