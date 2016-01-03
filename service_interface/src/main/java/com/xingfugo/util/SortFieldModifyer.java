package com.xingfugo.util;

public interface SortFieldModifyer {
	
	public boolean trigger(int fieldIndex);
	
	public String modify(String origStr);
}
