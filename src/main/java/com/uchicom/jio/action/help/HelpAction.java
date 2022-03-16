// (C) 2016 uchicom
package com.uchicom.jio.action.help;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.ui.window.JournalBook;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * オンラインヘルプ表示アクション.
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class HelpAction extends ConfirmAction {

  /** */
  private static final long serialVersionUID = 1L;

  public HelpAction(JournalBook journalBook) {
    super(journalBook);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.
   * ActionEvent)
   */
  @Override
  public void action(ActionEvent arg0) {

    Desktop desktop = Desktop.getDesktop();

    try {
      desktop.browse(new URI("http://labs.uchicom.com/help/jio/"));
    } catch (IOException e2) {
      e2.printStackTrace();
    } catch (URISyntaxException e2) {
      e2.printStackTrace();
    }
  }
}
