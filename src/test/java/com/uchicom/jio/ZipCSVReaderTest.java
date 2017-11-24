// (c) 2017 uchicom
package com.uchicom.jio;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.uchicom.jio.util.ZipCSVReader;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class ZipCSVReaderTest {

	/**
	 * {@link com.uchicom.jio.util.ZipCSVReader#getNextCsvLine(int)} のためのテスト・メソッド。
	 */
	@Test
	public void testGetNextCsvLine() {

		try {
			ZipCSVReader reader = new ZipCSVReader(new File("C:\\Users\\shigeki\\Dropbox\\uchicom\\software\\data\\jio\\uchicom _cpoy.zip"), "utf-8");
			assertTrue(reader.hasNextEntry());
			String[] parsed = reader.getNextCsvLine(4);
			assertNotNull(parsed);
			assertEquals(4, parsed.length);
			for (String parse : parsed) {
				System.out.println(":" + parse);
			}
			parsed = reader.getNextCsvLine(4);
			assertNotNull(parsed);
			assertEquals(4, parsed.length);
			for (String parse : parsed) {
				System.out.println(":" + parse);
			}
		} catch (FileNotFoundException e) {
			fail();
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			fail();
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
