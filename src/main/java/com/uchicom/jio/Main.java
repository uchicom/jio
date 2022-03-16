// (C) 2012 uchicom
package com.uchicom.jio;

import com.uchicom.jio.window.JournalFrame;
import javax.swing.SwingUtilities;

public class Main {

  /**
   * メイン呼び出し
   *
   * @param args
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        () -> {
          JournalFrame journalFrame = new JournalFrame();
          journalFrame.pack();
          journalFrame.setVisible(true);
        });
  }
}
