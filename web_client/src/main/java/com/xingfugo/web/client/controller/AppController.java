package com.xingfugo.web.client.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//获取app应用
@Controller
public class AppController extends BaseController{
	
	//进入app下载页面
	@RequestMapping(value="getapp",method=RequestMethod.GET)
	public String areaselect(){
		return "getapp";
	}
	
	
	//下载app apk文件
	@RequestMapping(value="downloadapp",method=RequestMethod.GET)
	public void downloadApp(HttpServletResponse response) throws FileNotFoundException {
        // 下载本地文件
        String fileName = "xingfugo.apk".toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream("D:\\workspace\\xfg_cp_client\\WebRoot\\inc\\XingFuGo_0623.apk");// 文件的存放路径
        // 设置输出的格式
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
