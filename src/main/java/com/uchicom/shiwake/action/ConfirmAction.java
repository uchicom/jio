// (c) 2012 uchicom
package com.uchicom.shiwake.action;

import java.awt.event.ActionEvent;

import com.uchicom.shiwake.window.ShiwakeFrame;
import com.uchicom.ui.action.AbstractResourceAction;

public abstract class ConfirmAction extends AbstractResourceAction {


	protected ShiwakeFrame shiwakeFrame;
	public ConfirmAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
		this.shiwakeFrame = shiwakeFrame;
	}

	@Override
	public final void actionPerformed(ActionEvent e) {
		shiwakeFrame.confirm();
		action(e);
	}

	/**
	 * 通常のアクション処理を記述する.
	 *
	 * @param e
	 */
	public abstract void action(ActionEvent e);

}
