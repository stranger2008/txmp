package com.xingfugo.file.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("fileServer")
public class FileServer {
	@XStreamAlias("filepath")
	private String filepath = null;
	
	@XStreamAlias("maxSize")
	private Long uploadFileMaxSize = null;
	
	@XStreamAlias("permit")
	private String permitFile = null;

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
	
	
	
}
