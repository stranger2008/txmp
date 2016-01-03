package com.xingfugo.business.module.mybatis;

/**
 * MySQL方言类
 * @author yu
 */
public class MySQLDialect extends MybatisDialect {

	@Override
	public String getLimitString(String sql, long offset, long limit) {
		StringBuffer buf = new StringBuffer( sql.length() + 20 );
		buf.append( sql );
		
		boolean hasOffset = (offset > 0 || forceLimitUsage());
		if(hasOffset){
			buf.append(" limit ").append(offset).append(", ").append(limit);
		}
		else{
			buf.append(" limit ").append(limit);
		}
		
		return buf.toString();
	}
}
