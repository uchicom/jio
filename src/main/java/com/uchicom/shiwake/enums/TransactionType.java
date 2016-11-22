/**
 * (c) 2016 uchicom
 */
package com.uchicom.shiwake.enums;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public enum TransactionType {

	Credit,Debit;
	public static TransactionType toEnum(String value) {
		switch(value) {
		case "0":
			return Credit;
		case "1":
			return Debit;
		}
		return null;
	}
}
