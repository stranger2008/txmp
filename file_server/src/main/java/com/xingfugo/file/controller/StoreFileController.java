package com.xingfugo.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xingfugo.file.service.ConfigService;
import com.xingfugo.file.service.StoreFileService;
import com.xingfugo.file.util.CreateImgUtil;
import com.xingfugo.file.util.FileUtil;
import com.xingfugo.file.util.RandomStrUtil;
import com.xingfugo.file.util.ToSwfTread;

@Controller
public class StoreFileController {

	@Resource
	private ConfigService configService ;
	
	@Resource
	private StoreFileService storeFileService ;
	
	//文件上传失败
	private static final String MSG_UPLOAD_FAILED = "upload_failed";
	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "storeFile")
	@ResponseBody
	public String saveFile(HttpServletRequest request,
			HttpServletResponse response) {
		String result = doSave(request,FileUtil.FILETYPE);
		return result;
	}
	/**
	 * 图片上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "storeImage")
	@ResponseBody
	public String saveImageFile(HttpServletRequest request,
			HttpServletResponse response) {
		String result = doSave(request,FileUtil.IMAGETYPE);
		return result;
	}
	/**
	 * 保存图片
	 * @param request
	 * @param fileType
	 * @return
	 */
	private String doSave(HttpServletRequest request, String fileType){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		//存储错误信息
		List<String > errorMessage = new ArrayList<String>();

		String proNm = request.getParameter("projectName");
		if(StringUtils.isEmpty(proNm)){
			errorMessage.add("projectName_null");
			String resultJson = toJsonStr("success",false,"msg",errorMessage);
			return resultJson;
		}
		String moduleNm = request.getParameter("moduleName");
		//存储文件上传路径
		String resultpath = null;
		//访问服务器的域名路径
		String visitBasePath = null;
		boolean ok = true;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			//获取文件
			MultipartFile multipartFile = entity.getValue();
			// 判断是否允许上传
			boolean canDoSave = storeFileService.checkUploadFile(multipartFile, fileType,errorMessage);
			if(!canDoSave){
				String resultJson = toJsonStr("success",false,"msg",errorMessage);
				return resultJson;
			}
			String fileName = multipartFile.getOriginalFilename();
			
			//获取文件存储路径
			String absPath = storeFileService.getAbsolutePath(fileType,proNm, moduleNm);
			//文件路径
			String filePath = absPath.replace("/", File.separator);
			// 得到新生成文件名称
			String ext = FileUtil.getFileExt(fileName) ;
			String newfilename = RandomStrUtil.getTwentyWordAndNum()+"." + ext;
			// 创建目标文件
			File targetFile = new File(filePath, newfilename);
			try {
				if (!targetFile.getParentFile().exists()) {
					targetFile.getParentFile().mkdirs();
				}
				if(!targetFile.exists()){
					targetFile.createNewFile();
				}
				
				if(fileType.equals(FileUtil.IMAGETYPE)){
					ok = storeFileService.createDefualtImg(multipartFile,targetFile,errorMessage);
					if(!ok){
						String resultJson = toJsonStr("success",false,"msg",errorMessage);
						return resultJson;
					}
					//生成缩略图
					ok = storeFileService.createSpecImagesByConfig(configService.getImageConfig(), moduleNm, targetFile.getAbsolutePath(),errorMessage);
				}else{
					multipartFile.transferTo(targetFile);
					if(ext.equalsIgnoreCase("pdf")){
//						boolean chok = PdfToSwf.pdf2swf(targetFile.getAbsolutePath());
//						if(!chok){
//							errorMessage.add("pdfToSwf_error");
//						}
						System.out.println("111:"+multipartFile.getSize());
						long i = multipartFile.getSize();
						boolean large = (i>=5242880);//(i>=10485760);
						ToSwfTread toswf = new ToSwfTread(targetFile.getAbsolutePath(),large);
						toswf.start();
					}
				}
				//获取服务相对访问路径
				String relativePath = configService.getRelServerPath(fileType,absPath,proNm);
				relativePath = relativePath.replace(File.separator, "/");
				visitBasePath = getVisitBasePath(relativePath,absPath);
				resultpath = relativePath + newfilename;
			} catch (IllegalStateException e) {
				e.printStackTrace();
				ok = false;
				errorMessage.add(MSG_UPLOAD_FAILED);
			} catch (IOException e) {
				e.printStackTrace();
				ok = false;
				errorMessage.add(MSG_UPLOAD_FAILED);
			}
		}
		if(errorMessage.isEmpty()){
			errorMessage = null;
		}
		String resultJson = toJsonStr("success",ok,"path",resultpath,"visitpath",visitBasePath,"msg",errorMessage);
		return resultJson;
	}


	//获取request对象，不用在每个方法上加request参数
	public HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	//获取项目根地址，如http://localhost:8080/xfg_file_server
	public String basePath(){
		HttpServletRequest request = getRequest();
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	}
	/**
	 * 转换json字符串
	 * @param messages
	 * @return
	 */
	public String toJsonStr(Object... messages ){
		StringBuffer bf = new StringBuffer();
		if(messages!=null && messages.length>0){
			bf.append("{");
			for(int i =0 ; i< messages.length ; i=i+2 ){
				Object obj1 = messages[i];
				Object obj2 = messages[i+1];
				if(obj1==null||obj2==null){
					continue;
				}
				if(i>0){
					bf.append(",");
				}
				bf.append("\""+obj1+"\"");
				bf.append(":");
				if(obj2 instanceof String){
					bf.append("\""+obj2+"\"");
				}
				if(obj2 instanceof Boolean){
					bf.append(obj2);
				}
				if(obj2 instanceof List){
					String info = ((List)obj2).toString();
					info = info.substring(info.indexOf("[")+1, info.lastIndexOf("]"));
					bf.append("\""+info+"\"");
				}
			}
			bf.append("}");
		}
		return bf.toString();
	}
	//获取访问服务器的域名路径
	private String getVisitBasePath(String relativePath, String absPath ){
		String absServerPath = configService.getAbsServerPath(absPath);
		System.out.println("absServerPath:"+absServerPath);
		System.out.println("relativePath:"+relativePath);
		System.out.println("absPath:"+absPath);
		String visitBasePath = null;
		int index = absServerPath.indexOf(relativePath);
		if(index!=-1){
			visitBasePath = absServerPath.substring(0,index);
		}
		System.out.println("visitBasePath:"+visitBasePath);
		return visitBasePath;
	}
	
	@RequestMapping(value = "specImg")
	@ResponseBody
	public String createSpecImg(HttpServletRequest request,
			HttpServletResponse response) {
		CreateImgUtil createUtil = new CreateImgUtil(configService);
		//createUtil.creatImg();
		createUtil.creatImg("uploads");
		return "{success:true}";
	}
}
