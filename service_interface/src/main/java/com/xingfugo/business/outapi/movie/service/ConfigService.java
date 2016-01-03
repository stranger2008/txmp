package com.xingfugo.business.outapi.movie.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.xingfugo.business.outapi.movie.module.Config;

@Service
public class ConfigService {
	private Config config;
	
	@PostConstruct
	private void buildUploadConfig(){
		 XStream xstream = new XStream(new StaxDriver());
		 xstream.processAnnotations(Config.class);
		 config = (Config)xstream.fromXML(this.getClass().getResourceAsStream("/movieConf.xml"));
	}
	
	public String getServerRootURL() {
		return config.getServerRootURL();
	}

	public String getInterfaceURL() {
		return config.getInterfaceURL();
	}

	public String getInterfaceParam() {
		return config.getInterfaceParam();
	}

	public String getSeatSelectURL() {
		return config.getSeatSelectURL();
	}

	public String getAppKey() {
		return config.getAppKey();
	}
	
	public int getMaxSelectedSeat() {
		return config.getMaxSelectedSeat();
	}

	public String getAppCode() {
		return config.getAppCode();
	}
	
}
