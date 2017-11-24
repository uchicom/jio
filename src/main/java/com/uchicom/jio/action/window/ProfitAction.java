// (c) 2012 uchicom
package com.uchicom.jio.action.window;

import java.awt.event.ActionEvent;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.window.JournalFrame;

/**
 * 損益計算書画面表示アクション.
 *
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class ProfitAction extends ConfirmAction {

	public ProfitAction(JournalFrame journalFrame) {
		super(journalFrame);
	}

	@Override
	public void action(ActionEvent e) {
		journalFrame.showProfitBook();
	}

}
