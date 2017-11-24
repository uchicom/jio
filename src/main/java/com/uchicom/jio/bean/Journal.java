// (c) 2012 uchicom
package com.uchicom.jio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.uchicom.jio.enums.TransactionType;

/**
 * 仕訳
 * @author Uchiyama Shigeki
 *
 */
public class Journal implements Serializable, Cloneable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /** これでマックスIDを制御 */
    private static long maxId;

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
	 * 借方
	 */
	private List<Transaction> debitList = new ArrayList<Transaction>();
	/**
	 * 貸方
	 */
	private List<Transaction> creditList = new ArrayList<Transaction>();
	public Journal(Long id, Date dealDay, String summary, Long amount, List<Transaction> debitList, List<Transaction> creditList) {
	    this.id = id;
	    this.dealDay = dealDay;
	    this.summary = summary;
	    this.amount = amount;
	    this.debitList = debitList;
	    this.creditList = creditList;

	}
	public Journal(TransactionType... types) {
		id = maxId++;
        dealDay = new Date();
        for (TransactionType type : types) {
        	add(new Transaction(type));
        }
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
	 * debitListを取得します。
	 * @return debitList
	 */
	public List<Transaction> getDebitList() {
		return debitList;
	}

	/**
	 * debitListを設定します。
	 * @param debitList
	 */
	public void setDebitList(List<Transaction> debitList) {
		this.debitList = debitList;
	}

	/**
	 * creditListを取得します。
	 * @return creditList
	 */
	public List<Transaction> getCreditList() {
		return creditList;
	}

	/**
	 * creditListを設定します。
	 * @param creditList
	 */
	public void setCreditList(List<Transaction> creditList) {
		this.creditList = creditList;
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

	public static Journal create(String[] strings) {
		Journal journal = new Journal();
		journal.setId(Long.parseLong(strings[0]));
		if (strings[1] != null && !"".equals(strings[1])) {
			journal.setDealDay(new Date(Long.parseLong(strings[1]) * 100000));
		}
		if (strings[2] != null && !"".equals(strings[2])) {
			journal.setSummary(strings[2]);
		}
		if (strings[3] != null && !"".equals(strings[3])) {
			journal.setAmount(Long.parseLong(strings[3]));
		}
		//maxid制御 TODOs
		if (maxId < journal.getId()) {
			maxId = journal.getId() + 1;
		}
		return journal;
	}
	public String toCsv() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(id);
		stringBuffer.append(",");
		if (dealDay != null) {
			stringBuffer.append(dealDay.getTime() / 100000);
		}
		stringBuffer.append(",");
		if (summary != null) {
			stringBuffer.append(summary.replaceAll("[\n,]", " "));//TODO改行,パース未対応なので置換する
		}
		stringBuffer.append(",");
		if (amount != null) {
			stringBuffer.append(amount);
		}
		return stringBuffer.toString();
	}

	public List<Transaction> getTransactionList() {
		List<Transaction> transactionList = new ArrayList<>(creditList.size() + debitList.size());
		transactionList.addAll(creditList);
		transactionList.addAll(debitList);
		return transactionList;
	}
	public void add(Transaction transaction) {
		transaction.setJournalId(id);
		switch (transaction.getType()) {
		case Credit:
			creditList.add(transaction);
			break;
		case Debit:
			debitList.add(transaction);
			break;
		default:
			break;
		}
	}
	
	@Override
	public Journal clone() {
		Journal journal = new Journal();
		journal.dealDay = dealDay;
		creditList.forEach((credit)-> {
			journal.add(credit.clone());
		});
		debitList.forEach((credit)-> {
			journal.add(credit.clone());
		});
		journal.status = status;
		journal.summary = summary;
		journal.amount = amount;
		return journal;
	}
}
