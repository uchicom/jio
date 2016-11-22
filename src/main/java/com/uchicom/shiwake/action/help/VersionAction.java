// (c) 2016 uchicom
package com.uchicom.shiwake.action.help;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.uchicom.shiwake.Constants;
import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.window.ShiwakeFrame;

public class VersionAction extends ConfirmAction {

	public VersionAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.uchicom.shiwake.action.ConfirmAction#action(java.awt.event.
	 * ActionEvent)
	 */
	@Override
	public void action(ActionEvent arg0) {
		JOptionPane.showMessageDialog(uiStore.getMainComponent(), Constants.VERSION);

	}

}
