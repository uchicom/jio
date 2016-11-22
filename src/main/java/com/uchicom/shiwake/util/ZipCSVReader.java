// (c) 2016 uchicom
package com.uchicom.shiwake.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class ZipCSVReader implements Closeable {
	private ZipInputStream zis;
	private byte[] bytes = new byte[1024 * 4 * 1024];
	private ByteArrayOutputStream baos = new ByteArrayOutputStream();
	private ZipEntry entry;
	private String encode;
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
		return entry != null;
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
		System.out.println("getNextCsvLine");
		String[] strings = null;
		int length = 0;
		byte[] temp = null;
		if (baos.size() > 0) {
			byte[] toByte = baos.toByteArray();
			int index = -1;
			for (int i = 0; i < toByte.length; i++) {
				if (toByte[i] == '\n') {
					index = i;
					break;
				}
			}
			if (index >= 0) {
				temp = Arrays.copyOfRange(toByte, index + 1, toByte.length);
				baos.reset();
				baos.write(toByte, 0, index);
			}
		} else {
			while ((length = zis.read(bytes)) > 0) {
				int index = -1;
				for (int i = 0; i < length; i++) {
					if (bytes[i] == '\n') {
						index = i;
						break;
					}
				}
				System.out.println("length:" + length);
				System.out.println("index:" + index);
				if (index >= 0) {
					baos.write(bytes, 0, index);
					System.out.println(":" + new String(bytes, 0, index,encode)+":");
					temp = Arrays.copyOfRange(bytes, index + 1, length);
					break;
				} else {
					System.out.println(":" + new String(bytes, 0, length,encode)+":");
					baos.write(bytes, 0, length);
				}
			}
		}
		if (baos.size() > 0) {
			strings = new String[size];
			try {
				String csv = new String(baos.toByteArray(), encode);
				System.out.println("csv:" + csv);
				String[] splits = csv.split(",");
				for (int i = 0; i < splits.length; i++) {
					strings[i] = splits[i];
				}
				System.out.println("csvlength:" + strings.length);
				baos.reset();
				if (temp != null) {
					baos.write(temp);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		return strings;
	}

	/* (非 Javadoc)
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
