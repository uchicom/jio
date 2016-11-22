// (c) 2012 uchicom
package com.uchicom.shiwake;

import javax.swing.SwingUtilities;

import com.uchicom.shiwake.window.ShiwakeFrame;

public class Main {

	/**
	 * メイン呼び出し
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()-> {
			ShiwakeFrame shiwakeFrame = new ShiwakeFrame();
			shiwakeFrame.pack();
			shiwakeFrame.setVisible(true);
		});
	}

}
