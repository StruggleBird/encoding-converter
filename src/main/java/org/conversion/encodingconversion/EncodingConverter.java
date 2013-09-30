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
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.conversion.utils.Utils;

/**
 * @version 1.0 2010-9-28
 * @author zt
 * 
 */
public class EncodingConverter implements IEncodingConverter {

	private String sourceEncoding;

	private String targetEncoding;

	private String fileSuffix;

	private File sourceFile;

	private File targetFile;

	/**
	 * 调用转换方法
	 * @param sourceRoot
	 * @param targetRoot
	 * @param filterSuffix
	 * @throws Exception
	 */
	public static void convert(File sourceRoot, File targetRoot,String sourceEncoding,String targetEncoding, String filterSuffix) throws Exception {
		System.out.println("specific sourceEncoding:"+sourceEncoding);
		if (sourceRoot.isDirectory()) {
			String [] extensions = (filterSuffix==null || filterSuffix.equals(""))?null:filterSuffix.split(";");
			List<File> fileList = (List<File>) FileUtils.listFiles(sourceRoot, extensions, true);
			for (File sourceFile : fileList) {
				EncodingConverter converter = new EncodingConverter();
				converter.setSource(sourceFile);
				String suffixPath = sourceFile.getAbsolutePath().substring(sourceRoot.getAbsolutePath().length());
				String targetFilePath = targetRoot.getAbsoluteFile() + suffixPath;
				File targetFile = new File(targetFilePath);
				converter.setTarget(targetFile);
				converter.setSourceEncoding(sourceEncoding);
				converter.setTargetEncoding(targetEncoding);
				converter.setFileSuffix(filterSuffix);
				converter.encode();
			}
		}else {
			EncodingConverter converter = new EncodingConverter();
			converter.setSource(sourceRoot);
			String suffixPath = sourceRoot.getAbsolutePath().substring(sourceRoot.getAbsolutePath().length());
			String targetFilePath = targetRoot.getAbsoluteFile() + suffixPath;
			File targetFile = new File(targetFilePath);
			converter.setTarget(targetFile);
			converter.setSourceEncoding(sourceEncoding);
			converter.setTargetEncoding(targetEncoding);
			converter.setFileSuffix(filterSuffix);
			converter.encode();
		}
	}

	/**
	 * 编码单个文件，并保存
	 */
	public void encode() throws Exception {

		InputStream in = new FileInputStream(sourceFile);
		String detectEncoding  = getFileEncode(in);
		System.out.println("[" + sourceFile.getName() + "] is processing"
				+ " ;  detect encoding:" + detectEncoding);
		if (detectEncoding.equals(targetEncoding.toUpperCase())) {
			if (!sourceFile.getAbsoluteFile().equals(targetFile.getAbsoluteFile())) {
				FileUtils.copyFile(sourceFile, targetFile);
			}
			
			System.out.println("source encoding is " + sourceEncoding +", don't need to encode.");
			return;
		}
		
		//自动检测源文件编码
		if (sourceEncoding == null || sourceEncoding.equals("")
				|| Utils.AUTO_DETECT.equals(sourceEncoding)) {
			sourceEncoding = detectEncoding;
		}
		

		String fileContent = FileUtils.readFileToString(sourceFile, sourceEncoding);
		FileUtils.writeStringToFile(targetFile, fileContent, targetEncoding);
	}

	/**
	 * 获取文件编码
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public String getFileEncode(InputStream in) throws IOException {
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
		return charset.displayName();
	}

	public void setSource(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	public void setSourceEncoding(String sourceEncoding) {
		this.sourceEncoding = sourceEncoding;
	}

	public void setTarget(File targetFile) {
		this.targetFile = targetFile;
	}

	public void setTargetEncoding(String targetEncoding) {
		this.targetEncoding = targetEncoding;
	}

	public synchronized String getFileSuffix() {
		return fileSuffix;
	}

	public synchronized void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public synchronized File getSourceFile() {
		return sourceFile;
	}

	public synchronized void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	public synchronized File getTargetFile() {
		return targetFile;
	}

	public synchronized void setTargetFile(File targetFile) {
		this.targetFile = targetFile;
	}

	public synchronized String getSourceEncoding() {
		return sourceEncoding;
	}

	public synchronized String getTargetEncoding() {
		return targetEncoding;
	}

}
