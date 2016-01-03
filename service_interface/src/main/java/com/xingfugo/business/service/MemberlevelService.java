package com.xingfugo.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.MemberlevelDao;
import com.xingfugo.business.module.Memberlevel;
import com.xingfugo.business.module.mybatis.BasePageForm;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.util.PageResultBuilder;

/**
 * @function 功能 等级配置Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Wed Sep 24 10:36:12 CST 2014
 */

@Service
public class MemberlevelService {

	@Autowired
	private MemberlevelDao memberlevelDao;
	
	public PageResult getListByPage(BasePageForm basePageForm){
		List<Map<String, Object>> list = memberlevelDao.getListByPage(basePageForm);
		PageResult result = PageResultBuilder.build(basePageForm.getPg(), list);
		return result;
	}
	
	public Memberlevel getByPk(String id) {
		return memberlevelDao.getByPk(id);
	}
	
	public void update(Memberlevel memberlevel) {
		memberlevelDao.update(memberlevel);
	}
	

	public List<Map<String,Object>> getList(Map<String,String> map){
		return memberlevelDao.getList(map);
	}

}

