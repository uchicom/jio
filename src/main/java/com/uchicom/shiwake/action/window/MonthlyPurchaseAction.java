// (c) 2016 uchicom
package com.uchicom.shiwake.action.window;

import java.awt.event.ActionEvent;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.window.ShiwakeFrame;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class MonthlyPurchaseAction extends ConfirmAction {

	/**
	 * @param shiwakeFrame
	 */
	public MonthlyPurchaseAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
	}

	/* (非 Javadoc)
	 * @see com.uchicom.shiwake.action.ConfirmAction#action(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent e) {
		shiwakeFrame.showMonthlyBook(ShiwakeFrame.PROP_KEY_MONTHLY_PURCHASE_WINDOW);
	}

}
