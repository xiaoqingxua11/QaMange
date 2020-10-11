package com.homework.util;

/**
 * ClassName:    MD5Util
 * Package:    com.homework.util
 * Description:
 * Datetime:    2020/9/27   19:07
 * Author:   ${小情绪}
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util
{
    public static String getMd5String(String source)
    {
        if (null == source || source.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
           /* md.update(source.getBytes());
            byte[] hash = md.digest();*/
            byte[] hash = md.digest(source.getBytes());
            //二进制换换成16进制数字
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }
}

