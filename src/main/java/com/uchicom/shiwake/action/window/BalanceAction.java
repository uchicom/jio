// (c) 2012 uchicom
package com.uchicom.shiwake.action.window;

import java.awt.event.ActionEvent;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.window.ShiwakeFrame;

/**
 * 貸借対照表画面表示アクション.
 *
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class BalanceAction extends ConfirmAction {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public BalanceAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
	}

	/* (non-Javadoc)
	 * @see com.uchicom.shiwake.action.ConfirmAction#action(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent arg0) {
		shiwakeFrame.showBalanceBook();
	}

}
