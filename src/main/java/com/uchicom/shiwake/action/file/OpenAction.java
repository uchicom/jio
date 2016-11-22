// (c) 2012 uchicom
package com.uchicom.shiwake.action.file;

import java.awt.event.ActionEvent;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.window.ShiwakeFrame;

/**
 * @author Uchiyama Shigeki
 *
 */
public class OpenAction extends ConfirmAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public OpenAction(ShiwakeFrame shiwake) {
		super(shiwake);
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent arg0) {
		shiwakeFrame.open();
	}

}
