// (c) 2016 uchicom
package com.uchicom.jio.util;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.List;
import java.util.logging.Logger;

import com.uchicom.jio.bean.Journal;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class JournalPrinter implements Printable {

	private static final Logger logger = Logger.getLogger(JournalPrinter.class.getCanonicalName());
//	private List<Journal> list;
	public JournalPrinter(List<Journal> list) {
//		this.list = list;
	}
	/* (非 Javadoc)
	 * @see java.awt.print.Printable#print(java.awt.Graphics, java.awt.print.PageFormat, int)
	 */
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		logger.info(String.valueOf(pageIndex));
		if (pageIndex > 1) {
			return NO_SUCH_PAGE;
		}
		try {
		Graphics2D g2d = (Graphics2D) graphics;
		//g2d.translate(pf.getImageableX(), pf.getImageableY());
		logger.info("ix:iy " + pageFormat.getImageableX() + ":"
				+ pageFormat.getImageableY());
		logger.info("iwidth:iheight " + pageFormat.getImageableWidth() + ":"
				+ pageFormat.getImageableHeight());
		g2d.drawString("仕訳帳", 100, 100);
		g2d.setStroke(new BasicStroke(0.1f));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return PAGE_EXISTS;
	}

}
