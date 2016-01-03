package com.xingfugo.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public final class ImgPathUitls {
	private static final String DEFAULT_IMG_PATH_KEY = "img_path";
	//private static final String IMAGE_SERVER = "http://119.161.187.240:8081";
	//访问文件上传服务路径配置文件名
	private static final String CONFIG_FILE_NAME = "config.properties";
	
	//加载文件域路径配置的key
	private static final String CONF_LOADING_URL_KEY = "loading.url";
	//加载文件或图片 路径配置的key
	private static final String FILE_URL_KEY = "filepath";
	private static final String IMG_URL_KEY = "imgpath";
		
	public static final String PROJECT_NAME = "txmp";
	
	//----添加图片访问地址路径
	/**
	 * 处理List集合中图片访问路径 （图片属性字段为img_path）
	 */
	public static void filterImagePath(List<Map<String, Object>> mapList){
		filterImagePath(mapList, DEFAULT_IMG_PATH_KEY);
	}
	/**
	 * 处理List集合中图片访问路径
	 * @param imgPathKey 图片属性字段名称
	 */
	public static void filterImagePath(List<Map<String, Object>> mapList, String imgPathKey){
		if(mapList == null || mapList.size() == 0){
			return;
		}
		
		for(Map<String, Object> goodsMap : mapList){
			filterImagePath(goodsMap, imgPathKey);
		}
	}
	/**
	 * 处理Map集合中图片访问路径 （图片属性字段为img_path）
	 */
	public static void filterImagePath(Map<String, Object> map){
		filterImagePath(map, DEFAULT_IMG_PATH_KEY);
	}
	/**
	 * 处理Map集合中图片访问路径 
	 * @param imgPathKey 图片属性字段名称
	 */
	public static void filterImagePath(Map<String, Object> map, String imgPathKey){
		if(map == null || map.isEmpty()){
			return;
		}
		
		String img_path = (String)map.get(imgPathKey);
		map.put(imgPathKey, filterImagePath(img_path));
	}
	/**
	 * 获取图片访问全路径
	 * @param imgPath
	 * @return
	 */
	public static String filterImagePath(String imgPath){
		String filepath = filterImagePath(imgPath,null);
		return filepath;
	}
	/**
	 * 获取文件服务器访问图片的路径
	 * @return
	 */
	public static String getLoadImgUrl(){ 
		String visitPath = getLoadUrl(IMG_URL_KEY);
		return visitPath;
	}
	/**
	 * 获取文件服务器访问文件的路径
	 * @return
	 */
	public static String getLoadFileUrl(){ 
		String visitPath = getLoadUrl(FILE_URL_KEY);
		return visitPath;
	}
	
	private static String getLoadUrl(String type_path){ 
		PropertiesUtil config_pro = new PropertiesUtil(CONFIG_FILE_NAME);
		try {
			String visitPath = config_pro.readValue(CONF_LOADING_URL_KEY);
			String filePath = config_pro.readValue(type_path);
			visitPath += "/"+PROJECT_NAME+filePath;
			if(visitPath.endsWith("/")){
				visitPath = visitPath.substring(0,visitPath.lastIndexOf("/"));
			}
			return visitPath ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获取不同大小的图片的访问路径
	 * @param imgPath 图片路径
	 * @param w_h 图片大小规格(例： 200_200)
	 * @return
	 */
	public static String filterImagePath(String imgPath , String w_h){
		if(imgPath == null || imgPath.length() == 0){
			return imgPath;
		}
		
		if(imgPath.startsWith("http://")){
			return imgPath;
		}
		
		//单个图片
		if(!imgPath.contains(",")){
			if(!imgPath.startsWith("/")){
				imgPath = "/"+imgPath ;
			}
			imgPath = getImgByStandard(imgPath,w_h);
			return getLoadImgUrl() + imgPath;
		}
		
		//多个图片
		String[] img = imgPath.split(",");
		StringBuffer buf = new StringBuffer();
		int size = img.length;
		for(int i = 0; i < size; i++){
			if(i > 0){
				buf.append(",");
			}
			
			buf.append(getLoadImgUrl());
			String temppath = img[i];
			if(!temppath.startsWith("/")){
				temppath = "/"+temppath;
			}
			temppath = getImgByStandard(temppath,w_h);
			buf.append(temppath);
		}
		
		return buf.toString();
	}
	
	//处理图片路径
	private static String getImgByStandard(String imagepath , String w_h){
		if(!StringUtils.isEmpty(imagepath)&&!StringUtils.isEmpty(w_h)){
			int index = imagepath.lastIndexOf(".");
			if(index!=-1){
				String ext = imagepath.substring(index);
				String fileNm = imagepath.substring(0, index);
				String filePath = fileNm+"_"+w_h+ext;
				return filePath;
			}
		}
		return imagepath;
	}
	
	/**
	 * 根据大小处理List集合中图片访问路径
	 * @param mapList 数据集合
	 * @param w 图片宽(例如： 200)
	 * @param h 图片高(例如： 200)
	 */
	public static void filterImagePath_spec(List<Map<String, Object>> mapList , int width , int height ){
		filterImagePath_spec(mapList, DEFAULT_IMG_PATH_KEY,width+"_"+height);
	}
	/**
	 * 根据大小处理List集合中图片访问路径
	 * @param mapList 数据集合
	 * @param w_h 图片大小(例如： 200_200 或者800_800)
	 */
	public static void filterImagePath_spec(List<Map<String, Object>> mapList , String w_h ){
		filterImagePath_spec(mapList, DEFAULT_IMG_PATH_KEY , w_h);
	}
	/**
	 * 根据大小处理List集合中图片访问路径
	 * @param mapList 数据集合
	 * @param imgPathKey 图片属性字段名称
	 * @param w 图片宽(例如： 200)
	 * @param h 图片高(例如： 200)
	 */
	public static void filterImagePath_spec(List<Map<String, Object>> mapList, String imgPathKey, int width , int height){
		filterImagePath_spec(mapList, imgPathKey,width+"_"+height);
	}
	/**
	 * 根据指定图片规格处理List集合中图片访问路径
	 * @param mapList 数据集合
	 * @param baseImagePath 图片属性字段名称
	 * @param imgPath_spec 指定要生成的缩略图存储字段名和规格的map集合。例如 map 的key = imagePath_200, value = 200_200 
	 */
	public static void filterImagePath_spec(List<Map<String, Object>> mapList,String baseImagePath, Map<String,String> imgPath_spec){
		if(mapList == null || mapList.size() == 0){
			return;
		}
		
		for(Map<String, Object> goodsMap : mapList){
			if(goodsMap == null || goodsMap.isEmpty()){
				continue;
			}
			filterImagePath_spec(goodsMap,baseImagePath,imgPath_spec);
		}
	}
	
	/**
	 * 根据指定图片规格处理Map集合中图片访问路径
	 * @param Map 数据集合
	 * @param baseImagePath 图片属性字段名称
	 * @param imgPath_spec 指定要生成的缩略图存储字段名和规格的map集合。例如 map 的key = imagePath_200, value = 200_200 
	 */
	public static void filterImagePath_spec(Map<String, Object> goodsMap ,String baseImagePath, Map<String,String> imgPath_spec){
		if(goodsMap == null || goodsMap.size() == 0){
			return;
		}
		
		String img_path = (String)goodsMap.get(baseImagePath);
		goodsMap.put(baseImagePath, filterImagePath(img_path));
		for(String key : imgPath_spec.keySet()){
			goodsMap.put(key, filterImagePath(img_path , imgPath_spec.get(key)));
		}
	}
	
	/**
	 * 根据大小处理List集合中图片访问路径
	 * @param mapList 数据集合
	 * @param imgPathKey 图片属性字段名称
	 * @param w_h 图片大小(例如： 200_200 或者800_800)
	 */
	public static void filterImagePath_spec(List<Map<String, Object>> mapList, String imgPathKey, String w_h){
		if(mapList == null || mapList.size() == 0){
			return;
		}
		
		for(Map<String, Object> goodsMap : mapList){
			if(goodsMap == null || goodsMap.isEmpty()){
				continue;
			}
			
			String img_path = (String)goodsMap.get(imgPathKey);
			goodsMap.put(imgPathKey, filterImagePath(img_path , w_h));
		}
	}
	
	/**
	 * 根据大小处理Map集合中图片访问路径 (图片属性字段=img_path)
	 * @param w_h 图片大小(例如： 200_200 或者800_800)
	 */
	public static void filterImagePath_spec(Map<String, Object> map ,String w_h){
		filterImagePath_spec(map,DEFAULT_IMG_PATH_KEY,w_h);
	}
	
	/**
	 * 根据大小处理Map集合中图片访问路径 
	 * @param imgPathKey 图片属性字段名称
	 * @param w_h 图片大小(例如： 200_200 或者800_800)
	 */
	public static void filterImagePath_spec(Map<String, Object> map,String imgPathKey ,String w_h){
		String img_path = (String)map.get(imgPathKey);
		map.put(imgPathKey, filterImagePath(img_path , w_h));
	}
	/**
	 * 获取单个图片缩略图的访问路径
	 * @param imgPath 图片路径
	 * @param w 图片宽(例如： 200)
	 * @param h 图片高(例如： 200)
	 */
	public static String filterImagePath_spec(String imgPath ,int width , int height){
		return filterImagePath(imgPath, width+"_"+height);
	}
}
