// (C) 2022 uchicom
package com.uchicom.jio.ui.factory;

import com.uchicom.jio.action.edit.*;
import com.uchicom.jio.action.file.*;
import com.uchicom.jio.action.help.*;
import com.uchicom.jio.action.window.*;
import com.uchicom.jio.ui.window.JournalBook;
import java.awt.event.*;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ResourceFactory {
  private static final Logger logger = Logger.getLogger(ResourceFactory.class.getCanonicalName());

  public JMenuBar createMenuBar(JournalBook journalBook) {
    ResourceBundle resourceBundle = journalBook.getResourceBundle();
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu(resourceBundle.getString("menu.file.NAME"));
    menu.setMnemonic(KeyEvent.VK_F);
    menuBar.add(menu);

    JMenuItem menuItem = new JMenuItem(new OpenAction(journalBook));
    menu.add(menuItem);
    menuItem = new JMenuItem(new CreateAction(journalBook));
    menu.add(menuItem);
    menuItem = new JMenuItem(new SaveAction(journalBook));
    menu.add(menuItem);
    menu.addSeparator();

    menu.add(new JMenuItem(new ImportAction(journalBook)));
    menu.add(new JMenuItem(new ImportAccountAction(journalBook)));
    menu.add(new JMenuItem(new ExportAccountAction(journalBook)));
    menu.addSeparator();

    menuItem = new JMenuItem(new PrintAction(journalBook));
    menu.add(menuItem);
    menu.addSeparator();
    menuItem = new JMenuItem(new CloseAction(journalBook));
    menu.add(menuItem);

    menu = new JMenu(resourceBundle.getString("menu.edit.NAME"));
    menu.setMnemonic(KeyEvent.VK_E);
    menuBar.add(menu);

    menuItem = new JMenuItem(new InsertAction(journalBook));
    menu.add(menuItem);
    menuItem = new JMenuItem(new DeleteAction(journalBook));
    menu.add(menuItem);
    // 区切り線
    menu.addSeparator();
    menuItem = new JMenuItem(new AddDebitAction(journalBook));
    menu.add(menuItem);
    menuItem = new JMenuItem(new DeleteDebitAction(journalBook));
    menu.add(menuItem);
    menu.addSeparator();
    menuItem = new JMenuItem(new AddCreditAction(journalBook));
    menu.add(menuItem);
    menuItem = new JMenuItem(new DeleteCreditAction(journalBook));
    menu.add(menuItem);
    // 区切り線
    menu.addSeparator();
    // コピーペースト機能
    menuItem = new JMenuItem(new CopyAction(journalBook));
    menu.add(menuItem);
    menuItem = new JMenuItem(new PasteAction(journalBook));
    menu.add(menuItem);

    menu = new JMenu(resourceBundle.getString("menu.window.NAME"));
    menu.setMnemonic(KeyEvent.VK_W);
    menuBar.add(menu);

    menuItem = new JMenuItem(new CashAction(journalBook));
    menu.add(menuItem);
    menuItem = new JMenuItem(new AccountsRecAction(journalBook));
    menu.add(menuItem);
    menuItem = new JMenuItem(new AccountsPayAction(journalBook));
    menu.add(menuItem);
    menuItem = new JMenuItem(new CostAction(journalBook));
    menu.add(menuItem);
    menu.addSeparator();
    menuItem = new JMenuItem(new AccountListAction(journalBook));
    menu.add(menuItem);
    menu.addSeparator();
    menuItem = new JMenuItem(new MonthlySalesAction(journalBook));
    menu.add(menuItem);
    menuItem = new JMenuItem(new MonthlyPurchaseAction(journalBook));
    menu.add(menuItem);
    menu.addSeparator();
    menuItem = new JMenuItem(new BalanceAction(journalBook));
    menu.add(menuItem);
    menuItem = new JMenuItem(new ProfitAction(journalBook));
    menu.add(menuItem);

    menu = new JMenu(resourceBundle.getString("menu.tool.NAME"));
    menu.setMnemonic(KeyEvent.VK_T);
    menuBar.add(menu);

    menu = new JMenu(resourceBundle.getString("menu.help.NAME"));
    menu.setMnemonic(KeyEvent.VK_H);

    menuItem = new JMenuItem(new HelpAction(journalBook));
    menu.add(menuItem);
    menuItem = new JMenuItem(new VersionAction(journalBook));
    menu.add(menuItem);
    menuBar.add(menu);

    menuBar.addFocusListener(
        new FocusListener() {

          @Override
          public void focusLost(FocusEvent e) {
            logger.info("JTable.focusLost");
          }

          @Override
          public void focusGained(FocusEvent e) {
            logger.info("JMenuBar.focusGained");
          }
        });

    journalBook.addFocusListener(
        new FocusListener() {

          @Override
          public void focusLost(FocusEvent e) {
            logger.info("main.focusLost");
          }

          @Override
          public void focusGained(FocusEvent e) {
            logger.info("main.focusGained");
          }
        });
    journalBook.addWindowFocusListener(
        new WindowFocusListener() {

          @Override
          public void windowLostFocus(WindowEvent e) {
            logger.info("weLostFocus:" + e);
          }

          @Override
          public void windowGainedFocus(WindowEvent e) {
            logger.info("weLostGained:" + e);
          }
        });
    return menuBar;
  }

  public JPopupMenu createPopupMenu(JournalBook journalBook) {
    JPopupMenu popup = new JPopupMenu();

    JMenuItem menuItem = new JMenuItem(new InsertAction(journalBook));
    popup.add(menuItem);
    menuItem = new JMenuItem(new DeleteAction(journalBook));
    popup.add(menuItem);
    // 区切り線
    popup.addSeparator();
    menuItem = new JMenuItem(new AddDebitAction(journalBook));
    popup.add(menuItem);
    menuItem = new JMenuItem(new AddCreditAction(journalBook));
    popup.add(menuItem);
    // 区切り線
    popup.addSeparator();
    // コピーペースト機能
    menuItem = new JMenuItem(new CopyAction(journalBook));
    popup.add(menuItem);
    menuItem = new JMenuItem(new PasteAction(journalBook));
    popup.add(menuItem);
    return popup;
  }
}
