// (c) 2012 uchicom
package com.uchicom.shiwake.action.file;

import java.awt.event.ActionEvent;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.window.ShiwakeFrame;

/**
 * @author Uchiyama Shigeki
 *
 */
public class CreateAction extends ConfirmAction {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public CreateAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent e) {
	    shiwakeFrame.create();
	}

}
