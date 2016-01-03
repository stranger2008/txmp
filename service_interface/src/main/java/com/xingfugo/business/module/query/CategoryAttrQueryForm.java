package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 实体分类表单查询实体
 * @author 创建人 刘香玲
 * @date 创建日期 Tue Sep 02 14:07:15 CST 2014
 */

public class CategoryAttrQueryForm extends BasePageForm{
	private String attrName;
    private String catAttr;
    private String attrType;
    private String isMust;
    private String moduleType;
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getCatAttr() {
		return catAttr;
	}
	public void setCatAttr(String catAttr) {
		this.catAttr = catAttr;
	}
	public String getAttrType() {
		return attrType;
	}
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}
	public String getIsMust() {
		return isMust;
	}
	public void setIsMust(String isMust) {
		this.isMust = isMust;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
    
}
