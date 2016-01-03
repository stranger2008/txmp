package com.xingfugo.business.module.mybatis;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 用来在类中字段上，标识MyBatis分页查询时包含的分页信息
 */
@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Inherited
public @interface APaging {

}
