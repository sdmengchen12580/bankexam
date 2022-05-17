package com.udit.bankexam.utils;

import java.security.MessageDigest;

public class MD5Util
{


    /**
     * 返回经MD5加密后的字符串
     *
     * @param str 字符串
     * @return 加密后的字符串
     */
    public static String getMD5Str(String str) throws Exception {
        MessageDigest messageDigest;
        StringBuffer md5StrBuff = new StringBuffer();
        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(str.getBytes("UTF-8"));
        for (byte aByteArray : messageDigest.digest()) {
            if (Integer.toHexString(0xFF & aByteArray).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & aByteArray));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & aByteArray));
        }
        return md5StrBuff.toString().toUpperCase();
    }
}
