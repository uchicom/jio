// (c) 2016 uchicom
package com.uchicom.shiwake.action.help;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.window.ShiwakeFrame;

public class HelpAction extends ConfirmAction {

	public HelpAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.uchicom.shiwake.action.ConfirmAction#action(java.awt.event.
	 * ActionEvent)
	 */
	@Override
	public void action(ActionEvent arg0) {

		Desktop desktop = Desktop.getDesktop();

		try {
			desktop.browse(new URI("http://labs.uchicom.com/help/shiwake/"));
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (URISyntaxException e2) {
			e2.printStackTrace();
		}
	}

}
