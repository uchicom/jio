// (c) 2012 uchicom
package com.uchicom.shiwake.action.window;

import java.awt.event.ActionEvent;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.window.ShiwakeFrame;

/**
 * 現金出納帳画面表示アクション.
 *
 * @author Uchiyama Shigeki
 *
 */
public class CashAction extends ConfirmAction {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public CashAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
	}

	/* (non-Javadoc)
	 * @see com.uchicom.shiwake.action.ConfirmAction#action(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent arg0) {
		shiwakeFrame.showBook(ShiwakeFrame.PROP_KEY_CASHBOOK_WINDOW);
	}

}
