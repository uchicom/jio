// (c) 2012 uchicom
package com.uchicom.jio;

import javax.swing.SwingUtilities;

import com.uchicom.jio.window.JournalFrame;

public class Main {

	/**
	 * メイン呼び出し
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()-> {
			JournalFrame journalFrame = new JournalFrame();
			journalFrame.pack();
			journalFrame.setVisible(true);
		});
	}

}
