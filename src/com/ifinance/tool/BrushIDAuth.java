package com.ifinance.tool;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class BrushIDAuth
{

	static int byteArrayToInt(byte[] b)
	{
	    byte[] a = new byte[4];
	    int i = a.length - 1, j = b.length - 1;
	    for (; i >= 0 ; i--, j--) 
	    {
	        if(j >= 0)
	            a[i] = b[j];
	        else
	            a[i] = 0;
	    }
	    int v0 = (a[0] & 0xff) << 24;
	    int v1 = (a[1] & 0xff) << 16;
	    int v2 = (a[2] & 0xff) << 8;
	    int v3 = (a[3] & 0xff) ;
	    return v0 + v1 + v2 + v3;
	}
	
	static byte[] arrayCat(byte[] buf1, byte[] buf2)
    {
        byte[] bufret = null;
        int len1 = 0;
        int len2 = 0;
        if(buf1 != null)
            len1 = buf1.length;
        if(buf2 != null)
            len2 = buf2.length;
        if(len1 + len2 > 0)
            bufret = new byte[len1 + len2];
        if(len1 > 0)
            System.arraycopy(buf1, 0, bufret, 0, len1);
        if(len2 > 0)
            System.arraycopy(buf2, 0, bufret, len1, len2);
        return bufret;
    }

	static   byte[] calMD5(byte byteArray[])
    {
        MessageDigest md5 = null;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            e.printStackTrace();
            return null;
        }

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        
        return md5Bytes;

    }

	static  byte[] shortUrl(byte input[])
    {
        byte outChars[] = new byte[6];
        byte decrypt[];
        byte j;

        byte encrypt[] = arrayCat("iite".getBytes(), input);

        byte chars[] =
        {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        int seed;


        decrypt = calMD5(encrypt);



        seed = (decrypt[3] & 0xff) << 24 | (decrypt[2] & 0xff) << 16 | (decrypt[1] & 0xff) << 8 | (decrypt[0] & 0xff);
        int hexint = 0x3FFFFFFF & seed;
        for (j = 0; j < 6; j++)
        {

            int index = 0x0000003D & hexint;

            outChars[j] = chars[index];

            hexint = hexint >> 5;
        }

        return outChars;

    }


    public static boolean checkBrushIdAuth(String brushId,String brushAdd)
    {
    		byte name[] = shortUrl(brushId.getBytes());
        String ble_name = null;
		try {
			ble_name = new String(name,"ascii");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String str = "iite-"+ble_name;
        if(brushAdd.equals(str))
        {
        		return true;
        }else
        {
        		return false;
        }
    }
}
