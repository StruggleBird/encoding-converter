package org.conversion.encodingconversion;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class EncodingConverTest
{
    IEncodingConverter encodingConverter = new EncodingConverter();
    
    
    @Before
    public void setUp()
    {
        encodingConverter = new EncodingConverter();
    }
    
    @Test
    public void testEncode()
    {
        File sourceFile = new File("C:/Documents and Settings/桌面/source/1/Author.java");
        File targetFolder = new File("C:/Documents and Settings/桌面/output/1");
        
        encodingConverter.setSource(sourceFile);
        encodingConverter.setTarget(targetFolder);
        encodingConverter.setTargetEncoding("utf-8");
        
        try
        {
            encodingConverter.encode();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    
}
