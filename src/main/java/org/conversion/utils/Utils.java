package org.conversion.utils;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @version 1.0 2010-9-29
 * @author zt
 *
 */
public  class Utils
{
    private static Map<String , Charset> charsetMap = Charset.availableCharsets();
    
    public static final String AUTO_DETECT = "auto detect";
    
    
    public static Object[] getCharSetList()
    {
        return charsetMap.values().toArray();
    }
    
    public static boolean emptyOrNull(Object object)
    {
        if (object == null)
        {
            return true;
        }

        if ("".equals(object))
        {
            return true;
        }

        return false;
    }
}
