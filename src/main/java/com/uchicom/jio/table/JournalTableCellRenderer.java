// (C) 2013 uchicom
package com.uchicom.jio.table;

import com.uchicom.jio.bean.Transaction;
import java.awt.Color;
import java.awt.Component;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 * 仕分けのレンダラー
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class JournalTableCellRenderer extends DefaultTableCellRenderer {

  /** */
  private static final long serialVersionUID = 1L;

  private JLabel label = new JLabel();
  private JTextField dateField = new JTextField();
  private JTextField amoutField = new JTextField();

  private JTextArea textArea = new JTextArea();
  private JTextPane textPane = new JTextPane();

  //    private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

  private TransactionTableModel model = new TransactionTableModel();
  private JTable intable;

  /** 金額 */
  private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
  //    private JScrollPane scrollPane = new JScrollPane(intable);

  public JournalTableCellRenderer() {
    label.setOpaque(true);
    //        textArea.setLineWrap(true);
    //        amoutField.setDocument(new LongDocument());
    amoutField.setHorizontalAlignment(JTextField.RIGHT);

    //        Font font = new Font(textArea.getFont().getName(), Font.PLAIN, 11);
    //        textArea.setFont(font);
    //        SimpleAttributeSet attr = new SimpleAttributeSet();
    //        StyleConstants.setLineSpacing(attr, (float) -0.2);
    //        textPane.setParagraphAttributes(attr, false);

    DefaultTableCellRenderer renderer =
        new DefaultTableCellRenderer() {
          /** */
          private static final long serialVersionUID = 1L;

          @Override
          public Component getTableCellRendererComponent(
              JTable table,
              Object value,
              boolean isSelected,
              boolean hasFocus,
              int row,
              int column) {
            JComponent returnComponent = null;
            int modelIndex = table.getColumnModel().getColumn(column).getModelIndex();
            switch (modelIndex) {
              case 0:
                label.setText((String) value);
                returnComponent = label;
                break;
              case 1:
                // 金額
                if ("".equals(value)) {
                  amoutField.setText("");
                } else {
                  amoutField.setText(currencyFormat.format(Integer.parseInt((String) value)));
                }
                returnComponent = amoutField;
                break;
            }

            // 配色
            if (isSelected) {
              returnComponent.setForeground(table.getSelectionForeground());
              returnComponent.setBackground(table.getSelectionBackground());
            } else {
              returnComponent.setBackground(table.getBackground());
              returnComponent.setForeground(table.getForeground());
            }
            if (hasFocus) {
              returnComponent.setBorder(BorderFactory.createLineBorder(Color.gray));
            } else {
              returnComponent.setBorder(null);
            }
            return returnComponent;
          }
        };
    DefaultTableColumnModel columnModel = new DefaultTableColumnModel();
    TableColumn tableColumn = new TableColumn(0);
    tableColumn.setCellRenderer(renderer);
    tableColumn.setIdentifier(0);
    columnModel.addColumn(tableColumn);
    tableColumn = new TableColumn(1);
    tableColumn.setCellRenderer(renderer);
    tableColumn.setIdentifier(1);
    columnModel.addColumn(tableColumn);
    intable = new JTable(model, columnModel);
    intable.setRequestFocusEnabled(false);
    label.setFont(textArea.getFont());
  }

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    JComponent returnComponent = null;
    int modelIndex = table.getColumnModel().getColumn(column).getModelIndex();
    switch (modelIndex) {
      case 0:
        // 日付
        dateField.setText((String) value);
        returnComponent = dateField;
        break;
      case 4:
        // 金額
        if ("".equals(value)) {
          amoutField.setText("");
        } else {
          amoutField.setText(currencyFormat.format(Integer.parseInt((String) value)));
        }
        returnComponent = amoutField;
        break;
      case 1:
        // 摘要
        if (value == null) {
          textArea.setText("");
          textPane.setText("");
        } else {
          if (value != null) {
            textArea.setText(value.toString());
            textPane.setText(value.toString());
          }
        }
        returnComponent = textArea;
        break;
      case 2:
        // 借方
      case 3:
        // 貸方
        if (value != null && value instanceof List<?>) {
          @SuppressWarnings("unchecked")
          List<Transaction> transactionList = (List<Transaction>) value;
          if (transactionList.size() > 1) {
            model.setRowList(transactionList);
            if (isSelected) {
              intable.selectAll();
            } else {
              intable.clearSelection();
            }
            returnComponent = intable;
          } else if (transactionList.size() == 1) {
            Transaction bean = transactionList.get(0);
            if (bean != null && bean.getAccount() != null && bean.getAccount().getName() != null) {
              label.setText(bean.getAccount().getName());
            } else {
              label.setText("");
            }
            returnComponent = label;

          } else {
            label.setText("");
            returnComponent = label;
          }
        } else {
          label.setText("");
          returnComponent = label;
        }
        break;
      default:
        label.setText("");
        returnComponent = label;
    }

    // 行高さ設定
    int rowHeight = returnComponent.getPreferredSize().height;
    if (rowHeight > table.getRowHeight(row)) {
      table.setRowHeight(row, rowHeight);
    }

    // 配色
    if (isSelected) {
      returnComponent.setForeground(table.getSelectionForeground());
      returnComponent.setBackground(table.getSelectionBackground());
      if (returnComponent == intable) {

        intable.selectAll();
      }
    } else {
      returnComponent.setBackground(table.getBackground());
      returnComponent.setForeground(table.getForeground());
    }
    if (hasFocus) {
      returnComponent.setBorder(BorderFactory.createLineBorder(Color.gray));
    } else {
      returnComponent.setBorder(null);
    }

    return returnComponent;
  }
}
