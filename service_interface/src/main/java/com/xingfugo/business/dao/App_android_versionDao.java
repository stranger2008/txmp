package com.xingfugo.business.dao;

import com.xingfugo.business.module.App_android_version;

/**
 * @function 功能 手机端版本dao层业务接口
 * @author  创建人史倩倩
 * @date  创建日期 Fri Sep 12 15:27:55 CST 2014
 */
public interface App_android_versionDao extends GenericDao<App_android_version,String>{
	public App_android_version selectMostRecentRelease(String pack);

}













