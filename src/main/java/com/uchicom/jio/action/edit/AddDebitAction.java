// (c) 2012 uchicom
package com.uchicom.jio.action.edit;

import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.JOptionPane;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.bean.Journal;
import com.uchicom.jio.table.ListTableModel;
import com.uchicom.jio.window.JournalFrame;

public class AddDebitAction extends ConfirmAction {
	private static final long serialVersionUID = 1L;

	public AddDebitAction(JournalFrame journalFrame) {
		super(journalFrame);
	}

	/* (non-Javadoc)
	 * @see com.uchicom.jio.action.ConfirmAction#action(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent e) {

		ListTableModel model = journalFrame.getModel();
		int[] rowIndexs = journalFrame.getSelectedRows();
		if (rowIndexs.length > 0) {
			for (int index = 0; index < rowIndexs.length; index++) {
				int rowIndex = rowIndexs[index];
				Journal bean = model.getRowList().get(rowIndex);
				if (bean.getCreditList().size() > 1) {
					JOptionPane.showMessageDialog(journalFrame, "多対多の複合仕分けは対応していません。");
					Arrays.copyOfRange(rowIndexs, rowIndex, rowIndexs.length - 1);
				} else {
					model.addDebitRows(rowIndexs);
				}
			}
		} else {
			//そもそもメニューで選択できないようにしたい。TODO
			//1対多の仕訳の場合は総金額を編集不可にしたいなTODO
			JOptionPane.showMessageDialog(journalFrame, "仕訳を選択してください.");
		}
	}

}
