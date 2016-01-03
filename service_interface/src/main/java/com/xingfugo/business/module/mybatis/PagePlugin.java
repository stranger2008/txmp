package com.xingfugo.business.module.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;


@Intercepts( { @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PagePlugin implements Interceptor {
	
	private static final String CONF_PAGE_SQL_SIGN = "page_sign";
	private static final String CONF_DIALECT_CLASS = "dialect_class";
	private static final String IPAGING_CLASS = IPaging.class.getName();

	private static Log LOG  =  LogFactory.getLog(PagePlugin.class );   
	
	// 数据库方言类，通过配置文件读取
	private static String DIALECT_CLASS = null; 
	// mapper.xml中，正则匹配来确定拦截的操作
	private static String PAGE_SQL_SIGN = null; 
	
	private static MybatisDialect DIALECT_SINGLETON = null;

	public Object intercept(Invocation ivk) throws Throwable {
		if (!(ivk.getTarget() instanceof RoutingStatementHandler)) {
			return ivk.proceed();
		}

		RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
		BaseStatementHandler delegate = (BaseStatementHandler) PageFieldUtils.getFieldValue(statementHandler, "delegate");
		MappedStatement mappedStatement = (MappedStatement) PageFieldUtils.getFieldValue(delegate, "mappedStatement");

		if (!mappedStatement.getId().matches(PAGE_SQL_SIGN)) {
			return ivk.proceed();
		}

		// 拦截需要分页的SQL
		BoundSql boundSql = delegate.getBoundSql();
		// 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不能为空
		Object parameterObject = boundSql.getParameterObject();
		if (parameterObject == null) {
			throw new NullPointerException("参数对象未实例化！");
		}
		
		// 参数对象必须实现IPaging接口，或者存在一个获取该接口对象的属性
		IPaging page = null;
		try {
			page = getPagingObject(parameterObject);
		} catch (Exception e) {
			LOG.warn("分页处理略过：" + e.getMessage());
			return ivk.proceed();
		}
		
		String sql = boundSql.getSql();
		String countSql = generateCountSql(sql);
		Connection connection = null;
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		long count = 0L;
		
		// 统计总数
		try {
			connection = (Connection) ivk.getArgs()[0];
			countStmt = connection.prepareStatement(countSql);
			BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),
					countSql, boundSql.getParameterMappings(), parameterObject);
			setParameters(countStmt, mappedStatement, countBS, parameterObject);
			rs = countStmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getLong(1);
			}
		} finally {
			if(rs != null){
				rs.close();
			}
			
			if(countStmt != null){
				countStmt.close();
			}
		}

		page.setTotalRecords(count);
		
		//TODO:添加处理代码：如果统计的总数为0，应略过后续操作
		/*if(count == 0L){
		}*/
		
		String pageSql = generatePageSql(sql, page.getCurrentPageStartIndex(), page.getSizePerPage());
		// 将分页sql语句反射回BoundSql.
		PageFieldUtils.setFieldValue(boundSql, "sql", pageSql);
		
		return ivk.proceed();
	}

	/**
	 * 对SQL参数(?)设置参数值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws java.sql.SQLException
	 */
	private static void setParameters(PreparedStatement ps,
			MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings == null) {
			return;
		}
		
		Configuration configuration = mappedStatement.getConfiguration();
		//修改所有分页查询禁止缓存
		configuration.setCacheEnabled(false);
		TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
		MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
		
		for (int i = 0; i < parameterMappings.size(); i++) {
			ParameterMapping parameterMapping = parameterMappings.get(i);
			if (parameterMapping.getMode() == ParameterMode.OUT) {
				continue;
			}			
			
			Object value = null;
			String propertyName = parameterMapping.getProperty();
			PropertyTokenizer prop = new PropertyTokenizer(propertyName);
			if (parameterObject == null) {
				value = null;
			} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				value = parameterObject;
			} else if (boundSql.hasAdditionalParameter(propertyName)) {
				value = boundSql.getAdditionalParameter(propertyName);
			} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
					&& boundSql.hasAdditionalParameter(prop.getName())) {
				value = boundSql.getAdditionalParameter(prop.getName());
				if (value != null) {
					value = configuration.newMetaObject(value).getValue(
							propertyName.substring(prop.getName().length()));
				}
			} else {
				value = (metaObject == null) ? null : metaObject.getValue(propertyName);
			}
			
			TypeHandler typeHandler = parameterMapping.getTypeHandler();
			if (typeHandler == null) {
				throw new ExecutorException(
						"There was no TypeHandler found for parameter "
								+ propertyName + " of statement "
								+ mappedStatement.getId());
			}
			
			typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
		}
	}
	
	private static IPaging getPagingObject(Object parameterObject){
		//1、判断参数对象是否直接实现了Paging接口
		if (parameterObject instanceof IPaging) {
			return (IPaging)parameterObject;
		}
		
		String propertyName = null;
		//2、通过注解方式获取Paging接口对象在父对象中的属性名称
		propertyName = PageFieldUtils.findPageFieldNameByAnnotation(parameterObject, APaging.class);
		LOG.debug(propertyName);
		
		//检测属性对象是否存在，以及是否为空
		if(!PageFieldUtils.hasField(parameterObject, propertyName, IPaging.class)){
			throw new IllegalArgumentException("参数对象["
					+ parameterObject.getClass().getName() + "]中不存在类型为["
					+ IPAGING_CLASS + "]名称为[" + propertyName
					+ "]的属性定义，或属性对象为空");
		}
		
		//获取IPaging接口对象的引用
		return (IPaging) PageFieldUtils.getFieldValue(parameterObject, propertyName);
	}
	
	/**
	 * 根据数据库方言，生成特定的分页sql
	 * @param sql
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	private static String generatePageSql(String sql, long offset, long pageSize) {
		if (DIALECT_CLASS == null) {
			return sql;
		}
		
		String pageSql = DIALECT_SINGLETON.getLimitString(sql, offset, pageSize);
		return pageSql;
	}
	
	private static String generateCountSql(String sql) {
		return DIALECT_SINGLETON.getCountString(sql);
	}

	public Object plugin(Object arg) {
		return Plugin.wrap(arg, this);
	}

	public void setProperties(Properties p)  {
		DIALECT_CLASS = p.getProperty(CONF_DIALECT_CLASS);
		LOG.info("loading dialect class:" + DIALECT_CLASS);
		if (DIALECT_CLASS == null) {
			LOG.error(CONF_DIALECT_CLASS + " property is not found!");
		}
		
		try {
			DIALECT_SINGLETON = (MybatisDialect) Class.forName(DIALECT_CLASS).newInstance();
			LOG.info("dialect class instance OK");
		} catch (InstantiationException e) {
			LOG.error(e);
		} catch (IllegalAccessException e) {
			LOG.error(e);
		} catch (ClassNotFoundException e) {
			LOG.error(e);
		}
		
		PAGE_SQL_SIGN = p.getProperty(CONF_PAGE_SQL_SIGN);
		LOG.info("loading page sql sign:" + PAGE_SQL_SIGN);
		if (PAGE_SQL_SIGN == null) {
			LOG.error(CONF_PAGE_SQL_SIGN + " property is not found!");
		}
	}
}