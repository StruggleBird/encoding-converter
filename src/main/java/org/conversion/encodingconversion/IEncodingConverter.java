package org.conversion.encodingconversion;

import java.io.File;

/**
 * interface of EncodingConverter
 * EncodingConverter is a tool for convert text file encoding from one to another.
 * @author zt
 *
 */
public interface IEncodingConverter
{
    /**
     * execute convert to change encoding
     * @throws Exception 
     */
    void encode() throws Exception;
    
    /**
     * set the file which need to convert encoding
     * @param sourceFile sourceFile
     */
    void setSource(File sourceFile);
    
    /**
     * set the folder to save the converted file 
     * @param targetFolder targetFolder
     */
    void setTarget(File targetFolder);
    
    /**
     * set the source file encoding 
     * @param sourceEncoding sourceEncoding
     */
    void setSourceEncoding(String sourceEncoding);
    
    /**
     * set the target file encoding 
     * @param targetEncoding targetEncoding
     */
    void setTargetEncoding(String targetEncoding);
}
