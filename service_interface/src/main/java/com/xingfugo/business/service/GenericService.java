package com.xingfugo.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.GenericDao;
import com.xingfugo.business.module.mybatis.BasePageForm;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.util.PageResultBuilder;

@Service
public class GenericService<T,Pk>{
	
	private GenericDao<T,Pk> genericDao;
	
	public GenericService() {}
	
	public GenericService(GenericDao<T, Pk> genericDao) {
        this.genericDao = genericDao;
    }
	
	public void insert(T t){
		genericDao.insert(t);
	}
	
	public String insertGetPk(T t){
		genericDao.insertGetPk(t);
		//return t.getPk();
		return "0";
	}
	
	public void update(T t){
		genericDao.update(t);
	}
	
	public void delete(Pk id){
		genericDao.delete(id);
	}
	
	public T getByPk(Pk id){
		if(id instanceof String && !id.toString().matches("[0-9]+")){
			return null;
		}
		return (T)genericDao.getByPk(id);
	}
	
	public PageResult getListByPage(BasePageForm basePageForm){
		List<Map<String, Object>> list = genericDao.getListByPage(basePageForm);
		PageResult result = PageResultBuilder.build(basePageForm.getPg(), list);
		return result;
	}
	
	public List<Map<String,Object>> getList(Map<String,String> map){
		return genericDao.getList(map);
	}
	
}
