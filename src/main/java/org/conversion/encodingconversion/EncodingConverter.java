package org.conversion.encodingconversion;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;

import org.apache.commons.io.FileUtils;

/**
 * @version 1.0 2010-9-28
 * @author zt
 * 
 */
public class EncodingConverter implements IEncodingConverter {

	private String sourceEncoding;

	private String targetEncoding;

	private File sourceFile;

	private File targetFile;

	public void encode() throws Exception {

		InputStream in = new FileInputStream(sourceFile);

		Reader fileReader = null;
		// if (Utils.emptyOrNull(sourceEncoding))
		// {
		// fileReader = new InputStreamReader(in);
		// }
		// else
		// {
		// fileReader = new InputStreamReader(in, sourceEncoding);
		// }
		// StringBuffer sbContent = new StringBuffer();
		// int c = 0;
		// while ((c = fileReader.read()) != -1)
		// {
		// sbContent.append((char)c);
		// }
		// fileReader.close();
		byte[] byContent = FileUtils.readFileToByteArray(sourceFile);
		String fileContent = new String(byContent, targetEncoding);

		FileUtils.writeStringToFile(targetFile, fileContent, targetEncoding);
		// OutputStream out = new FileOutputStream(targetFile);
		// Writer fileWriter = new OutputStreamWriter(out, targetEncoding);
		// fileWriter.write(sbContent.toString());
		// fileWriter.close();

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

}
