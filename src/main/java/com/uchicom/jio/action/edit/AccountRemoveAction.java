// (c) 2013 uchicom
package com.uchicom.jio.action.edit;

import java.awt.event.ActionEvent;

import com.uchicom.jio.window.AccountListBook;
import com.uchicom.jio.window.JournalFrame;
import com.uchicom.ui.action.AbstractResourceAction;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class AccountRemoveAction extends AbstractResourceAction<JournalFrame> {

    private AccountListBook accountListBook;

    public AccountRemoveAction(JournalFrame journalFrame, AccountListBook accountListBook) {
    	super(journalFrame);
        putValue(NAME, "削除");
        this.accountListBook = accountListBook;
    }
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        accountListBook.remove();

    }

}
