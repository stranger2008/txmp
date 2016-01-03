package com.xingfugo.business.module.mybatis;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.ReflectionUtils;

public final class PageFieldUtils {

	public static boolean hasField(Object target, String fieldName,
			Class<?> type) {
		Object field = getFieldValue(target, fieldName);
		return type.isInstance(field);
	}

	public static Object getFieldValue(Object target, String fieldName) {
		Field field = ReflectionUtils.findField(target.getClass(), fieldName);
		ReflectionUtils.makeAccessible(field);
		return ReflectionUtils.getField(field, target);
	}

	public static void setFieldValue(Object target, String fieldName,
			Object value) {
		Field field = ReflectionUtils.findField(target.getClass(), fieldName);
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, target, value);
	}

	public static String findPageFieldNameByAnnotation(Object target, Class<? extends Annotation> annotationclass) {
		Class<?> clazz = target.getClass();
		/*if(!clazz.isAnnotationPresent(annotationclass)){
			return null;
		}*/
		
		//Field[] fields = clazz.getDeclaredFields();
		List<Field> fields = getFields(clazz, true);
		if (fields == null || fields.size() == 0) {
			return null;
		}

		Field f = null;
		for (int i = 0; i < fields.size(); i++) {
			f = fields.get(i);
			ReflectionUtils.makeAccessible(f);
			if (!f.isAnnotationPresent(annotationclass)) {
				continue;
			}

			String fn = f.getName();
			Object obj = getFieldValue(target, fn);
			if (obj instanceof IPaging) {
				return fn;
			}
		}

		return null;
	}
	
	public static List<Field> getFields(Class<?> clazz, boolean includeSuperClass){
		if(clazz == null){
			return Collections.EMPTY_LIST;
		}
		
		List<Field> fields = new ArrayList<Field>();
		Field[] fs = clazz.getDeclaredFields();
		if(fs != null && fs.length > 0){
			for(int i = 0; i < fs.length; i++){
				fields.add(fs[i]);
			}
		}
		
		if(!includeSuperClass){
			return fields;
		}
	
		Class superClass = clazz.getSuperclass();
		if(superClass.equals(Object.class)){
			return fields;
		}
		
		fields.addAll(getFields(superClass, true));
		return fields;
	}
}
