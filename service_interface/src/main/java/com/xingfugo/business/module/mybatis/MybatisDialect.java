package com.xingfugo.business.module.mybatis;

/**
 * 与数据库相关的方言类，用于数据库执行分页查询时使用
 * @author yu
 *
 */
public abstract class MybatisDialect {
	
	protected boolean forceLimitUsage() {
		return false;
	}
	
	/**
	 * 生成用于查询指定范围的SQL
	 * @param sql 原始SQL
	 * @param offset 偏移量
	 * @param limit 最多记录数
	 * @return 用于查询指定范围的SQL
	 */
	protected abstract String getLimitString(String sql, long offset, long limit);
	
	/**
	 * 统计此次查询的记录总数
	 * @param sql 原始查询SQL
	 * @return 统计总数的SQL
	 */
	protected String getCountString(String sql){
		StringBuffer buf = new StringBuffer(sql.length() + 40);
		buf.append("select count(0) from ( ");
		buf.append(sql);
		buf.append(") row_count_");
		return buf.toString();
	}
}
