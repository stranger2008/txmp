package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.module.IncFeedback;
import com.xingfugo.business.module.mybatis.BasePageForm;

public interface IncFeedbackDao {

    public void insert(IncFeedback record);

    public List<IncFeedback> selectByPage(BasePageForm pageForm);

    public IncFeedback selectByPrimaryKey(Integer id);

}