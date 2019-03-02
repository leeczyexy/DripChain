package com.dali.DripChain.tools;

import java.io.UnsupportedEncodingException;

public class TransEncoding {
    /**
     * 将含有unicode字符串转化为utf8字符串
     * 参考http://www.cnblogs.com/lemon-flm/p/9531250.html
     * 参考https://blog.csdn.net/j080624/article/details/79097738 
     * @param messyCode
     * @return
     */
    public String UnicodeToUtf(String messyCode){
        String[] asciis = messyCode.split ("\\\\u");
        String nativeValue = asciis[0];
        try
        {
            for ( int i = 1; i < asciis.length; i++ )
            {
                String code = asciis[i];
                nativeValue += (char) Integer.parseInt (code.substring (0, 4), 16);
                if (code.length () > 4)
                {
                    nativeValue += code.substring (4, code.length ());
                }
            }
        }
        catch (NumberFormatException e)
        {
            return messyCode;
        }
        return nativeValue;
        
    }

    public String AllUnicodeToUtf(String uniString){
        try {
            String Rstring = new String(uniString.getBytes("UTF-8"),"Unicode");
            return Rstring;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }

}
