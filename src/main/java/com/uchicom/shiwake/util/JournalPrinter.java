// (c) 2016 uchicom
package com.uchicom.shiwake.util;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.List;

import com.uchicom.shiwake.bean.Journal;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class JournalPrinter implements Printable {
	private List<Journal> list;
	public JournalPrinter(List<Journal> list) {
		this.list = list;
	}
	/* (非 Javadoc)
	 * @see java.awt.print.Printable#print(java.awt.Graphics, java.awt.print.PageFormat, int)
	 */
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		System.out.println(pageIndex);
		if (pageIndex > 1) {
			return NO_SUCH_PAGE;
		}
		try {
		Graphics2D g2d = (Graphics2D) graphics;
		//g2d.translate(pf.getImageableX(), pf.getImageableY());
		System.out.println("ix:iy " + pageFormat.getImageableX() + ":"
				+ pageFormat.getImageableY());
		System.out.println("iwidth:iheight " + pageFormat.getImageableWidth() + ":"
				+ pageFormat.getImageableHeight());
		g2d.drawString("仕訳帳", 100, 100);
		g2d.setStroke(new BasicStroke(0.1f));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return PAGE_EXISTS;
	}

}
