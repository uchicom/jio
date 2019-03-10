// (c) 2012 uchicom
package com.uchicom.jio.action;

import java.awt.event.ActionEvent;

import com.uchicom.jio.window.JournalFrame;
import com.uchicom.ui.action.AbstractResourceAction;

public abstract class ConfirmAction extends AbstractResourceAction<JournalFrame> {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JournalFrame journalFrame;
	public ConfirmAction(JournalFrame journalFrame) {
		super(journalFrame);
		this.journalFrame = journalFrame;
	}

	@Override
	public final void actionPerformed(ActionEvent e) {
		journalFrame.confirm();
		action(e);
	}

	/**
	 * 通常のアクション処理を記述する.
	 *
	 * @param e
	 */
	public abstract void action(ActionEvent e);

}
