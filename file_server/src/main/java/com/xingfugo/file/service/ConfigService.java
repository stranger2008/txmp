package com.xingfugo.file.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.xingfugo.file.module.Config;
import com.xingfugo.file.module.FileServer;
import com.xingfugo.file.module.ImageServer;
import com.xingfugo.file.util.FileUtil;


@Service
public class ConfigService {
	private Config config;
	
	@PostConstruct
	private void buildUploadConfig(){
		 XStream xstream = new XStream(new StaxDriver());
		 xstream.processAnnotations(Config.class);
		 config = (Config)xstream.fromXML(this.getClass().getResourceAsStream("/config.xml"));
	}
	
	
	/**
	 * 获取与文件上传相关的配置信息
	 * @return
	 */
	public FileServer getFileConfig(){
		return config.getFileServer();
	}
	
	/**
	 * 获取与图片上传相关的配置信息
	 * @return
	 */
	public ImageServer getImageConfig(){
		return config.getImageServer();
	}
	/**
	 * 获取与图片上传相关的配置信息
	 * @return
	 */
	public String getServerPath(){
		return config.getServerPath().getName();
	}
	/**
	 * 获取与图片上传到服务器的文件路径
	 * @return
	 */
	public String getUploadFilePath(){
		return config.getServerPath().getValue();
	}
	
	/**
	 * 获取图片上传的绝对访问路径
	 * @return
	 */
	public String getAbsServerPath(String absPath){
		String resultPath = null;
		String domainNm = config.getServerPath().getName();
		if(!domainNm.endsWith("/")){
			domainNm += "/";
		}
		String pathNm = config.getServerPath().getValue();
		if(!pathNm.endsWith("/")){
			pathNm += "/";
		}
		if(absPath.contains(pathNm)){
			resultPath = absPath.replace(pathNm, domainNm);
			resultPath = resultPath.endsWith("/") ? resultPath : (resultPath+"/");
		}
		return resultPath;
	}
	
	/**
	 * 获取图片上传的相对访问路径
	 * @return
	 */
	public String getRelServerPath(String fileType,String absPath,String projectNm){
		String resultPath = null;
		String filepath = getUploadFilePath();
		if(!filepath.endsWith("/")){
			filepath = filepath + "/" ;
		}
		if(projectNm.startsWith("/")){
			projectNm = projectNm.substring(1);
		}
		if(projectNm.endsWith("/")){
			projectNm = projectNm.substring(0,projectNm.lastIndexOf("/"));
		}
		filepath += projectNm ; 
		if(FileUtil.FILETYPE.equals(fileType)){
			//文件服务配置文件夹路径
			filepath += getFileConfig().getFilepath();
		}else if(FileUtil.IMAGETYPE.equals(fileType)){
			filepath += getImageConfig().getFilepath();
		}
		
		if(absPath.contains(filepath)){
			resultPath = absPath.substring(filepath.length());
		}
		return resultPath;
	}
}
