package com.subaru.utils;

import java.security.MessageDigest;

public class HashHelper {
	
	 private static final char hexDigitsTab[] = "0123456789abcdef".toCharArray();
	
    public static String md5(String text){
        try {
            MessageDigest md5Md = MessageDigest.getInstance("MD5");
            md5Md.update(text.getBytes("utf-8"));
            return HashHelper.asHexString(md5Md.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public static String asHexString(byte[] bytes) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            byte byte0 = bytes[i];
            str.append(hexDigitsTab[byte0 >>> 4 & 0xf]);
            str.append(hexDigitsTab[byte0 & 0xf]);
        }
        return str.toString();
    }

}
