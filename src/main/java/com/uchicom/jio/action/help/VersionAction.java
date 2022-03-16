// (C) 2016 uchicom
package com.uchicom.jio.action.help;

import com.uchicom.jio.Constants;
import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.ui.window.JournalBook;
import com.uchicom.ui.util.DialogUtil;
import java.awt.event.ActionEvent;

/**
 * バージョン情報表示アクション.
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class VersionAction extends ConfirmAction {

  /** */
  private static final long serialVersionUID = 1L;

  public VersionAction(JournalBook journalBook) {
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
    DialogUtil.showMessageDialog(
        uiStore.getMainComponent(),
        uiStore.getResourceBundle().getString("message.version") + Constants.VERSION);
  }
}
