package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xingfugo.business.module.AttrValue;

public interface AttrValueMapper {

    public List<Map<String, Object>> selectByTradeId(@Param(value = "tradeIds") String tradeIds);
    
    
    
    public List<AttrValue> selectByAttr_id(@Param(value = "tradeIds") String tradeIds);
    
    
    /**
     * 查询属性值列表
     * @param map
     * @return
     * @author 陈显革
     * @date 2014-09-16
     */
    public List getList(Map<String, String> map);
    
    /**
     * 新增
     * @param attrValue
     * @author 陈显革
     * @date 2014-09-16
     */
    public void insert(AttrValue attrValue);
    
    /**
     * 修改
     * @param attrValue
     * @author 陈显革
     * @date 2014-09-16
     */
    public void update(AttrValue attrValue);
    
    /**
     * 删除
     * @param id
     * @author 陈显革
     * @date 2014-09-16
     */
    public void delete(String id);
    
    /**
     * 删除
     * @param id
     * @author 陈显革
     * @date 2014-09-16
     */
    public void deleteByAttrId(String attrId);
    
    /**
     * 根据id查询属性值
     * @param tradeId
     * @return
     * @author 陈显革
     * @date 2014-09-17
     */
    public AttrValue selectById(String tradeId);

}