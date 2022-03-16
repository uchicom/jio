// (C) 2012 uchicom
package com.uchicom.jio;

import com.uchicom.jio.ui.window.JournalBook;
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
          JournalBook journalBook = new JournalBook();
          journalBook.pack();
          journalBook.setVisible(true);
        });
  }
}
