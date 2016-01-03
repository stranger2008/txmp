package com.xingfugo.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.module.App_android_version;
import com.xingfugo.business.service.App_android_versionService;

//获取app应用
@Controller
public class AppController extends BaseController{
	
	@Autowired
	private App_android_versionService appAndroidVersionService;
	
	//默认下载天下名品包
	private static final String PAGEAGE = "cn.com.cfcm.tianxiamingpin";
	
	//进入app下载页面
	@RequestMapping(value="getapp",method=RequestMethod.GET)
	public String areaselect(){
		return "getapp";
	}
	
	@RequestMapping(value="downloadapp", method = RequestMethod.GET)
	public String getMostRecentRelease(String pack){
		if(StringUtils.isBlank(pack)){
			pack = PAGEAGE;
		}
		App_android_version appAndroidVersion = appAndroidVersionService.selectMostRecentRelease(pack);
		String d_url = appAndroidVersion.getDownload_url();
		if(StringUtils.isBlank(d_url)){
			return "404";
		}
		return "redirect:"+d_url;
	}
	
	//下载app apk文件
	/*
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
    */
}
