// (c) 2012 uchicom
package com.uchicom.shiwake.action.file;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.Sides;

import com.uchicom.shiwake.action.ConfirmAction;
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
		InputStream psStream = null;
		DocFlavor psInFormat = DocFlavor.INPUT_STREAM.POSTSCRIPT;
		Doc myDoc = new SimpleDoc(psStream, psInFormat, null);
		PrintRequestAttributeSet aset =
				new HashPrintRequestAttributeSet();
		aset.add(new Copies(5));
		aset.add(MediaSizeName.ISO_A4);
		aset.add(Sides.DUPLEX);
		PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat,  aset);
		if (services.length > 0) {
			DocPrintJob job = services[0].createPrintJob();
			try {
				job.print(myDoc,  aset);
			} catch (PrintException pe) {
				pe.printStackTrace();
			}
		}


	}

}
