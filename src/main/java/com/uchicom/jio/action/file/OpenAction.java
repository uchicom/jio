// (c) 2012 uchicom
package com.uchicom.jio.action.file;

import java.awt.event.ActionEvent;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.window.JournalFrame;

/**
 * @author Uchiyama Shigeki
 *
 */
public class OpenAction extends ConfirmAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public OpenAction(JournalFrame frame) {
		super(frame);
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent arg0) {
		journalFrame.open();
	}

}
