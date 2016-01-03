package com.xingfugo.util;

import java.util.List;

import com.xingfugo.business.module.mybatis.DefaultPagingBean;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.mybatis.PageUtil;

public final class PageResultBuilder {
	
	public static PageResult build(DefaultPagingBean pagingBean, List<?> list){
		PageResult result = new PageResult();
		
		result.setTotal(list == null ? 0 : (int)pagingBean.getTotalRecords());
		result.setSize(pagingBean.getSize());
		result.setCurPage((int)pagingBean.getCurrentPageNum());
		result.setTotalPage((int)pagingBean.getTotalPageNum());
		result.setCurTotal(list == null ? 0 : list.size());
		result.setList(list);
		return result;
	}

	public static PageUtil buildPageBar(PageResult result){
		PageUtil page = new PageUtil();
		page.setPageSize(result.getSize());
		page.setTotalRow(result.getTotal());
		page.setCurPage(result.getCurPage());
		return page;
	}
}
