// (c) 2012 uchicom
package com.uchicom.jio.action.edit;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.table.ListTableModel;
import com.uchicom.jio.window.JournalFrame;

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

	public DeleteAction(JournalFrame journalFrame) {
		super(journalFrame);
	}

	/* (non-Javadoc)
	 * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent e) {
		if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(uiStore.getMainComponent(), uiStore.getResourceBundle().getString("message.delete"))) {
			ListTableModel model = journalFrame.getModel();
			int[] rowIndexs = journalFrame.getSelectedRows();
			if (rowIndexs.length != 0) {
			    model.removeRows(rowIndexs);
				journalFrame.clearSelection();
			}
		}
	}

}
