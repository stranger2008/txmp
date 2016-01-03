package com.xingfugo.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.App_android_versionDao;
import com.xingfugo.business.module.App_android_version;
import com.xingfugo.business.module.mybatis.BasePageForm;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.util.PageResultBuilder;

/**
 * @function 功能 手机端版本Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Fri Sep 12 15:27:55 CST 2014
 */

@Service
public class App_android_versionService extends GenericService<App_android_version,String>{

	private App_android_versionDao appAndroidVersionDao;
	
	public App_android_versionService() {}
	
	@Autowired
	public App_android_versionService(App_android_versionDao appAndroidVersionDao) {
		super(appAndroidVersionDao);
		this.appAndroidVersionDao = appAndroidVersionDao;
	}
	
	public App_android_version selectMostRecentRelease(String pack){
		return appAndroidVersionDao.selectMostRecentRelease(pack);
	}
	
}

