// (C) 2012 uchicom
package com.uchicom.jio.ui.window;

import com.uchicom.csve.util.CSVReader;
import com.uchicom.jio.action.edit.AddCreditAction;
import com.uchicom.jio.action.edit.AddDebitAction;
import com.uchicom.jio.action.edit.CopyAction;
import com.uchicom.jio.action.edit.DeleteAction;
import com.uchicom.jio.action.edit.DeleteCreditAction;
import com.uchicom.jio.action.edit.DeleteDebitAction;
import com.uchicom.jio.action.edit.InsertAction;
import com.uchicom.jio.action.edit.PasteAction;
import com.uchicom.jio.action.file.CloseAction;
import com.uchicom.jio.action.file.CreateAction;
import com.uchicom.jio.action.file.ExportAccountAction;
import com.uchicom.jio.action.file.ImportAccountAction;
import com.uchicom.jio.action.file.ImportAction;
import com.uchicom.jio.action.file.OpenAction;
import com.uchicom.jio.action.file.PrintAction;
import com.uchicom.jio.action.file.SaveAction;
import com.uchicom.jio.action.help.HelpAction;
import com.uchicom.jio.action.help.VersionAction;
import com.uchicom.jio.action.window.AccountListAction;
import com.uchicom.jio.action.window.AccountsPayAction;
import com.uchicom.jio.action.window.AccountsRecAction;
import com.uchicom.jio.action.window.BalanceAction;
import com.uchicom.jio.action.window.CashAction;
import com.uchicom.jio.action.window.CostAction;
import com.uchicom.jio.action.window.MonthlyPurchaseAction;
import com.uchicom.jio.action.window.MonthlySalesAction;
import com.uchicom.jio.action.window.ProfitAction;
import com.uchicom.jio.bean.Account;
import com.uchicom.jio.bean.Journal;
import com.uchicom.jio.bean.Transaction;
import com.uchicom.jio.enums.TransactionType;
import com.uchicom.jio.ui.table.JournalTableCellRenderer;
import com.uchicom.jio.ui.table.ListTableModel;
import com.uchicom.jio.ui.table.LongDocument;
import com.uchicom.jio.ui.table.TextAreaCellEditor;
import com.uchicom.jio.ui.table.TransactionTableCellEditor;
import com.uchicom.jio.util.ZipCSVReader;
import com.uchicom.ui.util.DialogUtil;
import com.uchicom.ui.util.UIStore;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.undo.UndoManager;

/**
 * 仕訳一覧画面.
 *
 * @author Uchiyama Shigeki
 */
public class JournalBook extends JFrame implements UIStore<JournalBook> {
  /** ロガー */
  private static final Logger logger = Logger.getLogger(JournalBook.class.getCanonicalName());
  /** シリアルバージョン(現状使う想定なし) */
  private static final long serialVersionUID = 1L;

  /** 画面タイトル */
  private static final String TITLE_NAME = "jio";

  /** 設定プロパティーファイルの相対パス */
  private static final String CONF_FILE_PATH = "conf/jio.properties";

  /** 設定プロパティーファイルのコメント */
  private static final String PROP_COMMENT = "jio.properties";

  /** 画面の初期位置保持キー */
  private static final String PROP_KEY_JOURNAL_WINDOW = "journalWindow";
  /** 画面の初期位置保持キー */
  private static final String PROP_KEY_ACCOUNT_WINDOW = "accountWindow";
  /** 画面の初期位置保持キー */
  public static final String PROP_KEY_CASHBOOK_WINDOW = "cashbookWindow";
  /** 画面の初期位置保持キー */
  public static final String PROP_KEY_ACCOUNT_REC_WINDOW = "accountRecWindow";
  /** 画面の初期位置保持キー */
  public static final String PROP_KEY_ACCOUNT_PAY_WINDOW = "accountPayWindow";
  /** 画面の初期位置保持キー */
  public static final String PROP_KEY_COST_WINDOW = "costWindow";
  /** 画面の初期位置保持キー */
  public static final String PROP_KEY_MONTHLY_SALES_WINDOW = "monthlySalesWindow";
  /** 画面の初期位置保持キー */
  public static final String PROP_KEY_MONTHLY_PURCHASE_WINDOW = "monthlyPurchaseWindow";
  /** 画面の初期位置保持キー */
  private static final String PROP_KEY_BALANCE_WINDOW = "balanceWindow";
  /** 画面の初期位置保持キー */
  private static final String PROP_KEY_PROFIT_WINDOW = "profitWindow";

  /** 画面の初期位置保持キー */
  private static final String PROP_KEY_FILE = "file";
  /** 画面の初期位置保持キー */
  private static final String PROP_KEY_LOOK_AND_FEEL = "lookAndFeel";

  /** プロパティの分割用文字 */
  private static final String PROP_SPLIT_CHAR = ":";

  /** 仕訳表の列サイズ保持キー */
  private static final String PROP_KEY_JOURNAL_TABLE = "journalTable";
  /** */
  private Properties prop = new Properties();

  private Properties actionResource = new Properties();

  private static final ResourceBundle resourceBundle =
      ResourceBundle.getBundle("com.uchicom.jio.resource");

  /** 勘定リスト */
  Vector<Account> accountList = new Vector<>();

  /** 借方 */
  private TableCellEditor debitCellEditor =
      new TransactionTableCellEditor(accountList, TransactionType.Debit, this);
  /** 貸方 */
  private TableCellEditor creditCellEditor =
      new TransactionTableCellEditor(accountList, TransactionType.Credit, this);

  List<Journal> journalList = new ArrayList<Journal>();
  /** テーブル */
  private JTable table;

  private JPopupMenu popup;
  /** テーブルモデル */
  private ListTableModel model;

  private File selectedFile;

  /** コンストラクタ */
  public JournalBook() {
    super(TITLE_NAME);
    initComponents();
  }

  /** 画面コンポーネントの初期化 */
  public void initComponents() {
    // アイコン
    setIconImage(
        new ImageIcon(getClass().getClassLoader().getResource("com/uchicom/jio/icon.png"))
            .getImage());

    try (FileInputStream fis = new FileInputStream("conf/jioAction.properties")) {
      actionResource.load(fis);
    } catch (FileNotFoundException e) {
      logger.log(Level.SEVERE, "初期設定ファイルが見つかりません.", e);
    } catch (IOException e) {
      logger.log(Level.SEVERE, "初期設定ファイルが読み込み時にIOエラーが発生しました.", e);
    }

    initAccountView();
    // 設定プロパティーファイル読み込み
    initProperty();

    // 子画面は生成しておく
    initBook();

    // 動作設定
    initBehaviour();

    // メニューバー生成
    initMenuBar();

    // ポップアップメニュー生成
    initPopupMenu();

    // 画面作成
    initView();

    if (selectedFile != null && selectedFile.exists()) {
      openFile();
    }
  }

  /** プロパティー情報の初期化 */
  private void initProperty() {

    File file = new File(CONF_FILE_PATH);
    if (file.exists()) {

      try (FileInputStream fis = new FileInputStream(file); ) {

        prop.load(fis);
        // 画面の初期位置取得
        setWindowSize(JournalBook.this, PROP_KEY_JOURNAL_WINDOW);
        setWindowSize(accountListBook, PROP_KEY_ACCOUNT_WINDOW);
        setWindowSize(profitBook, PROP_KEY_PROFIT_WINDOW);
        // 子画面の初期位置取得

        // 初期ファイル
        if (prop.containsKey(PROP_KEY_FILE)) {
          String filePath = prop.getProperty(PROP_KEY_FILE);
          File initFile = new File(filePath);
          if (initFile.exists() && initFile.isFile()) {
            selectedFile = initFile;
          }
        }
        // ウィンドウスタイル
        if (prop.containsKey(PROP_KEY_LOOK_AND_FEEL)) {
          String lookAndFeel = prop.getProperty(PROP_KEY_LOOK_AND_FEEL);
          try {
            UIManager.setLookAndFeel(lookAndFeel);
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          } catch (InstantiationException e) {
            e.printStackTrace();
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /** 画面の振る舞いを初期化する. */
  private void initBehaviour() {
    // WindowListnerを登録する
    addWindowListener(
        new WindowListener() {

          @Override
          public void windowActivated(WindowEvent arg0) {
            // 実装なし
          }

          @Override
          public void windowClosed(WindowEvent arg0) {
            // 実装なし
          }

          @Override
          public void windowClosing(WindowEvent arg0) {
            // 設定プロパティファイル書き込み
            File file = new File(CONF_FILE_PATH);

            try (FileOutputStream fos = new FileOutputStream(file)) {
              // 画面の位置を保持する
              setWindowProperty(JournalBook.this, PROP_KEY_JOURNAL_WINDOW);
              setWindowProperty(accountListBook, PROP_KEY_ACCOUNT_WINDOW);
              setWindowProperty(bookMap.get(PROP_KEY_CASHBOOK_WINDOW), PROP_KEY_CASHBOOK_WINDOW);
              setWindowProperty(
                  bookMap.get(PROP_KEY_ACCOUNT_REC_WINDOW), PROP_KEY_ACCOUNT_REC_WINDOW);
              setWindowProperty(
                  bookMap.get(PROP_KEY_ACCOUNT_PAY_WINDOW), PROP_KEY_ACCOUNT_PAY_WINDOW);
              setWindowProperty(bookMap.get(PROP_KEY_COST_WINDOW), PROP_KEY_COST_WINDOW);
              setWindowProperty(
                  monthlyBookMap.get(PROP_KEY_MONTHLY_SALES_WINDOW), PROP_KEY_MONTHLY_SALES_WINDOW);
              setWindowProperty(
                  monthlyBookMap.get(PROP_KEY_MONTHLY_PURCHASE_WINDOW),
                  PROP_KEY_MONTHLY_PURCHASE_WINDOW);
              setWindowProperty(balanceBook, PROP_KEY_BALANCE_WINDOW);
              setWindowProperty(profitBook, PROP_KEY_PROFIT_WINDOW);

              // テーブルカラムの初期幅
              String tableColumnWidth =
                  table.getColumn(0).getWidth()
                      + PROP_SPLIT_CHAR
                      + table.getColumn(1).getWidth()
                      + PROP_SPLIT_CHAR
                      + table.getColumn(2).getWidth()
                      + PROP_SPLIT_CHAR
                      + table.getColumn(3).getWidth()
                      + PROP_SPLIT_CHAR
                      + table.getColumn(4).getWidth();
              prop.setProperty(PROP_KEY_JOURNAL_TABLE, tableColumnWidth);

              if (selectedFile != null) {
                prop.setProperty(PROP_KEY_FILE, selectedFile.getCanonicalPath());
              }

              logger.info(UIManager.getLookAndFeel().getClass().getCanonicalName());
              // ルックアンドフィール
              prop.setProperty(
                  PROP_KEY_LOOK_AND_FEEL, UIManager.getLookAndFeel().getClass().getCanonicalName());

              // 設定プロパティファイルが存在しない場合は作成する
              if (!file.exists()) {
                file.createNewFile();
              }
              prop.store(fos, PROP_COMMENT);
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            } catch (IOException e) {
              e.printStackTrace();
            }
            JournalBook.this.dispose();
          }

          @Override
          public void windowDeactivated(WindowEvent arg0) {
            // 実装なし
          }

          @Override
          public void windowDeiconified(WindowEvent arg0) {
            // 実装なし
          }

          @Override
          public void windowIconified(WindowEvent arg0) {
            // 実装なし
          }

          @Override
          public void windowOpened(WindowEvent arg0) {
            // 実装なし
          }
        });
  }

  /**
   * 画面のサイズをプロパティから設定する。
   *
   * @param frame
   * @param key
   */
  public void setWindowSize(JFrame frame, String key) {
    if (prop.containsKey(key)) {
      String initPoint = prop.getProperty(key);
      String[] points = initPoint.split(PROP_SPLIT_CHAR);
      if (points.length == 4) {
        frame.setLocation(Integer.parseInt(points[0]), Integer.parseInt(points[1]));
        frame.setPreferredSize(
            new Dimension(Integer.parseInt(points[2]), Integer.parseInt(points[3])));
      }
    }
  }

  /**
   * 画面の位置をプロパティに設定する。
   *
   * @param frame
   * @param key
   */
  public void setWindowProperty(JFrame frame, String key) {
    String value =
        frame.getLocation().x
            + PROP_SPLIT_CHAR
            + frame.getLocation().y
            + PROP_SPLIT_CHAR
            + frame.getWidth()
            + PROP_SPLIT_CHAR
            + frame.getHeight();
    prop.setProperty(key, value);
  }

  /** メニューバーを初期化する. */
  private void initMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu menu = new JMenu("ファイル(F)");
    menu.setMnemonic(KeyEvent.VK_F);
    menuBar.add(menu);

    JMenuItem menuItem = new JMenuItem(new OpenAction(this));
    menu.add(menuItem);
    menuItem = new JMenuItem(new CreateAction(this));
    menu.add(menuItem);
    menuItem = new JMenuItem(new SaveAction(this));
    menu.add(menuItem);
    menu.addSeparator();

    menu.add(new JMenuItem(new ImportAction(this)));
    menu.add(new JMenuItem(new ImportAccountAction(this)));
    menu.add(new JMenuItem(new ExportAccountAction(this)));
    menu.addSeparator();

    menuItem = new JMenuItem(new PrintAction(this));
    menu.add(menuItem);
    menu.addSeparator();
    menuItem = new JMenuItem(new CloseAction(this));
    menu.add(menuItem);

    menu = new JMenu("編集(E)");
    menu.setMnemonic(KeyEvent.VK_E);
    menuBar.add(menu);

    menuItem = new JMenuItem(new InsertAction(this));
    menu.add(menuItem);
    menuItem = new JMenuItem(new DeleteAction(this));
    menu.add(menuItem);
    // 区切り線
    menu.addSeparator();
    menuItem = new JMenuItem(new AddDebitAction(this));
    menu.add(menuItem);
    menuItem = new JMenuItem(new DeleteDebitAction(this));
    menu.add(menuItem);
    menu.addSeparator();
    menuItem = new JMenuItem(new AddCreditAction(this));
    menu.add(menuItem);
    menuItem = new JMenuItem(new DeleteCreditAction(this));
    menu.add(menuItem);
    // 区切り線
    menu.addSeparator();
    // コピーペースト機能
    menuItem = new JMenuItem(new CopyAction(this));
    menu.add(menuItem);
    menuItem = new JMenuItem(new PasteAction(this));
    menu.add(menuItem);

    menu = new JMenu("ウィンドウ(W)");
    menu.setMnemonic(KeyEvent.VK_W);
    menuBar.add(menu);

    menuItem = new JMenuItem(new CashAction(this));
    menu.add(menuItem);
    menuItem = new JMenuItem(new AccountsRecAction(this));
    menu.add(menuItem);
    menuItem = new JMenuItem(new AccountsPayAction(this));
    menu.add(menuItem);
    menuItem = new JMenuItem(new CostAction(this));
    menu.add(menuItem);
    menu.addSeparator();
    menuItem = new JMenuItem(new AccountListAction(this));
    menu.add(menuItem);
    menu.addSeparator();
    menuItem = new JMenuItem(new MonthlySalesAction(this));
    menu.add(menuItem);
    menuItem = new JMenuItem(new MonthlyPurchaseAction(this));
    menu.add(menuItem);
    menu.addSeparator();
    menuItem = new JMenuItem(new BalanceAction(this));
    menu.add(menuItem);
    menuItem = new JMenuItem(new ProfitAction(this));
    menu.add(menuItem);

    menu = new JMenu("ツール(T)");
    menu.setMnemonic(KeyEvent.VK_T);
    menuBar.add(menu);

    menu = new JMenu("ヘルプ(H)");
    menu.setMnemonic(KeyEvent.VK_H);

    menuItem = new JMenuItem(new HelpAction(this));
    menu.add(menuItem);
    menuItem = new JMenuItem(new VersionAction(this));
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
            // TODO 自動生成されたメソッド・スタブ
            logger.info("JMenuBar.focusGained");
          }
        });

    this.addFocusListener(
        new FocusListener() {

          @Override
          public void focusLost(FocusEvent e) {
            // TODO 自動生成されたメソッド・スタブ

          }

          @Override
          public void focusGained(FocusEvent e) {
            // TODO 自動生成されたメソッド・スタブ
            logger.info("main.focusGained");
          }
        });
    this.addWindowFocusListener(
        new WindowFocusListener() {

          @Override
          public void windowLostFocus(WindowEvent e) {
            // TODO 自動生成されたメソッド・スタブ
            logger.info("weLostFocus:" + e);
          }

          @Override
          public void windowGainedFocus(WindowEvent e) {
            // TODO 自動生成されたメソッド・スタブ
            logger.info("weLostGained:" + e);
          }
        });
  }

  private void initPopupMenu() {

    popup = new JPopupMenu();

    JMenuItem menuItem = new JMenuItem(new InsertAction(this));
    popup.add(menuItem);
    menuItem = new JMenuItem(new DeleteAction(this));
    popup.add(menuItem);
    // 区切り線
    popup.addSeparator();
    menuItem = new JMenuItem(new AddDebitAction(this));
    popup.add(menuItem);
    menuItem = new JMenuItem(new AddCreditAction(this));
    popup.add(menuItem);
    // 区切り線
    popup.addSeparator();
    // コピーペースト機能
    menuItem = new JMenuItem(new CopyAction(this));
    popup.add(menuItem);
    menuItem = new JMenuItem(new PasteAction(this));
    popup.add(menuItem);
  }

  public TableColumnModel createTableColumnModel() {
    JTextArea textArea = new JTextArea();
    textArea.setLineWrap(true);
    textArea.addKeyListener(
        new KeyListener() {

          @Override
          public void keyPressed(KeyEvent arg0) {
            logger.info("keyPressed" + arg0.toString());
          }

          @Override
          public void keyReleased(KeyEvent arg0) {
            logger.info("keyReleased" + arg0.toString());
          }

          @Override
          public void keyTyped(KeyEvent arg0) {
            logger.info("keyTyped" + arg0.toString());
          }
        });
    textArea.addInputMethodListener(
        new InputMethodListener() {

          @Override
          public void caretPositionChanged(InputMethodEvent arg0) {
            logger.info("inputMethodTextChanged" + arg0.toString());
          }

          @Override
          public void inputMethodTextChanged(InputMethodEvent arg0) {
            logger.info("inputMethodTextChanged" + arg0.toString());
          }
        });
    textArea.addFocusListener(
        new FocusListener() {

          @Override
          public void focusGained(FocusEvent arg0) {
            logger.info("focusGained" + arg0.toString());
          }

          @Override
          public void focusLost(FocusEvent arg0) {
            logger.info("focusLost" + arg0.toString());
            // JTextArea textArea = (JTextArea)arg0.getSource();
            // if (textArea.getLineCount() > 0) {
            // table.setRowHeight(table.getSelectedRow(),
            // (int)textArea.getPreferredSize().getHeight());
            // }

          }
        });
    JTextField textField = new JTextField();
    // カラム情報
    DefaultTableColumnModel columnModel = new DefaultTableColumnModel();

    JournalTableCellRenderer renderer = new JournalTableCellRenderer();
    TableColumn tableColumn = new TableColumn(0);

    tableColumn.setHeaderValue("取引日");
    tableColumn.setCellRenderer(renderer);
    tableColumn.setIdentifier(0);
    columnModel.addColumn(tableColumn);

    tableColumn = new TableColumn(1);
    tableColumn.setHeaderValue("摘要");
    TextAreaCellEditor textAreaCellEditor = new TextAreaCellEditor(textArea);
    tableColumn.setCellEditor(textAreaCellEditor);
    tableColumn.setCellRenderer(renderer);
    tableColumn.setIdentifier(1);
    columnModel.addColumn(tableColumn);

    tableColumn = new TableColumn(2);
    tableColumn.setHeaderValue("借方(入)");
    tableColumn.setCellEditor(debitCellEditor);
    tableColumn.setCellRenderer(renderer);
    tableColumn.setIdentifier(2);
    columnModel.addColumn(tableColumn);
    tableColumn = new TableColumn(3);
    tableColumn.setHeaderValue("貸方(出)");
    tableColumn.setCellEditor(creditCellEditor);
    tableColumn.setCellRenderer(renderer);
    tableColumn.setIdentifier(3);
    columnModel.addColumn(tableColumn);
    tableColumn = new TableColumn(4);
    tableColumn.setHeaderValue("金額");
    columnModel.addColumn(tableColumn);
    tableColumn.setCellEditor(new DefaultCellEditor(textField));
    tableColumn.setIdentifier(4);
    tableColumn.setCellRenderer(renderer);
    textField.setDocument(new LongDocument());
    textField.setHorizontalAlignment(JTextField.RIGHT);

    // 画面の初期位置取得
    if (prop.containsKey(PROP_KEY_JOURNAL_TABLE)) {
      String initPoint = prop.getProperty(PROP_KEY_JOURNAL_TABLE);
      String[] points = initPoint.split(PROP_SPLIT_CHAR);
      if (points.length == 5) {
        columnModel.getColumn(0).setPreferredWidth(Integer.parseInt(points[0]));
        columnModel.getColumn(1).setPreferredWidth(Integer.parseInt(points[1]));
        columnModel.getColumn(2).setPreferredWidth(Integer.parseInt(points[2]));
        columnModel.getColumn(3).setPreferredWidth(Integer.parseInt(points[3]));
        columnModel.getColumn(4).setPreferredWidth(Integer.parseInt(points[4]));
      }
    }

    return columnModel;
  }

  public File getFile() {
    return selectedFile;
  }

  /** 表示を初期化する。 */
  private void initView() {
    // テーブルモデル
    model = new ListTableModel(journalList, 5);
    // テーブル
    table = new JTable(model, createTableColumnModel());
    table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

    table.addFocusListener(
        new FocusListener() {

          @Override
          public void focusLost(FocusEvent e) {
            // TODO 自動生成されたメソッド・スタブ
            logger.info("JTable.focusLost");
          }

          @Override
          public void focusGained(FocusEvent e) {
            // TODO 自動生成されたメソッド・スタブ
            logger.info("JTable.focusGained");
          }
        });
    table.setSurrendersFocusOnKeystroke(true);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    JScrollPane scrollPane = new JScrollPane(table);
    // ポップアップメニューの設定
    table.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {;
              popup.show(e.getComponent(), e.getX(), e.getY());
            }
          }
        });
    scrollPane.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {;
              popup.show(e.getComponent(), e.getX(), e.getY());
            }
          }
        });
    getContentPane().add(scrollPane);
  }

  public JTable getTable() {
    return table;
  }

  public ListTableModel getModel() {
    return model;
  }

  public int[] getSelectedRows() {
    return table.getSelectedRows();
  }

  public void clearSelection() {
    table.getSelectionModel().clearSelection();
    if (table.getCellEditor() != null) {
      table.getCellEditor().cancelCellEditing();
    } else {
      logger.info("cellエディターがぬる");
    }
  }

  public void select(Consumer<File> function) {
    JFileChooser chooser = new JFileChooser();
    chooser.setSelectedFile(selectedFile);
    switch (chooser.showOpenDialog(this)) {
      case JFileChooser.CANCEL_OPTION:
        break;
      case JFileChooser.APPROVE_OPTION:
        File file = chooser.getSelectedFile();
        if (file.exists() && file.isFile()) {

          function.accept(file);

        } else {
          DialogUtil.showMessageDialog(this, "ファイルがありません。");
        }
        break;
      case JFileChooser.ERROR_OPTION:
        DialogUtil.showMessageDialog(this, "Errorが発生しました。");
        break;
      default:
    }
  }

  public void create(Consumer<File> function) {
    JFileChooser chooser = new JFileChooser();
    chooser.setSelectedFile(selectedFile);
    switch (chooser.showOpenDialog(this)) {
      case JFileChooser.CANCEL_OPTION:
        break;
      case JFileChooser.APPROVE_OPTION:
        File file = chooser.getSelectedFile();
        if (file.exists() && file.isFile()) {
          if (JOptionPane.OK_OPTION == DialogUtil.showConfirmDialog(this, "上書きしてよいですか？")) {
            function.accept(file);
          }
        } else {
          function.accept(file);
        }
        break;
      case JFileChooser.ERROR_OPTION:
        DialogUtil.showMessageDialog(this, "Errorが発生しました。");
        break;
      default:
    }
  }

  public void exportAccountCsv(File file) {
    try (FileOutputStream fis = new FileOutputStream(file)) {
      for (Account account : accountList) {
        fis.write(account.toCsv().getBytes(StandardCharsets.UTF_8));
        fis.write('\n');
      }
      DialogUtil.showMessageDialog(this, "エクスポート処理が完了しました。");
    } catch (FileNotFoundException e) {
      logger.log(Level.SEVERE, "ファイルが見つかりません", e);
    } catch (IOException e) {
      logger.log(Level.SEVERE, "CSV出力時にエラーが発生しました。", e); // TODO 画面にメッセージ出さないと。。
    } catch (Exception e) {
      logger.log(Level.SEVERE, "CSVエクスポートに失敗しました", e);
    }
  }

  /**
   * CSVから勘定一覧をインポートします
   *
   * @param file
   */
  public void importAccountCsv(File file) {
    try (CSVReader csvReader = new CSVReader(file, "utf-8")) {
      String[] strings = null;
      while ((strings = csvReader.getNextCsvLine(6, true)) != null) {
        String[] params = strings;
        accountList.forEach(
            account -> {
              if (account.getName().equals(params[2])) {
                account.setCd(params[1]);
                account.setCategory(params[3]);
                account.setSortKey(Integer.parseInt(params[4]));
                account.setRate(Integer.parseInt(params[5]));
              }
            });
      }
      accountListBook.repaint();
      DialogUtil.showMessageDialog(this, "インポート処理が完了しました。");
    } catch (FileNotFoundException e) {
      logger.log(Level.SEVERE, "ファイルが見つかりません", e);
    } catch (IOException e) {
      logger.log(Level.SEVERE, "CSV読み込み時にエラーが発生しました。", e);
    } catch (Exception e) {
      logger.log(Level.SEVERE, "CSVインポートに失敗しました", e);
    }
  }

  /** CSVからインポートします. */
  public void importCsv(File file) {
    journalList.clear();
    accountList.clear();
    // ファイルが
    try (CSVReader csvReader = new CSVReader(file, "utf-8")) {
      Map<Long, Account> accountMap = new HashMap<>();
      Map<String, Account> accountNameMap = new HashMap<>();
      long journalId = 1;
      long transactionId = 1;
      long accountId = 1;
      String[] strings = null;
      csvReader.getNextCsvLine(6, true);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      while ((strings = csvReader.getNextCsvLine(6, true)) != null) {
        Journal journal = null;

        if (strings[0].isEmpty()) {
          journal = journalList.get(journalList.size() - 1);
        } else {
          String[] journalParams = {
            String.valueOf(journalId++),
            String.valueOf(sdf.parse(strings[0]).getTime() / 100000),
            strings[3],
            strings[5]
          };
          journal = Journal.create(journalParams);
        }
        // 2
        Account account = null;
        if (!accountNameMap.containsKey(strings[2])) {
          String[] accountParams = {String.valueOf(accountId), null, strings[2], null, null};
          account = Account.create(accountParams);
          accountNameMap.put(strings[2], account);
          accountMap.put(accountId, account);
          accountId++;
          accountList.add(account);
        } else {
          account = accountNameMap.get(strings[2]);
        }

        String[] transactionParams1 = {
          String.valueOf(transactionId++),
          String.valueOf(journal.getId()),
          "1",
          String.valueOf(account.getId()),
          strings[1]
        };
        journal.add(Transaction.create(transactionParams1, accountMap));

        if (strings[4] != null && !strings[4].isEmpty()) {
          // 4
          account = null;
          if (!accountNameMap.containsKey(strings[4])) {
            String[] accountParams = {String.valueOf(accountId), null, strings[4], null, null};
            account = Account.create(accountParams);
            accountNameMap.put(strings[4], account);
            accountMap.put(accountId, account);
            accountId++;
            accountList.add(account);
          } else {
            account = accountNameMap.get(strings[4]);
          }

          String[] transactionParams2 = {
            String.valueOf(transactionId++),
            String.valueOf(journal.getId()),
            "0",
            String.valueOf(account.getId()),
            strings[5]
          };
          journal.add(Transaction.create(transactionParams2, accountMap));
        }

        if (!strings[0].isEmpty()) {
          journalList.add(journal);
        }
      }
      model.fireTableDataChanged();
      DialogUtil.showMessageDialog(this, "インポート処理が完了しました。");
    } catch (FileNotFoundException e) {
      logger.log(Level.SEVERE, "ファイルが見つかりません", e);
    } catch (IOException e) {
      logger.log(Level.SEVERE, "CSV読み込み時にエラーが発生しました。", e);
    } catch (Exception e) {
      logger.log(Level.SEVERE, "CSVインポートに失敗しました", e);
    }
  }

  public void open() {
    JFileChooser chooser = new JFileChooser();
    chooser.setSelectedFile(selectedFile);
    switch (chooser.showOpenDialog(this)) {
      case JFileChooser.CANCEL_OPTION:
        // DialogUtil.showMessageDialog(this, "キャンセルしました。");
        break;
      case JFileChooser.APPROVE_OPTION:
        File file = chooser.getSelectedFile();
        if (file.exists()) {
          selectedFile = file;
          openFile();
        } else {
          DialogUtil.showMessageDialog(this, "ファイルがありません。");
        }
        break;
      case JFileChooser.ERROR_OPTION:
        DialogUtil.showMessageDialog(this, "Errorが発生しました。");
        break;
      default:
    }
  }

  /** ファイルの形式csv */
  public void openFile() {
    journalList.clear();
    accountList.clear();
    if (selectedFile.exists() && selectedFile.isFile()) {
      try (ZipCSVReader csvReader = new ZipCSVReader(selectedFile, "utf8")) {
        Map<Long, Journal> journalMap = new HashMap<>();
        Map<Long, Account> accountMap = new HashMap<>();
        String[] strings = null;
        if (csvReader.hasNextEntry() && "journal.csv".equals(csvReader.getName())) {
          while ((strings = csvReader.getNextCsvLine(4)) != null) {
            Journal journal = Journal.create(strings);
            journalList.add(journal);
            journalMap.put(journal.getId(), journal);
          }
          csvReader.closeEntry();
        }
        if (csvReader.hasNextEntry() && "account.csv".equals(csvReader.getName())) {
          while ((strings = csvReader.getNextCsvLine(5)) != null) {
            Account account = Account.create(strings);
            accountList.add(account);
            accountMap.put(account.getId(), account);
          }
          csvReader.closeEntry();
        }
        if (csvReader.hasNextEntry() && "transaction.csv".equals(csvReader.getName())) {
          while ((strings = csvReader.getNextCsvLine(5)) != null) {
            Transaction transaction = Transaction.create(strings, accountMap);
            journalMap.get(transaction.getJournalId()).add(transaction);
          }
          csvReader.closeEntry();
        }
        model.fireTableDataChanged();
        setTitle(TITLE_NAME + " - " + selectedFile.getName());
      } catch (FileNotFoundException e) {
        // TODO 自動生成された catch ブロック
        e.printStackTrace();
      } catch (IOException e) {
        // TODO 自動生成された catch ブロック
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /** 作成します. */
  public void create() {

    JFileChooser chooser = new JFileChooser(selectedFile);
    switch (chooser.showOpenDialog(this)) {
      case JFileChooser.CANCEL_OPTION:
        // DialogUtil.showMessageDialog(this, "キャンセルしました。");
        break;
      case JFileChooser.APPROVE_OPTION:
        File file = chooser.getSelectedFile();
        if (file.exists()) {
          switch (DialogUtil.showConfirmDialog(
              this, "ファイルが存在します。\n上書きしますか？", "上書き確認", JOptionPane.OK_CANCEL_OPTION)) {
            case JOptionPane.OK_OPTION:
              selectedFile = file;
              break;
            case JOptionPane.CANCEL_OPTION:
              // 何もしない
              break;
            default:
          }

        } else {
          try {
            logger.info(file.getName());
            file.createNewFile();
          } catch (IOException e) {
            e.printStackTrace();
          }
          selectedFile = file;
        }
        break;
      case JFileChooser.ERROR_OPTION:
        DialogUtil.showMessageDialog(this, "Errorが発生しました。");
        break;
      default:
    }
  }

  /** 保存処理 */
  public void save() {
    if (selectedFile == null || !selectedFile.exists()) {
      // ファイル
      logger.info("ファイル選択無し");
    } else {
      if (selectedFile != null && selectedFile.exists()) {
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(selectedFile)); ) {
          if (!selectedFile.exists()) {
            selectedFile.createNewFile();
          }

          ZipEntry ent = new ZipEntry("journal.csv");
          zip.putNextEntry(ent);
          for (Journal journal : journalList) {
            zip.write(journal.toCsv().getBytes("utf8"));
            zip.write('\n');
          }
          zip.closeEntry();
          ent = new ZipEntry("account.csv");
          zip.putNextEntry(ent);
          for (Account account : accountList) {
            zip.write(account.toCsv().getBytes("utf8"));
            zip.write('\n');
          }
          zip.closeEntry();
          ent = new ZipEntry("transaction.csv");
          zip.putNextEntry(ent);
          for (Journal journal : journalList) {
            for (Transaction transaction : journal.getTransactionList()) {
              zip.write(transaction.toCsv().getBytes("utf8"));
              zip.write('\n');
            }
          }
          zip.closeEntry();
          zip.flush();

        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * 勘定リストを取得します.
   *
   * @return
   */
  public List<Account> getAccountList() {
    return accountList;
  }

  /** 勘定一覧画面を表示します. */
  public void showAccountList() {
    accountListBook.setVisible(true);
  }

  public void showProfitBook() {
    profitBook.setVisible(true);
  }

  /** 勘定一覧画面 */
  AccountListBook accountListBook;

  /** 損益計算書画面 */
  ProfitBook profitBook = new ProfitBook(journalList);

  /** 帳面マップ */
  Map<String, AccountBook> bookMap = new HashMap<>();

  /** 月別帳面マップ */
  Map<String, MonthlyBook> monthlyBookMap = new HashMap<>();

  /** 貸借対照表 */
  BalanceBook balanceBook = new BalanceBook(journalList);

  /** 勘定一覧画面を初期化します. */
  public void initAccountView() {

    accountListBook = new AccountListBook(this);
    accountListBook.initComponents();
    accountListBook.pack();
  }

  public void showBalanceBook() {
    balanceBook.setVisible(true);
  }

  /**
   * 勘定科目に紐づく仕訳を表示する。
   *
   * @param accountName
   * @param title
   */
  public void showBook(String key) {
    AccountBook accountBook = null;
    if (bookMap.containsKey(key)) {
      // 作成済み
      accountBook = bookMap.get(key);
    } else {
      // 新規作成
      accountBook = new AccountBook(journalList);
      accountBook.pack();
      setWindowSize(accountBook, key);
      bookMap.put(key, accountBook);
    }
    accountBook.setAccountName(
        prop.getProperty(key + ".ACCOUNT"), prop.getProperty(key + ".TITLE"));
    accountBook.setVisible(true);
  }

  /**
   * 勘定科目に紐づく仕訳を月ごとに集計して表示する。
   *
   * @param accountName
   * @param title
   */
  public void showMonthlyBook(String key) {
    MonthlyBook monthlyBook = null;
    if (monthlyBookMap.containsKey(key)) {
      // 作成済み
      monthlyBook = monthlyBookMap.get(key);
    } else {
      // 新規作成
      monthlyBook = new MonthlyBook(journalList);
      monthlyBook.pack();
      setWindowSize(monthlyBook, key);
      monthlyBookMap.put(key, monthlyBook);
    }
    monthlyBook.setAccountName(
        prop.getProperty(key + ".ACCOUNT"), prop.getProperty(key + ".TITLE"));
    monthlyBook.setVisible(true);
  }

  public void initBook() {
    AccountBook accountBook = new AccountBook(journalList);
    accountBook.pack();

    setWindowSize(accountBook, PROP_KEY_CASHBOOK_WINDOW);
    bookMap.put(PROP_KEY_CASHBOOK_WINDOW, accountBook);
    AccountBook costbook = new AccountBook(journalList);
    costbook.pack();
    setWindowSize(costbook, PROP_KEY_COST_WINDOW);
    bookMap.put(PROP_KEY_COST_WINDOW, costbook);
    AccountBook accountsPayBook = new AccountBook(journalList);
    accountsPayBook.pack();
    setWindowSize(accountsPayBook, PROP_KEY_ACCOUNT_PAY_WINDOW);
    bookMap.put(PROP_KEY_ACCOUNT_PAY_WINDOW, accountsPayBook);
    AccountBook accountsRecBook = new AccountBook(journalList);
    accountsRecBook.pack();
    setWindowSize(accountsRecBook, PROP_KEY_ACCOUNT_REC_WINDOW);
    bookMap.put(PROP_KEY_ACCOUNT_REC_WINDOW, accountsRecBook);

    MonthlyBook salesMonthlyBook = new MonthlyBook(journalList);
    salesMonthlyBook.pack();
    setWindowSize(salesMonthlyBook, PROP_KEY_MONTHLY_SALES_WINDOW);
    monthlyBookMap.put(PROP_KEY_MONTHLY_SALES_WINDOW, salesMonthlyBook);

    MonthlyBook purchaseMonthlyBook = new MonthlyBook(journalList);
    purchaseMonthlyBook.pack();
    setWindowSize(purchaseMonthlyBook, PROP_KEY_MONTHLY_PURCHASE_WINDOW);
    monthlyBookMap.put(PROP_KEY_MONTHLY_PURCHASE_WINDOW, purchaseMonthlyBook);

    balanceBook.pack();
    setWindowSize(balanceBook, PROP_KEY_BALANCE_WINDOW);

    profitBook.pack();
    setWindowSize(profitBook, PROP_KEY_PROFIT_WINDOW);
  }

  @Override
  public JournalBook getMainComponent() {
    return this;
  }

  @Override
  public UndoManager getUndoManager() {
    // TODO 自動生成されたメソッド・スタブ
    return null;
  }

  @Override
  public Properties getActionResource() {
    return actionResource;
  }

  public void confirm() {

    CellEditor editor = table.getCellEditor();
    if (editor != null) {
      editor.stopCellEditing();
    }
    debitCellEditor.stopCellEditing();
    creditCellEditor.stopCellEditing();
  }

  /*
   * (非 Javadoc)
   *
   * @see com.uchicom.ui.util.UIStore#getResourceBundle()
   */
  @Override
  public ResourceBundle getResourceBundle() {
    return resourceBundle;
  }

  public List<Journal> getJournalList() {
    return journalList;
  }
}
