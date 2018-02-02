package com.ifinance.tool;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ifinance.base.BaseController;

public class PublicUtils {

	public static String createUUID() {
		// 随机ID
		UUID uuid = UUID.randomUUID();
		String uid = uuid.toString().replace("-", "");
		return uid;
	}

	/**
	 * 创建随机短信验证码
	 * 
	 * @param charCount
	 * @return
	 */
	public static String createSMSCode(int charCount) {
		String charValue = "";
		for (int i = 0; i < charCount; i++) {
			char c = (char) (randomInt(0, 10) + '0');
			charValue += String.valueOf(c);
		}
		return charValue;
	}

	public static int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}

	public static boolean emptyString(String str) {
		boolean flag = false;

		if (null == str || "".equals(str.trim())) {
			flag = true;
		}

		return flag;
	}

	/**
	 * 计算百分比
	 * 
	 * @param y
	 * @param z
	 * @return
	 */
	public static int myPercent(int y, int z) {
		String baifenbi = "";
		double baiy = y * 1.0;
		double baiz = z * 1.0;
		double fen = baiy / baiz;
		DecimalFormat df1 = new DecimalFormat("##%");
		baifenbi = df1.format(fen);

		String result = baifenbi.replace("%", "");

		int rt = 0;
		if (Integer.parseInt(result) >= 100) {
			rt = 100;
		} else {
			rt = Integer.parseInt(result);
		}

		return rt;
	}

	public static boolean isEmail(String email) {
		boolean tag = true;
		final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	public static String getLocalIP() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] ipAddr = addr.getAddress();
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}
		// System.out.println(ipAddrStr);
		return ipAddrStr;
	}

	public static String getRemoveIP(BaseController bc) {
		// 过滤轰炸
		String ip = bc.getRequest().getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = bc.getRequest().getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = bc.getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = bc.getRequest().getRemoteAddr();
		}

		return ip;
	}

}
