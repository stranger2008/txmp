package com.xingfugo.business.outapi.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088511713990002";
	// 商户的私钥
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKQI4Er9xYsYwFXRQu31PxPBmqmXw1PU8Psg8OR/8o+DzsqE4a6BP+MVEdrxq7fFPcH58iDVQ03B/JLlk8uVHq51aghvRx9MbYSndLF3T6Mpt0Gv46wOOz3jg0DmKkItRctFMS4wkxLVpdar28so5zOn/p+Za++2OVsT8Cru/zhZAgMBAAECgYBa8lDpQypzpJV+/zcMmB0Q5xbJUMuLf1Tc57an+giSaZIN1v8Jsb+qN9Ref/+lNEa5dcs5SQS36AYRrBf1mAgZ7qN04itahnJb0DrMcsgJqnZLMFfNC1HpDzPm6AOWTvKDRmwRhpjRnaTsEE15Wmrtzt7scLHz61dWEdOKrviUwQJBANbCTQ8KT/vIUd6HafAGw23hSzLGAOvldIwThmBZEf9qo+TDACgu/whI/e7czkx3f+xEZxz0subYXdTwMyx3X+8CQQDDiO7aF6I2Shmg+bPf99lnSrTyx8o5c4PlCpIVPjSrmIRs1bf9fxLa1/zGD0PSmVpF1uLRPy4sv8gWdrFJ5CQ3AkAb1YcDQgTnzyi9PLPARkAiB6clZ4BLEqwuU4GBD0pD3mBrj9J/YsfvHPa8dKXQR1SvHJZ4rEZboQNYS+F2C+rFAkEAsubEcrKQ7c8aZ9hPJSoltYX6zO7TLYFtOdBUHC+QDTg8YT+hWcQ8QhK/6Y7p5PP+TkvvtrdxzeuibDsotoyQPQJAXNJOcugEeKlOLhPeQrd4bpflVE3wYSffeYJGnn5p0Wv7ellYfdfJNeRrLn168+R+CwSxwPjiUasHRBNri7oEaA==";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
