// (c) 2012 uchicom
package com.uchicom.shiwake.action.window;

import java.awt.event.ActionEvent;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.window.ShiwakeFrame;

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

	public MonthlySalesAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
	}

	/* (non-Javadoc)
	 * @see com.uchicom.shiwake.action.ConfirmAction#action(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent arg0) {
		shiwakeFrame.showMonthlyBook(ShiwakeFrame.PROP_KEY_MONTHLY_SALES_WINDOW);
	}

}
