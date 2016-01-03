/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.common.codeBuild
 * FileName: Constants.java
 */
package com.xingfugo.business.common.codeBuild;

/**
 * 功能：存放代码生成工具所需要的所有常量
 * date:2011-07-10
 * @author 李良林
 *
 */
public class Constants {
	
	/*
	 * 链接数据库信息
	 */
	public static final String TYPE = "mysql";
	
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String URL = "jdbc:mysql://192.168.1.206:3306/xingfugo?useUnicode=true&characterEncoding=UTF-8";
	
	public static final String USERNAME = "root";
	
	public static final String PASSWD = "111111";
	
	//src根目录
	public static final String SRC_PATH = "src/main/java/";
	
	//源码根目录
	public static final String ROOT_SAVEPATH = SRC_PATH+"com/xingfugo/business/";
	
	/*
	 * 模板路径
	 */
	public static final String TEMPLATEPATH = ROOT_SAVEPATH+"common/codeBuild/";
	
	/*
	 * 生成后文件存放的位置
	 */
	
	//pojo类存放的位置
	public static final String POJO_SAVEPATH = ROOT_SAVEPATH+"module";
	
	//ibatis的sql.xml文件存放的位置
	public static final String SQLMAP_SAVEPATH = ROOT_SAVEPATH+"/dao";
	
	//dao接口存放的位置
	public static final String DAO_SAVEPATH = ROOT_SAVEPATH+"dao";
	
	//service实现类存放的位置
	public static final String SERVICEIMPL_SAVEPATH = ROOT_SAVEPATH+"/service";
	
	//表单查询实体类存放的位置
	public static final String QUERYFORM_SAVEPATH = POJO_SAVEPATH+"/query";
	
}
