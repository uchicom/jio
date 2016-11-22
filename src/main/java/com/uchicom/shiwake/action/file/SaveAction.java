// (c) 2012 uchicom
package com.uchicom.shiwake.action.file;

import java.awt.event.ActionEvent;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.window.ShiwakeFrame;

/**
 * @author Uchiyama Shigeki
 *
 */
public class SaveAction extends ConfirmAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ShiwakeFrame shiwakeFrame = null;
	public SaveAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
		putValue(NAME, "保存");
		this.shiwakeFrame = shiwakeFrame;

	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent e) {
	    shiwakeFrame.save();
	}

}
