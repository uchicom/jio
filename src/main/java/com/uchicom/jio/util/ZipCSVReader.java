// (c) 2016 uchicom
package com.uchicom.jio.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.uchicom.csve.util.CSVReader;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class ZipCSVReader implements Closeable {
	private ZipInputStream zis;
	private ZipEntry entry;
	private String encode;
	private CSVReader csvReader;

	/**
	 * @throws FileNotFoundException
	 *
	 */
	public ZipCSVReader(File file, String encode) throws FileNotFoundException {
		this.zis = new ZipInputStream(new FileInputStream(file));
		this.encode = encode;
	}

	public boolean hasNextEntry() throws IOException {
		this.entry = zis.getNextEntry();
		if (entry != null) {
			csvReader = new CSVReader(zis, encode);
			return true;
		} else {
			return false;
		}
	}

	public boolean isDirectory() {
		return entry.isDirectory();
	}

	public String getName() {
		return entry.getName();
	}

	public void closeEntry() throws IOException {
		this.zis.closeEntry();
	}

	public String[] getNextCsvLine(int size) throws IOException {
		return csvReader.getNextCsvLine(size, true);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {

		if (zis != null) {
			try {
				zis.close();
			} catch (Exception e) {
			} finally {
				zis = null;
			}
		}
	}

}
