// (c) 2013 uchicom
package com.uchicom.shiwake.action.window;

import java.awt.event.ActionEvent;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.window.ShiwakeFrame;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class AccountListAction extends ConfirmAction {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AccountListAction(ShiwakeFrame shiwakeFrame) {
    	super(shiwakeFrame);
    }

	/* (non-Javadoc)
	 * @see com.uchicom.shiwake.action.ConfirmAction#action(java.awt.event.ActionEvent)
	 */
    @Override
    public void action(ActionEvent arg0) {
        shiwakeFrame.showAccountList();
    }

}
