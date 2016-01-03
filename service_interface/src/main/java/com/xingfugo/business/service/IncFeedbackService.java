package com.xingfugo.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.IncFeedbackDao;
import com.xingfugo.business.module.IncFeedback;
import com.xingfugo.business.module.mybatis.BasePageForm;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.util.PageResultBuilder;

@Service
public class IncFeedbackService {
	
	@Autowired
	private IncFeedbackDao incFeedbackDao;
	
	public IncFeedback selectByPrimaryKey(Integer id){
		return incFeedbackDao.selectByPrimaryKey(id);
	}
	
	public int insert(IncFeedback incFeedback){
		incFeedbackDao.insert(incFeedback);
		return incFeedback.getId();
	}
	
	public PageResult selectByPage(BasePageForm pageForm){
		List<IncFeedback> list = incFeedbackDao.selectByPage(pageForm);
		return PageResultBuilder.build(pageForm.getPg(), list);
	}
}
