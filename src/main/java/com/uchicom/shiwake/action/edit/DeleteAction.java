// (c) 2012 uchicom
package com.uchicom.shiwake.action.edit;

import java.awt.event.ActionEvent;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.table.ListTableModel;
import com.uchicom.shiwake.window.ShiwakeFrame;

/**
 * 行を削除する
 * @author Uchiyama Shigeki
 *
 */
public class DeleteAction extends ConfirmAction {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DeleteAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
	}

	/* (non-Javadoc)
	 * @see com.uchicom.shiwake.action.ConfirmAction#action(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent e) {
		ListTableModel model = shiwakeFrame.getModel();
		int[] rowIndexs = shiwakeFrame.getSelectedRows();
		if (rowIndexs.length != 0) {
		    model.removeRows(rowIndexs);
			shiwakeFrame.clearSelection();
		}
	}

}
