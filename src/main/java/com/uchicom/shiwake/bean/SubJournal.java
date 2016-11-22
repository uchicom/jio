// (c) 2012 uchicom
package com.uchicom.shiwake.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 勘定に関する仕訳
 * @author Uchiyama Shigeki
 *
 */
public class SubJournal implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /** 仕訳ID */
    private Long id;
	/**
	 * 取引日
	 */
	private Date dealDay;
	/**
	 * 摘要
	 */
	private String summary;
    /**
     * 金額
     */
    private Long amount;
    /**
     * ステータス
     */
    private Integer status;
	/**
	 * 取引
	 */
	private List<Transaction> transactionList = new ArrayList<Transaction>();
	public SubJournal(Date dealDay, String summary, Long amount, List<Transaction> transactionList) {

	    this.dealDay = dealDay;
	    this.summary = summary;
	    this.amount = amount;
	    this.transactionList = transactionList;

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
	 * transactionListを取得します。
	 * @return transactionList
	 */
	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	/**
	 * transactionListを設定します。
	 * @param transactionList
	 */
	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
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
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * statusを設定します。
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}


}
