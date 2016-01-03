package com.xingfugo.web.admin.chinapay;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ChinapayConfig {

	private static ChinapayConfig chinapayConfig = new ChinapayConfig();
	
	private Properties properties = null;
	
	private ChinapayConfig() {
		init();
	}
	
	/**
	 * 获取实例
	 * @return
	 */
	public static ChinapayConfig getInstance() {
		return chinapayConfig;
	}
	
	/**
	 * 初始化银联支付配置
	 */
	public void init() {
		try {
			InputStream is = ChinapayConfig.class.getResourceAsStream("/com/xingfugo/web/admin/chinapay/chinapay.properties");
			properties = new Properties();
			properties.load(is);
		} catch (IOException e) {
			throw new RuntimeException("加载银联支付配置文件失败");
		}
	}
	
	/**
	 * 取得key相应的值
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		return properties.getProperty(key);
	}
	
	/**
	 * 获取配置
	 * @return
	 */
	public Properties getProperties() {
		return properties;
	}
}
