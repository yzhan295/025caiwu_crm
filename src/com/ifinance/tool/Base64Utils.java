package com.ifinance.tool;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Utils {
	// 加密
	public static String encodeBase64(String str) {

		byte[] encodeBase64 = null;
		try {
			encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new String(encodeBase64);
	}

	// 解密
	public static String decodeBase64(String str) {
		byte[] encodeBase64 = null;
		try {
			encodeBase64 = Base64.decodeBase64(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(encodeBase64);
	}
}