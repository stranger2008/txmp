package com.xingfugo.outapi.lottery;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author wyl
 *	启动监听彩票推送的开奖信息
 */
public class ServerSocketListener implements ServletContextListener {

	private LotterySocketThread lt;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		lt.stop();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		String port = arg0.getServletContext().getInitParameter("socketPort");
		arg0.getServletContext().log("---- Socket服务随web启动而启动 ----");
		lt = new LotterySocketThread(Integer.parseInt(port));
		lt.start();
		arg0.getServletContext().log("---- Socket服务已经启动完毕，端口：" + port + "----");

	}

}
