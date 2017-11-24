// (c) 2013 uchicom
package com.uchicom.jio.table;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import com.uchicom.jio.bean.Account;
import com.uchicom.jio.bean.Transaction;
import com.uchicom.jio.enums.TransactionType;
import com.uchicom.jio.window.JournalFrame;

/**
 * 取引のセルエディター 勘定科目は貸方借方両方で使用できる 数字を入れた場合はIDを検索、 数字以外を入れた場合は名称を検索
 *
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class TransactionTableCellEditor implements TableCellEditor {

	private List<CellEditorListener> listenerList = new LinkedList<CellEditorListener>();
	JTable table;
	JournalFrame journalFrame;
	/** 編集用コンボボックス */
	private JComboBox<Account> comboBox;
	DefaultCellEditor comboBoxCellEditor;
	private Vector<Account> accountList;
	DefaultCellEditor defaultCellEditor;
	TransactionType type;
	public TransactionTableCellEditor(Vector<Account> accountList, TransactionType type, JournalFrame journalFrame) {
		this.type = type;
		this.accountList = accountList;
		this.journalFrame = journalFrame;
		ComboBoxModel<Account> model = new DefaultComboBoxModel<>(accountList);
		comboBox = new JComboBox<>(model);

		comboBox.setEditable(true);
		// comboBox.getEditor().addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// System.out.println("actioneditor" + e);
		// }
		// });
		// comboBox.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// System.out.println("actioncombobox" + e);
		//
		// }
		// });
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println("itemStateChanged" + arg0);
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					if (list != null && list.size() == 1) {
						Transaction bean = list.get(0);
						Object object = comboBox.getSelectedItem();
						Account account = null;
						if (object instanceof Account) {
							account = (Account) object;
						} else {
							String editValue = object.toString();
							for (Account tmp : accountList) {
								if (editValue.equals(tmp.getName())) {
									account = tmp;
									break;
								}
							}
							if (account == null) {
								account = new Account(null, editValue, null);
								accountList.add(account);
							}
						}
						// コンボボックスから他のセルを選択して抜ける場合に値が設定されないのでその対策
						bean.setAccount(account);
					} else {
						Object object = comboBox.getSelectedItem();
						Account account = null;
						if (object instanceof Account) {
							account = (Account) object;
						} else {
							String editValue = object.toString();
							for (Account bean : accountList) {
								if (editValue.equals(bean.getName())) {
									account = bean;
									break;
								}
							}
							if (account == null) {
								account = new Account(null, editValue, null);
								accountList.add(account);
							}
						}
						comboBox.setSelectedItem(account);
					}
				}
			}

		});
		// comboBox.addFocusListener(new FocusListener() {
		//
		// @Override
		// public void focusLost(FocusEvent e) {
		// // TODO 自動生成されたメソッド・スタブ
		// System.out.println("focusLost:combobox" + e);
		// }
		//
		// @Override
		// public void focusGained(FocusEvent e) {
		// // TODO 自動生成されたメソッド・スタブ
		// System.out.println("focusGained:comboox" + e);
		// }
		// });

		JTextField textField = new JTextField();
		textField.setDocument(new LongDocument());
		textField.setFocusCycleRoot(false);
		TransactionTableModel tableModel = new TransactionTableModel();
		DefaultTableColumnModel columnModel = new DefaultTableColumnModel();
		TableColumn tableColumn = new TableColumn(0);
		columnModel.addColumn(tableColumn);

		comboBoxCellEditor = new DefaultCellEditor(comboBox) {

			/*
			 * (non-Javadoc)
			 *
			 * @see javax.swing.CellEditor#getCellEditorValue()
			 */
			@Override
			public Object getCellEditorValue() {
				Object object = comboBox.getEditor().getItem();
				System.out.println("getCellEditorValue:" + object);
				System.out.println("getCellEditorValue:" + comboBox.getSelectedItem());
				System.out.println("getCellEditorValue:" + comboBox.getEditor().getItem());


				Account account = null;
				if (object instanceof Account) {
					account = (Account) object;
				} else {
					String editValue = object.toString();
					for (Account bean : accountList) {
						if (editValue.equals(bean.getName())) {
							account = bean;
							break;
						}
					}
					if (account == null) {
						account = new Account(null, editValue, null);
						accountList.add(account);
					}
				}
				return account;
			}

		};
		comboBoxCellEditor.setClickCountToStart(2);
		tableColumn.setCellEditor(comboBoxCellEditor);

		tableColumn = new TableColumn(1);
		columnModel.addColumn(tableColumn);

		defaultCellEditor = new DefaultCellEditor(textField);
		defaultCellEditor.setClickCountToStart(2);
		tableColumn.setCellEditor(defaultCellEditor);

		table = new JTable(tableModel, columnModel);

		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFocusCycleRoot(false);
		comboBox.setFocusCycleRoot(false);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.CellEditor#addCellEditorListener(javax.swing.event.
	 * CellEditorListener)
	 */
	@Override
	public void addCellEditorListener(CellEditorListener l) {
		System.out.println("addCellEditorListener");

		listenerList.add(l);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.CellEditor#cancelCellEditing()
	 */
	@Override
	public void cancelCellEditing() {
		System.out.println("cancelCellEditing");
		List<CellEditorListener> cancelList = new ArrayList<CellEditorListener>();
		cancelList.addAll(listenerList);
		for (CellEditorListener listener : cancelList) {
			listener.editingStopped(new ChangeEvent(table));
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	@Override
	public Object getCellEditorValue() {
		System.out.println("getCellEditorValue");
		returnComponent = null;
		return list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.CellEditor#isCellEditable(java.util.EventObject)
	 */
	@Override
	public boolean isCellEditable(EventObject anEvent) {

		if (!(returnComponent instanceof JTable)) {
			if (anEvent instanceof MouseEvent) {
				MouseEvent mouseEvent = (MouseEvent) anEvent;
				return mouseEvent.getClickCount() >= 2;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.CellEditor#removeCellEditorListener(javax.swing.event.
	 * CellEditorListener)
	 */
	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		System.out.println("removeCellEditorListener:TransactionTableCellEditor");
		listenerList.remove(l);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.CellEditor#shouldSelectCell(java.util.EventObject)
	 */
	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		System.out.println("shouldSelectCell:TransactionTableCellEditor");
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.CellEditor#stopCellEditing()
	 */
	@Override
	public boolean stopCellEditing() {
		System.out.println("stopCellEditing");
		if (returnComponent instanceof JTable) {
			System.out.println("jtable");
			comboBoxCellEditor.stopCellEditing();
			defaultCellEditor.stopCellEditing();
			table.editingStopped(new ChangeEvent(table));
			journalFrame.getTable().editingStopped(new ChangeEvent(journalFrame.getTable()));
//			table.repaint();
		} else {
			System.out.println("not jtable");
			journalFrame.getTable().editingStopped(new ChangeEvent(journalFrame.getTable()));
			//jioFrame.getModel().fireTableDataChanged();
		}
//		System.out.println("stopCellEditing:TransactionTableCellEditor");
//		System.out.println(comboBox.getSelectedIndex());
//		if (comboBox.getSelectedItem() != null) {
//			System.out.println("a:" + comboBox.getSelectedItem());
//			System.out.println("b:" + comboBoxCellEditor.getCellEditorValue());
//			System.out.println("c:" + comboBox.getEditor().getItem());
//
//			List<CellEditorListener> stopList = new ArrayList<CellEditorListener>();
//			stopList.addAll(listenerList);
//
//			for (CellEditorListener listener : stopList) {
//				listener.editingStopped(new ChangeEvent(table));
//			}
//
//			// table表示時
//			// comboBoxCellEditorのストップが呼ばれないようなので、こちらから呼ぶ。
//			if (returnComponent instanceof JTable) {
//				comboBoxCellEditor.stopCellEditing();
//				table.editingStopped(new ChangeEvent(table));
//				table.repaint();
//			} else {
//				System.out.println(comboBox.getSelectedItem().toString());
//				if (list == null) {
//					// エラー
//					System.out.println("listがおかしい");
//				} else {
//
//					Account account = null;
//					if (comboBox.getSelectedIndex() > 0) {
//						account = (Account) comboBox.getSelectedItem();
//					} else {
//						String editValue = comboBox.getEditor().getItem().toString();
//						for (Account bean2 : accountList) {
//							if (editValue.equals(bean2.getName())) {
//								account = bean2;
//								break;
//							}
//						}
//						if (account == null) {
//							account = new Account(null, editValue, null);
//							accountList.add(account);
//						}
//					}
//				}
//			}
//		} else {
//			System.out.println("selectedItem = null");
//		}

		return true;
	}

	Component returnComponent = null;
	List<Transaction> list = null;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing
	 * .JTable, java.lang.Object, boolean, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		System.out.println("getTableCellEditorComponent" + row + ",");

		list = (List<Transaction>) value;
		if (list == null) {
			comboBox.setSelectedItem(null);
			returnComponent = comboBox;
		} else if (list.size() > 1) {

			TransactionTableModel model = (TransactionTableModel) this.table.getModel();
			model.setRowList(list);
			returnComponent = this.table;
		} else {
			if (list.size() == 1) {
				Transaction bean = list.get(0);
				if (bean.getAccount() != null) {
					comboBox.setSelectedItem(bean.getAccount());
					// comboBox.getEditor().setItem(bean.getAccount().getName());
				} else {
					comboBox.setSelectedItem(null);
				}
				returnComponent = comboBox;
			} else {
				comboBox.setSelectedItem(null);
				returnComponent = comboBox;
			}
		}
		System.out.println(accountList);
		System.out.println(returnComponent);
		return returnComponent;
	}

}
