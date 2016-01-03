package com.xingfugo.business.module.mybatis;

import java.util.List;

public class PageResult {
	//每页记录数
	private int size = 0;
	//总页数
	private int totalPage = 0;
	//总记录数
	private int total = 0;
	//当前页码
	private int curPage = 0;
	//当前页条数
	private int curTotal = 0;
	
	private List<?> list = null;

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurTotal() {
		return curTotal;
	}

	public void setCurTotal(int curTotal) {
		this.curTotal = curTotal;
	}
}
