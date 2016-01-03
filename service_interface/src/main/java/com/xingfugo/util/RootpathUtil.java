package com.xingfugo.util;


/**
 * 读取root path综合类
 * 
 * @author 李良林
 */
public class RootpathUtil {
	
	private static String rootpath;
	
	/**
	 * 得到某一个类的路径
	 * 
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

	public static String getRootpath() {
		if (rootpath != null) {
			return rootpath;
		} else {
			String root_path = getPath(RootpathUtil.class).replace(
					"WEB-INF/classes/", "");
			setRootpath(root_path);
			return root_path;
		}
	}

	public static String getClassPath() {
		return getPath(RootpathUtil.class);
	}

	public static void setRootpath(String rootpath) {
		RootpathUtil.rootpath = rootpath;
	}

}
