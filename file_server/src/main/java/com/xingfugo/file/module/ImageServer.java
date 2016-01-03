package com.xingfugo.file.module;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("imageServer")
public class ImageServer {
	@XStreamAlias("filepath")
	private String filepath=null;
	
	@XStreamAlias("maxSize")
	private Long uploadFileMaxSize = null;
	
	@XStreamAlias("permit")
	private String permitFile = null;
	 
	
	@XStreamAlias("auto")
	private ImgAutoConfig autoConf = null;


	public String getFilepath() {
		return filepath;
	}


	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


	public Long getUploadFileMaxSize() {
		return uploadFileMaxSize;
	}


	public void setUploadFileMaxSize(Long uploadFileMaxSize) {
		this.uploadFileMaxSize = uploadFileMaxSize;
	}


	public String getPermitFile() {
		return permitFile;
	}


	public void setPermitFile(String permitFile) {
		this.permitFile = permitFile;
	}


	public ImgAutoConfig getAutoConf() {
		return autoConf;
	}


	public void setAutoConf(ImgAutoConfig autoConf) {
		this.autoConf = autoConf;
	}
	 
	public List<ImgSpecConfig> getAutoImageSpect(String moduleName){
		if(StringUtils.isEmpty(moduleName)){
			return null;
		}
		
		List<ImgModuleConfig> moduleConfList = autoConf.getModuleSpecList();
		if(moduleConfList == null){
			return null;
		}
		
		for(ImgModuleConfig moduleConf : moduleConfList){
			if(moduleName.equals(moduleConf.getName())){
				//返回模块级配置
				return moduleConf.getSpecList();
			}
		}
		return null;
	}
}
