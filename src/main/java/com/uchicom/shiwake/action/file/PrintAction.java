// (c) 2012 uchicom
package com.uchicom.shiwake.action.file;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JOptionPane;

import com.uchicom.shiwake.action.ConfirmAction;
import com.uchicom.shiwake.util.JournalPrinter;
import com.uchicom.shiwake.window.ShiwakeFrame;

/**
 * @author Uchiyama Shigeki
 *
 */
public class PrintAction extends ConfirmAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public PrintAction(ShiwakeFrame shiwakeFrame) {
		super(shiwakeFrame);
		putValue(NAME, "印刷");
		// alt+でメニューを選択状態にするキー、メニューが開いている場合は選択するキーとなる。
		putValue(MNEMONIC_KEY, KeyEvent.VK_X);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void action(ActionEvent e) {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(new JournalPrinter(shiwakeFrame.getJournalList()));

		PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
		attributes.add(MediaSizeName.ISO_A4);
		boolean doPrint = job.printDialog();
		if (doPrint) {
		    try {
		        job.print();
		        JOptionPane.showMessageDialog(shiwakeFrame, "印刷が完了しました。");
		    } catch (PrinterException pe) {
		    	JOptionPane.showMessageDialog(shiwakeFrame, pe.getMessage());
		    }
		}

	}

}
