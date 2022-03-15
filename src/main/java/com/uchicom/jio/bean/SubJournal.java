// (C) 2012 uchicom
package com.uchicom.jio.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 勘定に関する仕訳
 *
 * @author Uchiyama Shigeki
 */
public class SubJournal implements Serializable {

  /** */
  private static final long serialVersionUID = 1L;

  /** 仕訳ID */
  //    private Long id;
  /** 取引日 */
  private Date dealDay;
  /** 摘要 */
  private String summary;
  /** 金額 */
  private Long amount;
  /** ステータス */
  private Integer status;
  /** 勘定科目 */
  private Account account;
  /** 残高 */
  private Integer balance;

  public SubJournal(Date dealDay, String summary, Long amount, Account account, Integer balance) {

    this.dealDay = dealDay;
    this.summary = summary;
    this.amount = amount;
    this.account = account;
    this.balance = balance;
  }

  public SubJournal() {
    dealDay = new Date(System.currentTimeMillis());
  }

  /**
   * dealDayを取得します。
   *
   * @return dealDay
   */
  public Date getDealDay() {
    return dealDay;
  }

  /**
   * dealDayを設定します。
   *
   * @param dealDay
   */
  public void setDealDay(Date dealDay) {
    this.dealDay = dealDay;
  }

  /**
   * summaryを取得します。
   *
   * @return summary
   */
  public String getSummary() {
    return summary;
  }

  /**
   * summaryを設定します。
   *
   * @param summary
   */
  public void setSummary(String summary) {
    this.summary = summary;
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
   * statusを取得します。
   *
   * @return status
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * statusを設定します。
   *
   * @param status
   */
  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getBalance() {
    return balance;
  }

  public void setBalance(Integer balance) {
    this.balance = balance;
  }
}
