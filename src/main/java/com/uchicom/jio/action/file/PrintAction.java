// (c) 2012 uchicom
package com.uchicom.jio.action.file;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Logger;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.OrientationRequested;

import com.uchicom.jio.action.ConfirmAction;
import com.uchicom.jio.util.JournalPrinter;
import com.uchicom.jio.window.JournalFrame;

/**
 * @author Uchiyama Shigeki
 *
 */
public class PrintAction extends ConfirmAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	

	private static final Logger logger = Logger.getLogger(PrintAction.class.getCanonicalName());

	public PrintAction(JournalFrame journalFrame) {
		super(journalFrame);
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
		job.setPrintable(new JournalPrinter(journalFrame.getJournalList()));


        /* Construct the print request specification.
        * The print data is a Printable object.
        * the request additonally specifies a job name, 2 copies, and
        * landscape orientation of the media.
        */
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(OrientationRequested.LANDSCAPE);
        aset.add(new Copies(2));
        aset.add(new JobName("My job", null));

        /* Create a print job */
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new JournalPrinter(journalFrame.getJournalList()));
        /* locate a print service that can handle the request */
        PrintService[] services =
                PrinterJob.lookupPrintServices();

        if (services.length > 0) {
                logger.info("selected printer " + services[0].getName());
                try {
                        pj.setPrintService(services[0]);
                        pj.pageDialog(aset);
                        if(pj.printDialog(aset)) {
                                pj.print(aset);
                        }
                } catch (PrinterException pe) {
                        System.err.println(pe);
                }
        }

	}

}
