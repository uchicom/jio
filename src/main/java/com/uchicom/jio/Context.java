// (c) 2019 uchicom
package com.uchicom.jio;

import java.sql.SQLException;
import java.util.Properties;

import com.iciql.Db;
import com.uchicom.util.ResourceUtil;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class Context {

//	private static final Logger logger = Logger.getLogger(Context.class.getCanonicalName());

	private static final Context context = new Context();

	private final Properties properties;

	private Context() {
		properties = ResourceUtil.createProperties(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(System.getProperty("com.uchicom.jio.db", "db.properties")), "UTF-8");
	}

	/**
	 * データベースをオープンして取得します。
	 * 
	 * @return
	 */
	public static Db openDb() {
		return Db.open(context.properties.getProperty("url"), context.properties.getProperty("user"),
				context.properties.getProperty("password"));
	}

	public static Db openDbTransaction() throws SQLException {
		Db db = openDb();
		db.getConnection().setAutoCommit(false);
		db.setAutoSavePoint(false);
		return db;
	}

}
