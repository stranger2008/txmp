package com.xingfugo.web.admin.chinapay;

import java.util.Properties;

public class ChinapayTest {

	public static void main(String[] args) {
		Properties pro = ChinapayConfig.getInstance().getProperties();
		System.out.println(pro.getProperty("chinapay.merid"));
		System.out.println(ChinapayTest.class.getResource(""));
	}
}
