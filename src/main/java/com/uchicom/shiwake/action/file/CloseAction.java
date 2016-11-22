// (c) 2012 uchicom
package com.uchicom.shiwake.action.file;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.window.ShiwakeFrame;

/**
 * @author Uchiyama Shigeki
 *
 */
public class CloseAction extends ConfirmAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
//	private ShiwakeFrame shiwakeFrame = null;

	public CloseAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
//		putValue(NAME, "終了(X)");
//		//alt+でメニューを選択状態にするキー、メニューが開いている場合は選択するキーとなる。
//		putValue(MNEMONIC_KEY, KeyEvent.VK_X);
//		this.shiwakeFrame = shiwakeFrame;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent e) {
		//画面を閉じる
		shiwakeFrame.dispatchEvent(new WindowEvent(shiwakeFrame, WindowEvent.WINDOW_CLOSING));
	}

}
