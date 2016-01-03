package com.xingfugo.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.InfoAttrMapper;
import com.xingfugo.business.module.InfoAttr;

@Service
public class InfoAttrService {
	@Autowired
	private InfoAttrMapper mapper;
	
	public void batchInsert(List<InfoAttr> infoAttrList){
		if(infoAttrList == null || infoAttrList.isEmpty()){
			return;
		}
		
		mapper.batchInsert(infoAttrList);
	}
	
	public void deletByGoodsId(Integer goodsId){
		mapper.deletByGoodsId(goodsId);
	}
	
	public List<InfoAttr> selectByGoodsId(Integer goodsId){
		return mapper.selectByGoodsId(goodsId);
	}
	
	public List<Map<String, Object>> getGoodsAtrrsByInfoId(String infoattr_id){
		
		return mapper.getGoodsAtrrsByInfoId(infoattr_id);
	}
	
	public List<Map<String, Object>> selectByInfoId(String infoattr_id){
		return mapper.selectByInfoId(infoattr_id);
	}
	
	/**
     * 根据infoattr_id,查询商品所有属性和属性值
     * @param infoattr_id
     * @return
     * @author 陈显革
     * @date 2014-09-17
     */
    public List<Map<String, Object>> selectInfoAttrsById(String infoattr_id) {
    	return mapper.selectInfoAttrsById(infoattr_id);
    }
    
    /**
     * 根据infoattr_id,查询attr_id, attr_value, value_id
     * @param infoattr_id
     * @return
     * @author 陈显革
     * @date 2014-09-19
     */
    public List<Map<String, Object>> selectById(String infoattr_id) {
    	return mapper.selectById(infoattr_id);
    }
    
    /**
     * @param value_id
     * @return
     * 根据分类属性值 查找对应的属性
     */
    public List<Map<String, Object>> getInfoAttrByValueId(String value_id){
    	return mapper.getInfoAttrByValueId(value_id);
    }
}
