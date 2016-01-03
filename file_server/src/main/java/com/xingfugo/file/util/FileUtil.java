package com.xingfugo.file.util;

import java.io.File;


public class FileUtil {
	/**
	 * 项目根路径
	 */
	private static String rootpath;
	
	public static final String FILETYPE = "FILE";
	public static final String IMAGETYPE ="IMG";
	
	/**
	 * 拼接文件路径
	 * 
	 * @param pathNames
	 * @return
	 */
	public static String connectFilePath(String... pathNames) {
	
		if(pathNames==null){
			return null;
		}
		int len = pathNames.length;
		 
		StringBuilder bf = new StringBuilder();
		for(int i =0 ; i<len;i++){
			String path = pathNames[i];
			if(path.contains(File.separator)){
				path = path.replace(File.separator,"/");
			};
			if(path.startsWith("/")){
				path = path.substring(1);
			}
			if(!path.endsWith("/")){
				path = path+"/";
			}
			bf.append(path);
		}
		 
		return bf.toString();
	}
	
	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFileExt(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return "";
	}
	
	/**
	 * 获取类路径
	 * @param name
	 * @return
	 */
	private static String getPath(Class name) {
		String strResult = null;
		if (System.getProperty("os.name").toLowerCase().indexOf("window") > -1) {
			strResult = name.getResource("/").toString().replace("file:/", "")
					.replace("%20", " ");
		} else {
			strResult = name.getResource("/").toString().replace("file:", "")
					.replace("%20", " ");
		}
		return strResult;
	}

	/**
	 * 获取项目根路径
	 * @return
	 */
	public static String getRootpath() {
		
		if (rootpath != null) {
			return rootpath;
		} else {
			String root_path = getPath(FileUtil.class).replace(
					"WEB-INF/classes/", "");
			setRootpath(root_path);
			return root_path;
		}
	}

	public static void setRootpath(String rootpath) {
		FileUtil.rootpath = rootpath;
	}
	
	public static void main(String[] args){
		 
		System.out.println(System.getProperty("os.name"));
		
	}
}
