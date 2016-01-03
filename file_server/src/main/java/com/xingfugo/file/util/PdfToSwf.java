package com.xingfugo.file.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
/**
 * pdf转换成swf 工具类
 */
public class PdfToSwf {
	/**
	 *利用SWFTools工具将pdf转换成swf，转换完后的swf文件与pdf同名
	 *@author iori 
	 *@param fileDir
	 *PDF文件存放路径（包括文件名）
	 *@param exePath 转换器安装路径
	 *@throws java.io.IOException
	 * */
	public static synchronized boolean pdf2swf(String fileDir, String exePath,boolean system)
			throws IOException {
		if(StringUtils.isEmpty(fileDir)){
			return false;
		}
		fileDir = fileDir.replace("/", File.separator);
		exePath = exePath.replace("/", File.separator);
		// 文件路径
		String filepath = fileDir.substring(0,fileDir.lastIndexOf("."));
		// 文件名，不带后缀
		String newFile = filepath+".swf" ;
		Process pro = null;
		if (system) {
			// 如果是windows系统 //命令行命令
//			String cmd = exePath + " \"" + fileDir + "\" -s flashversion=9 -o \"" +newFile+ "\" ";
			String cmd = "cmd /c \"" + exePath + "\" -t " + fileDir + " -s poly2bitmap flashversion=9  -o " + newFile;
			//1.-s languagedir=F:\\xpdf-chinese-simplified"  
			// Runtime执行后返回创建的进程对象
			pro = Runtime.getRuntime().exec(cmd);
		} else {
			
			// 如果是linux系统,路径不能有空格，而且一定不能用双引号，否则无法创建进程
			 String cmd = exePath + " " + fileDir + " -s poly2bitmap flashversion=9 -o " + newFile;
			// Runtime执行后返回创建的进程对象
			pro = Runtime.getRuntime().exec(cmd);
		}
		// 非要读取一遍cmd的输出，要不不会flush生成文件（多线程）
		new DoOutput(pro.getInputStream()).start();
		new DoOutput(pro.getErrorStream()).start();
		try {
			// 调用waitFor方法，是为了阻塞当前进程，直到cmd执行完
			pro.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 *利用SWFTools工具将pdf转换成swf，转换完后的swf文件与pdf同名 (小文件快速转换)
	 *@author iori 
	 *@param fileDir
	 *PDF文件存放路径（包括文件名）
	 *@param exePath 转换器安装路径
	 *@throws java.io.IOException
	 * */
	public static synchronized boolean pdf2swf_fast(String fileDir, String exePath,boolean system)
			throws IOException {
		if(StringUtils.isEmpty(fileDir)){
			return false;
		}
		fileDir = fileDir.replace("/", File.separator);
		exePath = exePath.replace("/", File.separator);
		// 文件路径
		String filepath = fileDir.substring(0,fileDir.lastIndexOf("."));
		// 文件名，不带后缀
		String newFile = filepath+".swf" ;
		Process pro = null;
		if (system) {
			// 如果是windows系统 //命令行命令
//			String cmd = exePath + " \"" + fileDir + "\" -s flashversion=9 -o \"" +newFile+ "\" ";
			String cmd = "cmd /c \"" + exePath + "\" -t " + fileDir + " -s flashversion=9  -o " + newFile;
			//1.-s languagedir=F:\\xpdf-chinese-simplified"  
			// Runtime执行后返回创建的进程对象
			pro = Runtime.getRuntime().exec(cmd);
		} else {
			
			// 如果是linux系统,路径不能有空格，而且一定不能用双引号，否则无法创建进程
			 String cmd = exePath + " " + fileDir + " -s flashversion=9 -o " + newFile;
			// Runtime执行后返回创建的进程对象
			pro = Runtime.getRuntime().exec(cmd);
		}
		// 非要读取一遍cmd的输出，要不不会flush生成文件（多线程）
		new DoOutput(pro.getInputStream()).start();
		new DoOutput(pro.getErrorStream()).start();
		try {
			// 调用waitFor方法，是为了阻塞当前进程，直到cmd执行完
			pro.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 判断是否是windows操作系统
	 * @author iori 
	 * @return
	 * */
	private static boolean isWindowsSystem() {
		String p = System.getProperty("os.name");
		return p.toLowerCase().indexOf("windows") >= 0 ? true : false;
	}

	/**
	 * 
	 * 多线程内部类
	 *  读取转换时cmd进程的标准输出流和错误输出流，这样做是因为如果不读取流，进程将死锁 
	 *  @author iori
	 * */
	private static class DoOutput extends Thread {
		public InputStream is;
		// 构造方法
		public DoOutput(InputStream is) {
			this.is = is;
		}
		public void run() {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					this.is));
			String str = null;
			try {
				// 这里并没有对流的内容进行处理，只是读了一遍
				while ((str = br.readLine()) != null);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static boolean pdf2swf(String filePath){
		return pdf2swf(filePath,true);
	}
	/**
	 * pdf转换成swf文件
	 * @param filePath pdf文件路径
	 */
	public static boolean pdf2swf(String filePath,boolean large){
		// 转换器安装路径
		String exePath ;
		//判断操作系统
		boolean system=PdfToSwf.isWindowsSystem();
		if(system){
			exePath= "D:/Program Files/SWFTools/pdf2swf.exe";
		}else{
			exePath= "/usr/local/bin/pdf2swf";
		}
		try {
			if(large){
				return PdfToSwf.pdf2swf(filePath, exePath,system);
			}else{
				return PdfToSwf.pdf2swf_fast(filePath, exePath, system);
			}
		} catch (IOException e) {
			System.err.println("转换出错！");
			e.printStackTrace();
			return false;
		}
	}
 
	public static void main(String[] args){
		String path = "E:/testpdf/20140826.pdf";
		pdf2swf(path);
	}
}
