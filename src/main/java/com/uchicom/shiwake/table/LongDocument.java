// (c) 2013 uchicom
package com.uchicom.shiwake.table;

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

    @Override
    public void insertString(int offset, String str, AttributeSet attributes) {
        System.out.println("insertString:" + offset + ":" + str + ":" + attributes);
        try {
            Long.parseLong(str);
            super.insertString(offset, str, attributes);
        } catch (Exception e) {

        }
    }
}
