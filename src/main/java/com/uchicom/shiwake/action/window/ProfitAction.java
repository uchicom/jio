// (c) 2012 uchicom
package com.uchicom.shiwake.action.window;

import java.awt.event.ActionEvent;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.window.ShiwakeFrame;

public class ProfitAction extends ConfirmAction {

	public ProfitAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
	}

	@Override
	public void action(ActionEvent e) {
		shiwakeFrame.showProfitBook();

	}

}
