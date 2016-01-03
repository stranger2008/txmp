package com.xingfugo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.xingfugo.business.module.common.FieldValidateError;
import com.xingfugo.business.module.common.OpError;

public final class SpringFramewokUtil {
	
	public static List<OpError> handleValidateErr(Errors errors){
		return handleValidateErr(errors, null);
	}
	
	/**
	 * 将Spring错误消息转换成特定对象
	 * @param errors Spring错误消息对象
	 * @param skipFields 忽略的字段
	 * @return 转换的消息对象列表
	 */
	public static List<OpError> handleValidateErr(Errors errors, List<String> skipFields){
		List<FieldError> fieldErrs = errors.getFieldErrors();
		List<ObjectError> globalErrs = errors.getGlobalErrors();

		int fieldErrSize = (fieldErrs == null ? 0 : fieldErrs.size());
		int globalErrSize = (globalErrs == null ? 0 : globalErrs.size());
		int size = fieldErrSize + globalErrSize;
		if(size == 0){
			return Collections.emptyList();
		}
		
		boolean skiped = !CollectionUtils.isEmpty(skipFields);
		List<OpError> errList = new ArrayList<OpError>(size);
		
		//字段错误列表转换
		if(fieldErrSize > 0){
			FieldError error = null;
			for(int i = 0; i < fieldErrSize; i++){
				error = fieldErrs.get(i);
				
				String field = error.getField();
				if(skiped && skipFields.contains(field)){
					continue;
				}
				
				String code = error.getCode();
				String msg = error.getDefaultMessage();
				
				FieldValidateError err = new FieldValidateError(field, code, msg);
				
				errList.add(err);
			}
		}
		
		//全局错误（不与具体字段相关）转换
		if(globalErrSize > 0){
			ObjectError error = null;
			for(int i = 0; i < globalErrSize; i++){
				error = globalErrs.get(i);
				
				String code = error.getCode();
				String msg = error.getDefaultMessage();
				
				OpError err = new OpError(code, msg);
				
				errList.add(err);
			}
		}
		
		return errList;
	}
	
	/**
	 * Spring框架手动生成错误消息封装
	 * @param errors Spring验证错误消息对象
	 * @param key 错误消息对应的key值
	 * @param messageSource 信息资源对象
	 * @param loc 本地对象
	 */
	public static void errReject(Errors errors, String key,
			MessageSource messageSource, Locale loc) {
		errors.reject(key, messageSource.getMessage(key, null, loc));
	}
	
	public static void errRejectValue(Errors errors, String key, String field,
			MessageSource messageSource, Locale loc) {
		errors.rejectValue(field, key, messageSource.getMessage(key, null, loc));
	}
}
