package com.xingfugo.business.module;

import java.util.ArrayList;
import java.util.List;

public class GoodsAttr {
    private String attrId;

    private String attrName;

    private String attrType;
    
    private boolean required;

    private Integer sortNo;

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

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
}