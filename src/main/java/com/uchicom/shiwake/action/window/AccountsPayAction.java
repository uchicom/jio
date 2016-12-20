// (c) 2012 uchicom
package com.uchicom.shiwake.action.window;

import java.awt.event.ActionEvent;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.window.ShiwakeFrame;

/**
 * 買掛帳表示アクション.
 *
 * @author Uchiyama Shigeki
 *
 */
public class AccountsPayAction extends ConfirmAction {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public AccountsPayAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
	}

	/* (non-Javadoc)
	 * @see com.uchicom.shiwake.action.ConfirmAction#action(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent e) {
		shiwakeFrame.showBook(ShiwakeFrame.PROP_KEY_ACCOUNT_PAY_WINDOW);
	}

}
