// (c) 2013 uchicom
package com.uchicom.jio.table;

import java.util.logging.Logger;

import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class LongDocument extends PlainDocument {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	

	private static final Logger logger = Logger.getLogger(LongDocument.class.getCanonicalName());

	@Override
	public void insertString(int offset, String str, AttributeSet attributes) {
		logger.info("insertString:" + offset + ":" + str + ":" + attributes);
		StringBuffer strBuff = new StringBuffer(str.length());
		try {
			if (str.matches("^.*[^0-9０-９].*$")) {
				for (char cha : str.toCharArray()) {
					if (cha >= '0' && cha <= '9') {
						strBuff.append(cha);
					} else if (cha >= '０' && cha <= '９') {
						strBuff.append((char)('0' + (cha - '０')));
					}
				}
				super.insertString(offset, strBuff.toString(), attributes);
			} else {
				super.insertString(offset, str, attributes);
			}
		} catch (Exception e) {

		}
	}
}
