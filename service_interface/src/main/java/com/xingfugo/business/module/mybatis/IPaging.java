package com.xingfugo.business.module.mybatis;

/**
 * 用于记录集的分页计算
 * @author yu
 */
public interface IPaging {
	/**
	 * 根据总记录数和每页记录条数，返回总页数
	 * @return 总页数
	 */
	public long getTotalPageNum();

	/**
	 * 返回总记录数
	 * @return 总记录数
	 */
	public long getTotalRecords();
	
	/**
	 * 设置总记录数
	 * @param totalRecords 总记录数
	 */
	public void setTotalRecords(long totalRecords);
	
	/**
	 * 返回当前页码，从1开始（记录数不为零的情况下）
	 * @return 当前页码
	 */
	public long getCurrentPageNum();

	/**
	 * 返回每页记录数
	 * @return 每页记录数
	 */
	public int getSizePerPage();

	/**
	 * 设置每页记录数
	 * @param sizePerPage 每页记录数
	 */
	public void setSizePerPage(int sizePerPage);
	
	/**
	 * 设置当前页码，从1开始
	 * @param pageNum 当前页码
	 */
	public void setCurrenPageNum(long pageNum);
	
	/**
	 * 返回当前页记录索引开始值，从0开始
	 * @return 当前页记录索引开始值
	 */
	public long getCurrentPageStartIndex();

	/**
	 * 返回当前页记录索引最大值，从0开始
	 * @return 当前页记录索引最大值
	 */
	public long getCurrentPageEndIndex();
}
