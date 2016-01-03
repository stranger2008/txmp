package com.xingfugo.business.outapi.airline.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;


/**
 * @author wyl
 *	读取机票配置文件
 */
public abstract class   ReadAirLineProperties {
	public static  String NAME;
	public static String PWD;
	//配置文件名称
	public static final String fileName ="airline.properties";
	//授权账号
	public static final String  KEY_NANME="airline.name";
	//授权密码
	public static final String  KEY_PWD="airline.password";
	
	static{
		
		try {
			InputStream fis =ReadAirLineProperties.class.getClassLoader().getResourceAsStream(fileName);	
			PropertyResourceBundle props = new PropertyResourceBundle(fis);
			NAME =props.getString(KEY_NANME);
			PWD=props.getString(KEY_PWD);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
