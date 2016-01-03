package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.InfoAttr;

public interface InfoAttrMapper {
    int batchInsert(List<InfoAttr> list);

    List<InfoAttr> selectByGoodsId(Integer goodsId);
    
    void deletByGoodsId(Integer goodsId);
    
    List<Map<String, Object>> selectByInfoId(String infoattr_id);
    
    List<Map<String, Object>> getGoodsAtrrsByInfoId(String infoattr_id);
    
    /**
     * 根据infoattr_id,查询商品所有属性和属性值
     * @param infoattr_id
     * @return
     * @author 陈显革
     * @date 2014-09-17
     */
    List<Map<String, Object>> selectInfoAttrsById(String infoattr_id);
    
    /**
     * 根据infoattr_id,查询attr_id, attr_value, value_id
     * @param infoattr_id
     * @return
     * @author 陈显革
     * @date 2014-09-19
     */
    List<Map<String, Object>> selectById(String infoattr_id);
    
    /**
     * @param infoattr_id
     * @return
     * 根据分类属性值 查找对应的属性
     */
    List<Map<String, Object>> getInfoAttrByValueId(String value_id);
    
    
}