package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.module.AppClientInfo;
import com.xingfugo.business.module.mybatis.BasePageForm;

public interface AppClientInfoDao {
  
    public void insert(AppClientInfo record);

    public List<AppClientInfo> selectByPage(BasePageForm pageForm);

    public AppClientInfo selectByPrimaryKey(Integer id);

}