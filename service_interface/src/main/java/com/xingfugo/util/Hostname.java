package com.xingfugo.util;

import java.io.IOException;

public class Hostname {
	
	private static String hostname;
	
	public static String getHostname(){
		if(hostname!=null){
			return hostname;
		}
		PropertiesUtil pu = new PropertiesUtil("host.properties");
		try {
			hostname = pu.readValue("hostname");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hostname;
	}
	
	public static void main(String args[]){
		System.out.println(Hostname.getHostname());
	}
}
