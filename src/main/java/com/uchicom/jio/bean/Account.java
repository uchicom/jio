// (c) 2012 uchicom
package com.uchicom.jio.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 勘定科目
 * 
 * @author uchiyama
 *
 */
public class Account implements Serializable, Comparable<Account> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static long maxId;
	/** レコードID */
	private Long id;
	/** 勘定科目コード */
	private String cd;
	/** 勘定科目名 */
	private String name;
	/** 勘定科目グループ */
	private String category;
	/** 貸借対照表並び順 */
	private int sortKey;
	/** 割合 */
	private int rate = 100;

	public Account(Long id, String cd, String name, String category) {
		this.id = id;
		this.cd = cd;
		this.name = name;
		this.category = category;
	}

	public Account(String cd, String name, String category) {
		id = maxId++;
		this.cd = cd;
		this.name = name;
		this.category = category;
	}

	public Account() {
		id = maxId++;
	}

	/**
	 * idを取得します。
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * idを設定します。
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		if (maxId <= id) {
			maxId = id + 1;
		}
		this.id = id;
	}

	/**
	 * cdを取得します。
	 * 
	 * @return cd
	 */
	public String getCd() {
		return cd;
	}

	/**
	 * cdを設定します。
	 * 
	 * @param cd
	 */
	public void setCd(String cd) {
		this.cd = cd;
	}

	/**
	 * nameを取得します。
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * nameを設定します。
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * categoryを取得します。
	 * 
	 * @return category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * categoryを設定します。
	 * 
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	public int getSortKey() {
		return sortKey;
	}

	public void setSortKey(int sortKey) {
		this.sortKey = sortKey;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	/**
	 * インサート処理
	 * 
	 * @param con
	 * @param beanList リストで渡す
	 * @throws SQLException
	 */
	public static void insert(Connection con, List<Account> beanList) throws SQLException {
		PreparedStatement preState = con.prepareStatement("insert into t_account(cd, name, category) values(?, ?, ?)");
		for (Account bean : beanList) {
			bean.insert(preState);
		}
	}

	/**
	 * そのものをインサートする
	 * 
	 * @param preState
	 * @return
	 * @throws SQLException
	 */
	public int insert(PreparedStatement preState) throws SQLException {
		preState.setString(1, cd);
		preState.setString(2, name);
		preState.setString(3, category);
		return preState.executeUpdate();
	}

	/**
	 * アップデート処理
	 * 
	 * @param con
	 * @param beanList リストで渡す
	 * @throws SQLException
	 */
	public static void update(Connection con, List<Account> beanList) throws SQLException {
		PreparedStatement preState = null;
		try {
			con.prepareStatement("update t_account set cd = ?, name = ?, category = ? where id = ? ");
			for (Account bean : beanList) {
				bean.update(preState);
			}
		} finally {
			try {
				if (preState != null) {
					preState.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public int update(PreparedStatement preState) throws SQLException {
		preState.setString(1, cd);
		preState.setString(2, name);
		preState.setString(3, category);
		preState.setLong(4, id);
		return preState.executeUpdate();
	}

	/**
	 * アップデート処理
	 * 
	 * @param con
	 * @param beanList リストで渡す
	 * @throws SQLException
	 */
	public static void delete(Connection con, List<Account> beanList) throws SQLException {
		PreparedStatement preState = null;
		try {
			preState = con.prepareStatement("delete from t_account where id = ? ");
			for (Account bean : beanList) {
				bean.delete(preState);
			}
		} finally {
			preState.close();
		}
	}

	public int delete(PreparedStatement preState) throws SQLException {
		preState.setLong(1, id);
		return preState.executeUpdate();
	}

	public static List<Account> selectAll(Connection con) throws SQLException {
		List<Account> list = new ArrayList<Account>();
		PreparedStatement preState = null;
		ResultSet result = null;
		try {
			preState = con.prepareStatement("select id, cd, name, category from t_account");
			result = preState.executeQuery();
			while (result.next()) {
				list.add(new Account(result.getLong(1), result.getString(2), result.getString(3), result.getString(4)));
			}
		} finally {
			if (result != null) {
				result.close();
			}
			if (preState != null) {
				preState.close();
			}
		}
		return list;
	}

	public String toString() {
		return name == null ? "" : name;
	}

	@Override
	public int compareTo(Account o) {
		if (o == null) {
			return -1;
		} else {
			return sortKey - o.sortKey;
		}
	}

	public static Account create(String[] strings) {
		Account account = new Account();
		account.setId(Long.parseLong(strings[0]));
		account.setCd(strings[1]);
		account.setName(strings[2]);
		account.setCategory(strings[3]);
		if (strings[4] != null && !"".equals(strings[4])) {
			account.setSortKey(Integer.parseInt(strings[4]));
		}
		return account;
	}

	public String toCsv() {
		StringBuffer strBuff = new StringBuffer(128);
		strBuff.append(id);
		strBuff.append(",");
		if (cd != null) {
			strBuff.append(cd);
		}
		strBuff.append(",");
		if (name != null) {
			strBuff.append(name);
		}
		strBuff.append(",");
		if (category != null) {
			strBuff.append(category);
		}
		strBuff.append(",");
		strBuff.append(sortKey);
		strBuff.append(",");
		strBuff.append(rate);

		return strBuff.toString();
	}

}
