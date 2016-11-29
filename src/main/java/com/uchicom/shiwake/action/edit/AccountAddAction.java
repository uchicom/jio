// (c) 2013 uchicom
package com.uchicom.shiwake.action.edit;

import java.awt.event.ActionEvent;

import com.uchicom.shiwake.window.AccountListBook;
import com.uchicom.shiwake.window.ShiwakeFrame;
import com.uchicom.ui.action.AbstractResourceAction;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class AccountAddAction extends AbstractResourceAction<ShiwakeFrame> {

    private AccountListBook accountListBook;

    public AccountAddAction(ShiwakeFrame shiwakeFrame, AccountListBook accountListBook) {
    	super(shiwakeFrame);
        putValue(NAME, "追加");
        this.accountListBook = accountListBook;
    }
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        accountListBook.add();
    }

}
