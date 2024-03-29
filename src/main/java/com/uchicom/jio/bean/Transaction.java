// (C) 2012 uchicom
package com.uchicom.jio.bean;

import com.uchicom.jio.enums.TransactionType;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * 取引
 *
 * @author uchiyama
 */
public class Transaction implements Serializable, Cloneable {

  /** */
  private static final long serialVersionUID = 1L;

  private static long maxId;

  /** */
  private Long id;

  /** 仕訳ID */
  private Long journalId;

  private TransactionType type;

  /** 勘定科目 */
  private Account account;

  private Long amount;

  public Transaction() {
    id = maxId++;
  }

  public Transaction(TransactionType type) {
    this();
    this.type = type;
  }

  public Transaction(Account account, Long amount) {
    this.amount = amount;
    this.account = account;
  }

  /**
   * idを取得します.
   *
   * @return id
   */
  public Long getId() {
    return id;
  }

  /**
   * idを設定します.
   *
   * @param id id
   */
  public void setId(Long id) {
    if (maxId <= id) {
      maxId = id + 1;
    }
    this.id = id;
  }

  /**
   * journalIdを取得します.
   *
   * @return journalId
   */
  public Long getJournalId() {
    return journalId;
  }

  /**
   * journalIdを設定します.
   *
   * @param journalId journalId
   */
  public void setJournalId(Long journalId) {
    this.journalId = journalId;
  }

  /**
   * typeを取得します.
   *
   * @return type
   */
  public TransactionType getType() {
    return type;
  }

  /**
   * typeを設定します.
   *
   * @param type type
   */
  public void setType(TransactionType type) {
    this.type = type;
  }

  /**
   * accountを取得します。
   *
   * @return account
   */
  public Account getAccount() {
    return account;
  }

  /**
   * accountを設定します。
   *
   * @param account
   */
  public void setAccount(Account account) {
    this.account = account;
  }

  /**
   * amountを取得します。
   *
   * @return amount
   */
  public Long getAmount() {
    return amount;
  }

  /**
   * amountを設定します。
   *
   * @param amount
   */
  public void setAmount(Long amount) {
    this.amount = amount;
  }

  /**
   * そのものをインサートする
   *
   * @param preState
   * @return
   * @throws SQLException
   */
  public int insert(PreparedStatement preState) throws SQLException {
    if (account == null || account.getId() == null) {
      preState.setLong(1, -1);
    } else {
      preState.setLong(1, account.getId());
    }
    preState.setLong(2, amount);
    preState.setLong(3, journalId);
    return preState.executeUpdate();
  }

  public int update(PreparedStatement preState) throws SQLException {
    if (account == null || account.getId() == null) {
      preState.setLong(1, -1);
    } else {
      preState.setLong(1, account.getId());
    }
    preState.setLong(2, amount);
    preState.setLong(3, journalId);
    preState.setLong(4, id);
    return preState.executeUpdate();
  }

  public int delete(PreparedStatement preState) throws SQLException {
    preState.setLong(1, id);
    return preState.executeUpdate();
  }

  public static Transaction create(String[] strings, Map<Long, Account> accountMap) {
    Transaction transaction = new Transaction();
    transaction.setId(Long.parseLong(strings[0]));
    if (strings[1] != null && !"".equals(strings[1])) {
      transaction.setJournalId(Long.parseLong(strings[1]));
    }
    if (strings[2] != null && !"".equals(strings[2])) {
      transaction.setType(TransactionType.toEnum(strings[2]));
    }
    if (strings[3] != null && !"".equals(strings[3])) {
      transaction.setAccount(accountMap.get(Long.parseLong(strings[3])));
    }
    if (strings[4] != null && !"".equals(strings[4])) {
      transaction.setAmount(Long.parseLong(strings[4]));
    }
    return transaction;
  }

  public String toCsv() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(id);
    stringBuffer.append(",");
    if (journalId != null) {
      stringBuffer.append(journalId);
    }
    stringBuffer.append(",");
    if (type != null) {
      stringBuffer.append(type.ordinal());
    }
    stringBuffer.append(",");
    if (account != null) {
      stringBuffer.append(account.getId());
    }
    stringBuffer.append(",");
    if (amount != null) {
      stringBuffer.append(amount);
    }
    return stringBuffer.toString();
  }

  public String toString() {
    if (amount == null) {
      return account == null ? "" : account.getName();
    } else {
      return (account == null ? "" : account.getName()) + ":" + amount;
    }
  }

  @Override
  public Transaction clone() {
    Transaction transaction = new Transaction();
    transaction.id = maxId++;
    transaction.journalId = journalId;
    transaction.account = account;
    transaction.amount = amount;
    transaction.type = type;
    return transaction;
  }
}
