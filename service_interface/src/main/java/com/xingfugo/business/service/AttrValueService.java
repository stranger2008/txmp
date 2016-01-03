package com.xingfugo.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.AttrValueMapper;
import com.xingfugo.business.module.AttrValue;

@Service
public class AttrValueService {

	@Autowired
	private AttrValueMapper attrValueMapper;
	
	/**
	 * 根据id串返回集合
	 * @param tradeIds id串，逗号隔开
	 * @return
	 */
	public List<Map<String, Object>> selectByTradeId(String tradeIds){
		return attrValueMapper.selectByTradeId(tradeIds);
	}
	
	/**
	 * 根据id串返回集合
	 * @param tradeIds id串，逗号隔开
	 * @return
	 */
	public List<AttrValue> selectByAttr_id(String tradeIds){
		return attrValueMapper.selectByAttr_id(tradeIds);
	}
	
	/**
     * 查询属性值列表
     * @param map
     * @return
     * @author 陈显革
     * @date 2014-09-16
     */
    public List getList(Map<String, String> map) {
    	return attrValueMapper.getList(map);
    }
    
    /**
     * 新增
     * @param attrValue
     * @author 陈显革
     * @date 2014-09-16
     */
    public void insert(AttrValue attrValue) {
    	attrValueMapper.insert(attrValue);
    }
    
    /**
     * 修改
     * @param attrValue
     * @author 陈显革
     * @date 2014-09-16
     */
    public void update(AttrValue attrValue) {
    	attrValueMapper.update(attrValue);
    }
    
    /**
     * 删除
     * @param id
     * @author 陈显革
     * @date 2014-09-16
     */
    public void delete(String id) {
    	attrValueMapper.delete(id);
    }
    
    /**
     * 删除
     * @param id
     * @author 陈显革
     * @date 2014-09-16
     */
    public void deleteByAttrId(String attrId) {
    	attrValueMapper.deleteByAttrId(attrId);
    }
    
    /**
     * 根据id查询属性值
     * @param tradeId
     * @return
     * @author 陈显革
     * @date 2014-09-17
     */
    public AttrValue selectById(String tradeId) {
    	return attrValueMapper.selectById(tradeId);
    }
	
}
