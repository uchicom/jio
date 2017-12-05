// (c) 2016 uchicom
package com.uchicom.jio.action.help;

import java.awt.event.ActionEvent;

import com.uchicom.jio.Constants;
import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.window.JournalFrame;
import com.uchicom.ui.util.DialogUtil;

/**
 * バージョン情報表示アクション.
 *
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class VersionAction extends ConfirmAction {

	public VersionAction(JournalFrame journalFrame) {
		super(journalFrame);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.
	 * ActionEvent)
	 */
	@Override
	public void action(ActionEvent arg0) {
		DialogUtil.showMessageDialog(uiStore.getMainComponent(), uiStore.getResourceBundle().getString("message.version") + Constants.VERSION);
	}

}
