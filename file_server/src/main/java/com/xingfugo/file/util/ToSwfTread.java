package com.xingfugo.file.util;

import org.apache.commons.lang.StringUtils;

public class ToSwfTread extends Thread {
	private String filePath ;
	private boolean large;
	public ToSwfTread() {
	}
	public ToSwfTread(String filePath , boolean large) {
		this.filePath = filePath;
		this.large = large;
	}
	
	public ToSwfTread(String filePath) {
		this.filePath = filePath;
		this.large = true;
	}
	
	@Override
	public void run() {
		//super.run();
		if(StringUtils.isNotEmpty(filePath)){
			PdfToSwf.pdf2swf(filePath,large);
		}
	}
}
