package com.xingfugo.util;

import org.apache.commons.lang.StringUtils;

public class SortFiledHandler {
	private static final String SORT_ASC = "asc";
	private static final String SORT_DESC = "desc";
	
	private static final String STRING_SPACE = " ";
	public static final String STRING_FIELD_SEPRATOR = ", ";
	
	/**
	 * 排序字段映射处理
	 * @param origSortField 原始排序字符串
	 * @param fieldsMapper 映射的字段数组
	 * @param defaultSort 默认排序语句
	 * @return 进行映射后的排序语句
	 */
	public static String handle(String origSortField, final String[] fieldsMapper,
			final String defaultSort, SortFieldModifyer modifyer){
		if(StringUtils.isEmpty(origSortField) || StringUtils.isEmpty(origSortField.trim())){
			return defaultSort;
		}
		
		int mapperSize = (fieldsMapper == null) ? 0 : fieldsMapper.length;
		if(mapperSize == 0){
			return defaultSort;
		}
		
		origSortField = origSortField.trim();
		int len = origSortField.length();
		//两个字符一组，组成排序字段+排序模式的定义
		int sortFiledCount = len / 2;
		//只允许最后一个缺少排序模式
		boolean haveSingleSortField = (len % 2 == 1);
		
		StringBuffer buf = new StringBuffer();
		String sortFiledItem = null;
		String mappedStr = null;
		int fieldIndex;
		while(sortFiledCount > 0){
			sortFiledItem = origSortField.substring(0, 2);
			fieldIndex = Integer.parseInt(sortFiledItem.substring(0, 1));
			
			if(fieldIndex < mapperSize){
				mappedStr = fieldsMapper[fieldIndex] + STRING_SPACE +  
				((sortFiledItem.charAt(1) == '1') ? SORT_DESC : SORT_ASC);
			}
			else{
				mappedStr = null;
			}
			
			if(modifyer != null && modifyer.trigger(fieldIndex)){
				mappedStr = modifyer.modify(mappedStr);
			}
			
			if(mappedStr != null){
				buf.append(mappedStr);
				buf.append(STRING_FIELD_SEPRATOR);
			}
			
			origSortField = origSortField.substring(2);;
			sortFiledCount--;
		}
		
		if(haveSingleSortField){
			fieldIndex = Integer.parseInt(origSortField);
			
			if(fieldIndex < mapperSize){
				mappedStr = fieldsMapper[fieldIndex];  
			}
			else{
				mappedStr = null;
			}
			
			if(modifyer != null && modifyer.trigger(fieldIndex)){
				mappedStr = modifyer.modify(mappedStr);
			}
			
			if(mappedStr != null){
				buf.append(mappedStr);
			}
		}
		
		if(buf.toString().endsWith(STRING_FIELD_SEPRATOR)){
			buf.delete(buf.length() - 2, buf.length());
		}
		
		return buf.toString();
	}
}
