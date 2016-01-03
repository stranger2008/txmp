package com.xingfugo.util;

import java.util.UUID;
// 生成UUID
public class UUIDGenerator {
	public UUIDGenerator() {
	}
	//生成一个UUID
	public static String getUUID() {
		String s = UUID.randomUUID().toString(); 
		// 去掉"-"符号
		return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	}
	//获得指定数量的UUID
	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}
		return ss;
	}

	public static void main(String[] args) {
//		String[] ss = getUUID(10);
//		for (int i = 0; i < ss.length; i++) {
//			System.out.println("ss["+i+"]====="+ss[i]);
//		}
		
		System.out.println(getUUID());
	}
}