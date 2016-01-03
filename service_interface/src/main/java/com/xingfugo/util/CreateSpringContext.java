/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.function
 * FileName: CreateSpringContext.java 
 */

package com.xingfugo.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @function 功能  加载spring的配置文件至内存
 * @author  创建人 李良林
 * @date  创建日期  Jun 27, 2011
 */
public class CreateSpringContext {
	private static ApplicationContext context;
	
	static{
		if(context ==null){
			context = new ClassPathXmlApplicationContext("applicationContext*.xml");
		}
	}
	
	public static ApplicationContext getContext(){
		return context;
	}
}
