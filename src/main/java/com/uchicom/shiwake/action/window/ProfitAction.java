// (c) 2012 uchicom
package com.uchicom.shiwake.action.window;

import java.awt.event.ActionEvent;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.window.ShiwakeFrame;

/**
 * 損益計算書画面表示アクション.
 *
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class ProfitAction extends ConfirmAction {

	public ProfitAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
	}

	@Override
	public void action(ActionEvent e) {
		shiwakeFrame.showProfitBook();
	}

}
