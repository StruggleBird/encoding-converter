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
    
}
