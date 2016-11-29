// (c) 2012 uchicom
package com.uchicom.shiwake.action.edit;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.table.ListTableModel;
import com.uchicom.shiwake.window.ShiwakeFrame;

/**
 * 行を追加する
 *
 * @author Uchiyama Shigeki
 *
 */
public class InsertAction extends ConfirmAction {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public InsertAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
	}

	/* (non-Javadoc)
	 * @see com.uchicom.shiwake.action.ConfirmAction#action(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent arg0) {
		if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(uiStore.getMainComponent(), uiStore.getResourceBundle().getString("message.add"))) {
			ListTableModel model = shiwakeFrame.getModel();
			int[] rowIndexs = shiwakeFrame.getSelectedRows();
			if (rowIndexs.length == 0) {
				model.addRow();
			} else {
				model.addRows(rowIndexs);
			}
		}
	}

}
