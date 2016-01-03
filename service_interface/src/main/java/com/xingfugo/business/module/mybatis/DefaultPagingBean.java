package com.xingfugo.business.module.mybatis;

import org.apache.commons.lang.StringUtils;

/**
 * 用于记录集的分页计算
 * @author yu
 */
public class DefaultPagingBean implements IPaging {
	
	//排序编码，客户端提交排序编码，根据排序编码生成排序字段名称
	private String sortCode = null;
	
	//排序字段名称
	private String sortField = null;
	
	private int sizePerPage = 20; 				// 每页记录数
	private long totalPageNum = 0L; 			// 总页数
	private long totalRecords = 0L; 			// 总记录数
	private long currentPageNum = 1L; 			// 当前页码
	private long currentPageStartIndex = 0L; 	// 当前记录起始索引
	private long currentPageEndIndex = 0L; 	// 当前页记录最大索引
	private boolean calced = false;
	
	/**
	 * 构造函数，默认每页10条记录，按第一页计算
	 */
	public DefaultPagingBean(){
		init(1L, 20);
	}

	/**
	 * 构造函数，默认每页10条记录，指定页码
	 * @param pageNum 当前页码，从1开始
	 */
	public DefaultPagingBean(long pageNum){
		init(pageNum, 20);
	}
	
	/**
	 * 构造函数，指定每页记录数，指定页码
	 * @param pageNum 当前页码，从1开始
	 * @param sizePerPage 每页记录条数
	 */
	public DefaultPagingBean(long pageNum, int sizePerPage){
		init(pageNum, sizePerPage);
	}

	private void init(long pageNum, int sizePerPage){
		setSizePerPage(sizePerPage);
		setCurrenPageNum(pageNum);
	}
	
	private void calcAll(){
		if(calced){
			return;
		}
		
		calcTotalPageNum();
		calcCurrentPageNum();
		calcCurrentPageIndex();
		calced = true;
	}
	
	private void calcTotalPageNum(){
		if(totalRecords == 0){
			return;
		}
		
		totalPageNum = totalRecords / sizePerPage;
		if (totalRecords % sizePerPage != 0){
			totalPageNum += 1;
		}
	}
	
	private void calcCurrentPageNum(){
		if(totalPageNum == 0){
			this.currentPageNum = 0;
			return;
		}
		
		if (currentPageNum <= 0){
			this.currentPageNum = 1;
			return;
		}

		if (currentPageNum > totalPageNum){
			this.currentPageNum = totalPageNum;
		}
	}
	
	private void calcCurrentPageIndex(){
		if(this.currentPageNum == 0){
			return;
		}
		
		this.currentPageStartIndex = ((this.currentPageNum - 1) * this.sizePerPage);
		this.currentPageEndIndex = this.currentPageStartIndex + this.sizePerPage - 1;
	}
	
	/**
	 * 根据总记录数和每页记录条数，返回总页数
	 * @return 总页数
	 */
	public long getTotalPageNum() {
		calcAll();
		return totalPageNum;
	}

	/**
	 * 返回总记录数
	 * @return 总记录数
	 */
	public long getTotalRecords() {
		return totalRecords;
	}

	/**
	 * 设置总记录数
	 * @param totalRecords 总记录数
	 */
	public void setTotalRecords(long totalRecords) {
		if(totalRecords > 0){
			this.totalRecords = totalRecords;
			this.calced = false;
		}
	}
	
	/**
	 * 返回当前页码，从1开始（记录总数不为零的情况下）
	 * @return 当前页码
	 */
	public long getCurrentPageNum() {
		calcAll();
		return this.currentPageNum;
	}

	/**
	 * 返回每页记录数
	 * @return 每页记录数
	 */
	public int getSizePerPage() {
		return this.sizePerPage;
	}

	/**
	 * 设置每页记录数
	 * @param sizePerPage 每页记录数
	 */
	public void setSizePerPage(int sizePerPage) {
		if(sizePerPage <= 0 || this.sizePerPage == sizePerPage){
			return;
		}
		
		this.sizePerPage = sizePerPage;
		this.calced = false;
	}
	
	/**
	 * 设置当前页码，从1开始
	 * @param pageNum 当前页码
	 */
	public void setCurrenPageNum(long pageNum){
		if(pageNum <= 0 || this.currentPageNum == pageNum){
			return;
		}
		
		this.currentPageNum = pageNum;
		this.calced = false;
	}
	
	/**
	 * 返回当前页记录索引开始值，从0开始
	 * @return 当前页记录索引开始值
	 */
	public long getCurrentPageStartIndex() {
		calcAll();
		return currentPageStartIndex;
	}

	/**
	 * 返回当前页记录索引最大值，从0开始
	 * @return 当前页记录索引最大值
	 */
	public long getCurrentPageEndIndex() {
		calcAll();
		return currentPageEndIndex;
	}
	
	public int getSize() {
		return getSizePerPage();
	}

	public void setSize(int pageSize) {
		setSizePerPage(pageSize);
	}

	public long getCurPage() {
		return getCurrentPageNum();
	}

	public void setCurPage(long curPageNum) {
		setCurrenPageNum(curPageNum);
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		if(StringUtils.isNotEmpty(sortField)){
			this.sortField = sortField;
		}
	}

	public String getSortCode() {
		return sortCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}	
}
