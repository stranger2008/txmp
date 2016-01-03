package com.xingfugo.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用JavaSocket发送TCP请求
 * author  wangyilong
 */
public class TcpUtils {
	
	//远程主机地址
	private static String ip ;
	// 远程主机端口
	private static String port;
	// 待发送报文的中文字符串形式
	private static String reqData;
	//该方法与远程主机间通信报文的编码字符集(编码为byte[]发送到Server)
	private static String reqCharset;
	
	@SuppressWarnings("static-access")
	public TcpUtils(String ip,String port,String reqData,String reqCharset) {
		this.ip=ip;
		this.port =port;
		this.reqData=reqData;
		this.reqCharset =reqCharset;
	}
	/**
	 * 发送TCP请求
	 * @see 本方法默认的连接超时和读取超时均为30秒
	 * @see 编码与解码请求响应字节时,均采用双方约定的字符集,即本方法的第四个参数reqCharset
	 * @throws java.io.IOException
	 */
	public static String sendTCPRequest()
		{
			@SuppressWarnings("unused")
			Map<String, String> respMap = new HashMap<String, String>();
			OutputStream out = null; //写
			InputStream in = null; //读
			String respData = null; // 响应报文
			Socket socket = new Socket(); // 客户机
			try {
				socket.setKeepAlive(true);  
				socket.setSoTimeout(30000);
				socket.connect(new InetSocketAddress(ip, Integer.parseInt(port)),30000);
				/**
				 * 发送HTTP请求
				 */
				out = socket.getOutputStream();
				endLength(socket,out,reqData.length());
				out.write(reqData.getBytes(reqCharset));
				out.flush();
				/**
				 * 接收HTTP响应
				 */
				in = socket.getInputStream();
				ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
				byte[] buffer = new byte[512];
				int len = -1;
				while ((len = in.read(buffer)) != -1) {
					bytesOut.write(buffer, 0, len);
				}
				/**
				 * 解码HTTP响应的完整报文
				 */
				respData = bytesOut.toString(reqCharset);
			} catch (Exception e) {
				 e.printStackTrace();
			} finally {
				if (null != socket && socket.isConnected() && !socket.isClosed()) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return respData;
	}
	
	/**
	 *	与服务器建立socket连接后，要先以网络字节序发发送request字符串的长度，此长度以4字节发送。 
	 */
	public static  void endLength(Socket socket,OutputStream out, Integer lenth){
			  
			try {
				byte []receive=new byte[4];  
				byte[] tempByte = getBytes(lenth);
				System.arraycopy(tempByte,0,receive,0,tempByte.length);
				out.write(receive);
			 }catch (IOException e) {
				e.printStackTrace();
			}
	}
	/**
	 *	socket通信是以字节流或者字节包进行的，socket发送方须将数据转换为字节流或者字节包，
	 *而接收方则将字节流和字节包再转换回相应的数据类型。如果发送方和接收方都是同种语言，则一般只涉及到字节序的调整。
	 *而对于java和c/c++的通信，则情况就要复杂一些，主要是因为java中没有unsigned类型，并且java和c在某些数据类型上的长度不一致。
	 */
	public static byte[] getBytes(int x)
	{
		return new byte[] {
		(byte)(x >>> 24),
		(byte)(x >>> 16),
		(byte)(x >>> 8),
		(byte)x
		};
	}
}