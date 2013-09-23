package org.conversion.encodingconversion;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.ByteOrderMarkDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import junit.framework.TestCase;

public class EncodingConvertTest extends TestCase {
	EncodingConverter encodingConverter = new EncodingConverter();
	
	

	public void setUp() {
		encodingConverter = new EncodingConverter();
	}

	public void testEncode() {
		File sourceFile = new File("demo/gbk.txt");;
		File targetFolder = new File("demo/output/target-utf8.txt");

		encodingConverter.setSource(sourceFile);
		encodingConverter.setTarget(targetFolder);
		encodingConverter.setTargetEncoding("utf-8");

		try {
			encodingConverter.encode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testGetFileEncodeGbk() throws Exception {
		File sourceFile = new File("demo/gbk.txt");;
		InputStream in = new FileInputStream(sourceFile);
		assertEquals("GB2312", encodingConverter.getFileEncode(in));
		
	}
	public void testGetFileEncodeUtf8() throws Exception {
		File sourceFile = new File("demo/utf-8.txt");
		InputStream in = new FileInputStream(sourceFile);
		assertEquals("UTF-8", encodingConverter.getFileEncode(in));
	}

	public static void main(String[] args) throws Exception {
		File sourceFile = new File("demo/utf-8.txt");;
		InputStream in = new FileInputStream(sourceFile);
		InputStream is = new BufferedInputStream(in);

		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(false));
		detector.add(new ByteOrderMarkDetector());
		detector.add(JChardetFacade.getInstance()); // Another singleton.
		// ASCIIDetector用于ASCII编码测定
		detector.add(ASCIIDetector.getInstance());
		// UnicodeDetector用于Unicode家族编码的测定
		detector.add(UnicodeDetector.getInstance());
		java.nio.charset.Charset charset = detector.detectCodepage(is, Integer.MAX_VALUE);
		System.out.println(charset.displayName());

	}

}
