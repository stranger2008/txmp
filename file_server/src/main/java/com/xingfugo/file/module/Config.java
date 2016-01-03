package com.xingfugo.file.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 配置文件中root节点内容的映射对象 
 */
@XStreamAlias("root")
public class Config {

	@XStreamAlias("imageServer")
	private ImageServer imageServer;
	
	@XStreamAlias("fileServer")
	private FileServer fileServer;
	
	@XStreamAlias("serverPath")
	private ServerPath serverPath;
	
	public ImageServer getImageServer() {
		return imageServer;
	}

	public void setImageServer(ImageServer imageServer) {
		this.imageServer = imageServer;
	}

	public FileServer getFileServer() {
		return fileServer;
	}

	public void setFileServer(FileServer fileServer) {
		this.fileServer = fileServer;
	}

	public ServerPath getServerPath() {
		return serverPath;
	}

	public void setServerPath(ServerPath serverPath) {
		this.serverPath = serverPath;
	}

}
