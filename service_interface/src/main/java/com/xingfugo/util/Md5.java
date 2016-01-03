/*
 * ISConsole Copyright 2011 xingfugo COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.xingfugo.util
 * FileName: Md5.java
 */
package com.xingfugo.util;

/**
 * @function 功能  MD5加密		
 * @author  创建人 李良林
 * @date  创建日期  Jun 25, 2011
*/
public class Md5 {
	
	public static void main(String args[]){
		//System.out.println(Md5.getMD5("ruibaotong1xfg_sso_code".getBytes()));
	}
	
	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];

				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

}
