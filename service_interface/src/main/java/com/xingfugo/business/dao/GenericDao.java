package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.mybatis.BasePageForm;

public interface GenericDao<T,Pk> {
	
	//插入
	public void insert(T t);
	
	//插入返回主键
	public String insertGetPk(T t);
	
	//修改
	public void update(T t);
	
	//删除
	public void delete(Pk id);
	
	//根据主键获取对象实体
	public T getByPk(Pk id);
	
	//分页查询
	public List<Map<String,Object>> getListByPage(BasePageForm basePageForm);
	
	//查询
	public List<Map<String,Object>> getList(Map<String, String> map);
	
}
