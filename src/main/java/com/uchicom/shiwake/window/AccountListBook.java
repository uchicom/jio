// (c) 2013 uchicom
package com.uchicom.shiwake.window;

import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.uchicom.shiwake.action.edit.AccountAddAction;
import com.uchicom.shiwake.action.edit.AccountRemoveAction;
import com.uchicom.shiwake.bean.Account;
import com.uchicom.shiwake.table.AccountTableModel;
import com.uchicom.shiwake.table.LongDocument;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class AccountListBook extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTable table;
    private AccountTableModel model;
    private ShiwakeFrame shiwakeFrame;

    public AccountListBook(ShiwakeFrame shiwakeFrame) {
        super("勘定科目一覧");
        this.shiwakeFrame = shiwakeFrame;
    }

    public void initComponents() {

        // 動作設定
        initBehaviour();

        // メニュー生成
        initMenuBar();

        // 画面作成
        initView();


    }

    private void initBehaviour() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    /**
     * メニューバーを初期化する.
     */
    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("編集(E)");
        menu.setMnemonic(KeyEvent.VK_E);
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem(new AccountAddAction(shiwakeFrame, this));
        menu.add(menuItem);
        menuItem = new JMenuItem(new AccountRemoveAction(shiwakeFrame, this));
        menu.add(menuItem);
//        // 区切り線
//        menu.addSeparator();
//        // コピーペースト機能
//        menuItem = new JMenuItem(new AccountCopyAction(this));
//        menu.add(menuItem);
//        menuItem = new JMenuItem(new AccountPasteAction(this));
//        menu.add(menuItem);

//        menu = new JMenu("ヘルプ(H)");
//        menu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(menu);

    }

    public void initView() {
        // データ格納リスト

        // テーブルモデル
        model = new AccountTableModel(shiwakeFrame.getAccountList(), 4);
        // テーブル
        table = new JTable(model, createTableColumnModel());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);
    }

    public TableColumnModel createTableColumnModel() {
        JTextField longField = new JTextField();
        longField.setDocument(new LongDocument());
        longField.setHorizontalAlignment(JTextField.RIGHT);
        JTextField textField = new JTextField();

        DefaultTableColumnModel columnModel = new DefaultTableColumnModel();

        TableColumn tableColumn = new TableColumn(0);
        tableColumn.setHeaderValue("ID");
        columnModel.addColumn(tableColumn);
        tableColumn.setCellEditor(new DefaultCellEditor(longField));
        tableColumn = new TableColumn(1);
        tableColumn.setHeaderValue("勘定科目コード");
        columnModel.addColumn(tableColumn);
        tableColumn.setCellEditor(new DefaultCellEditor(textField));
        tableColumn = new TableColumn(2);
        tableColumn.setHeaderValue("勘定科目名");
        columnModel.addColumn(tableColumn);
        tableColumn.setCellEditor(new DefaultCellEditor(textField));
        tableColumn = new TableColumn(3);
        tableColumn.setHeaderValue("勘定科目グループ");
        columnModel.addColumn(tableColumn);
        tableColumn.setCellEditor(new DefaultCellEditor(textField));
        tableColumn = new TableColumn(4);
        tableColumn.setHeaderValue("貸借対照表表示順");
        columnModel.addColumn(tableColumn);
        tableColumn.setCellEditor(new DefaultCellEditor(textField));
        return columnModel;
    }

    public void add() {
        int[] rowIndexs = table.getSelectedRows();
        if (rowIndexs.length == 0) {
            rowIndexs = new int[]{0};
        }
        model.addRows(rowIndexs);
    }

    public void remove() {

        int[] rowIndexs = table.getSelectedRows();
        if (rowIndexs.length != 0) {
            model.removeRows(rowIndexs);
            table.getSelectionModel().clearSelection();
            if (table.getCellEditor() != null) {
                table.getCellEditor().cancelCellEditing();
            } else {
                System.out.println("cellエディターがぬる");
            }
            table.clearSelection();
        }
    }

    public void setRowList(List<Account> beanList) {
        model.setRowList(beanList);
    }

}
