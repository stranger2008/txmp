package com.xingfugo.util;

import java.io.File;

public final class FileSepratorUtils {
	
	public static boolean pathStartWithFileSeprator(String filePath){
		if(filePath == null){
			return false;
		}
		
		return (filePath.startsWith("/") || filePath.startsWith("\\"));
	}
	
	public static boolean pathEndWithFileSeprator(String filePath){
		if(filePath == null){
			return false;
		}
		
		return (filePath.endsWith("/") || filePath.endsWith("\\"));
	}
	
	public static String plusPath(String parentPath, String childPath){
		if(parentPath == null){
			return childPath;
		}
		
		if(childPath == null){
			return parentPath;
		}
		
		boolean parentEnd = pathEndWithFileSeprator(parentPath);
		boolean childStart = pathStartWithFileSeprator(childPath);
		StringBuffer buf = new StringBuffer(parentPath);
		if(parentEnd && childStart){
			buf.deleteCharAt(buf.length() - 1);
			buf.append(childPath);
			return buf.toString();
		}
		
		if(!parentEnd && !childStart){
			buf.append(File.separator);
			buf.append(childPath);
			return buf.toString();
		}
		
		buf.append(childPath);
		return buf.toString();
	}
	
	public static String subtractPath(String fullPath, String parentPath){
		if(fullPath == null){
			return null;
		}
		
		if(parentPath == null){
			return fullPath;
		}
		
		File fullPathFile = new File(fullPath);
		String fullAbsolutePath = fullPathFile.getAbsolutePath();
		File parentPathFile = new File(parentPath);
		String parentAbsolutePath = parentPathFile.getAbsolutePath();
		
		int i = fullAbsolutePath.indexOf(parentAbsolutePath);
		if(i < 0){
			return fullAbsolutePath;
		}
		
		return fullAbsolutePath.substring(i+parentAbsolutePath.length());
	}
	
	public static String regularPath(String filePath, boolean forceLinuxStyle){
		if(filePath == null){
			return null;
		}
		
		if(forceLinuxStyle || File.separator.equals("/")){
			return filePath.replaceAll("\\\\", "/");
		}
		
		return filePath.replaceAll("/", "\\\\");
	}
}
