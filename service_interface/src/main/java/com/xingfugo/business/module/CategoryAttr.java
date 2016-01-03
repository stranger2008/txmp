package com.xingfugo.business.module;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class CategoryAttr {
    private String attrId;

    @NotBlank
    @Length(max=20)
    private String attrName;

    @NotBlank
    @Length(max=200)
    private String catAttr;

    @NotBlank
    @Length(max=1)
    private String attrType;

    @NotBlank
    @Length(max=1)
    private String isMust;

    @NotNull
    @DecimalMin(value="0")
	@DecimalMax(value="2147483647")
    private Integer sortNo;

    @NotBlank
    @Length(max=20)
    private String moduleType;

    @Length(max=200)
    private String defaultVal;

    private List<CategoryAttrValue> attrValueList = new ArrayList<CategoryAttrValue>();
    
    public List<CategoryAttrValue> getAttrValueList() {
		return attrValueList;
	}

	public void setAttrValueList(List<CategoryAttrValue> attrValueList) {
		this.attrValueList = attrValueList;
	}

	public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

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

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }
    
    //所属分类id--扩展属性
    private String catId;
    //所属分类名称--扩展属性
    private String catName;

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
    
}