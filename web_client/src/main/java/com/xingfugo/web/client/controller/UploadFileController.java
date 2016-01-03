package com.xingfugo.web.client.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.util.PropertiesUtil;

@Controller
public class UploadFileController extends BaseController {

	// 存储图片的临时目录名
	private static final String UPLOADS_PATH = "temp";
	//文件上传访问action
	private static final String UPLOAD_FILE_ACTION = "storeFile.action";
	//图片上传访问action
	private static final String UPLOAD_IMG_ACTION = "storeImage.action";
	//访问文件上传服务路径配置文件名
	private static final String CONFIG_FILE_NAME = "config.properties";
	//访问文件上传服务路径配置的key
	private static final String CONF_FILE_URL_KEY = "uploadfile.url";

	/**
	 * 测试上传图片的访问页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "upTest")
	public String index(ModelMap model) {
		return "advpos/uploadPic";
	}

	/**
	 * 图片上传
	 * 
	 * @param request
	 * @param response
	 * @param out
	 * @return
	 */
	@RequestMapping(value = "upload{fileType}_{moduleName}", method = RequestMethod.POST)
	@ResponseBody
	public String upload(HttpServletRequest request,
			HttpServletResponse response,@PathVariable(value="fileType") String fileType,@PathVariable(value="moduleName") String moduleName) {
	 
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 
		// 获取文件的存放文件位置
		String strPath = getImageSavePath();
		// 存储文件名称

		String result = null;

		String fileName = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile multipartFile = entity.getValue();// 文件名
			fileName = multipartFile.getOriginalFilename();
			// 得到上传文件的扩展名
			String ftype = getFileExt(fileName);
			// 获得当前的系统时间（年月日时分钟秒）来作为文件名
			String newfilename = createFileNameByDate(ftype);
			// 创建文件
			File targetFile = new File(strPath, newfilename);
			try {
				if (!targetFile.isFile()) {
					targetFile.createNewFile();
				}
				multipartFile.transferTo(targetFile);

				result = uploadFileToServer(targetFile,moduleName,fileType);
				//删除临时上传文件
				deleteFile(new File(strPath));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 删除 filepath路径下的文件
	 * @param filepath
	 */
	private void deleteFile(File file){
		if(file.exists()){
			if(file.isFile()){
				file.delete();
			}else{
				File[] child_files = file.listFiles();
				if(child_files.length>0){
					for(File temp : child_files){
						deleteFile(temp);
					}
				}
			}
			
		}
	}
	/**
	 * 获取图片存放的服务器地址
	 * 
	 * @return 临时存放路径
	 */
	private String getImageSavePath() {
		// 获取文件的存放文件位置
		String strPath = getRequest().getSession().getServletContext()
				.getRealPath("/" + UPLOADS_PATH)
				+ File.separator;

		File path = new File(strPath);
		if (!path.exists()) {
			path.mkdirs();
		}
		return strPath;
	}

	/**
	 * 生成随机字符串来作为文件名 ftype：文件扩展名
	 */
	private String createFileNameByDate(String ftype) {
		String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");// 返回一个随机UUID。
		String filename = uuid + "." + ftype;

		return filename;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	private String getFileExt(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return "";
	}

	/**
	 * 传送文件到文件服务器
	 * 
	 * @param upfile
	 *            要上传的文件
	 * @param folderNm 上传到文件服务器的文件夹           
	 * @return 文件上传服务器地址
	 */
	private String uploadFileToServer(File upfile,String folderNm , String fileType) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String resultPath = null;
		try {
			String visitPath = getUploadUrl();
			String url = visitPath + UPLOAD_IMG_ACTION;
			if(fileType!=null&&fileType.equals("Imgs")){
				url = visitPath + UPLOAD_IMG_ACTION;
			}else if(fileType!=null&&fileType.equals("Files")){
				url = visitPath + UPLOAD_FILE_ACTION;
			}
			// 把文件上传给下面这个地址
			HttpPost httppost = new HttpPost(url);

			// 把文件转换成流对象FileBody
			FileBody bin = new FileBody(upfile);
			if(folderNm==null){
				HttpEntity reqEntity = MultipartEntityBuilder.create().addPart(
						"bin", bin).build();
				httppost.setEntity(reqEntity);
			}else{
				StringBody module_name = new StringBody(folderNm, ContentType.create("text/plain", Consts.UTF_8));  
				
				StringBody pro_name = new StringBody(ImgPathUitls.PROJECT_NAME, ContentType.create("text/plain", Consts.UTF_8));  
				
				HttpEntity reqEntity = MultipartEntityBuilder.create().addPart(
						"bin", bin).addPart("moduleName", module_name).addPart("projectName",pro_name).build();
				httppost.setEntity(reqEntity);
			}

			// 发起请求 并返回请求的响应
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				// 打印响应状态
				//System.out.println(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					// 打印响应内容
					resultPath = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
//					System.out.println(resultPath);
				}
				//销毁   
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resultPath;
	}
	//获取上传图片服务器访问地址
	private String getUploadUrl(){
		PropertiesUtil config_pro = new PropertiesUtil(CONFIG_FILE_NAME);
		try {
			String visitPath = config_pro.readValue(CONF_FILE_URL_KEY);
			if(!visitPath.endsWith("/")){
				visitPath = visitPath+"/";
			}
			return visitPath ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 加载图片访问路径（服务器路径）
	 */
	@RequestMapping("visitImgPath")
	@ResponseBody
	public String ImgBasePath(){
		String visitBasePath =  ImgPathUitls.getLoadImgUrl();
		String result = "{imgBasePath:'"+visitBasePath+"'}";
		return result;
	}
	
	/**
	 * 加载文件访问路径（服务器路径）
	 */
	@RequestMapping("visitFilePath")
	@ResponseBody
	public String FileBasePath(){
		String visitBasePath =  ImgPathUitls.getLoadFileUrl();
		String result = "{fileBasePath:'"+visitBasePath+"'}";
		return result;
	}
	
	//web文本编辑器默认上上传图片到服务器的存储文件夹
	private static final String CK_MODULE_TYPE = "client/ckeditor";
	
	/**
	 * web文本编辑器上传图片 
	 * @param request
	 * @param response
	 * @param out
	 * @return
	 */
	@RequestMapping(value = "ckupload")
	@ResponseBody
	public String ckupload(HttpServletRequest request, HttpServletResponse response) {
		String moduleName = CK_MODULE_TYPE;
		String fileType = "Imgs";
		
		String ckEditorFuncNum = request.getParameter("CKEditorFuncNum");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 
		// 获取文件的存放文件位置
		String strPath = getImageSavePath();
		// 存储文件名称

		String result = null;

		String fileName = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile multipartFile = entity.getValue();// 文件名
			fileName = multipartFile.getOriginalFilename();
			// 得到上传文件的扩展名
			String ftype = getFileExt(fileName);
			// 获得当前的系统时间（年月日时分钟秒）来作为文件名
			String newfilename = createFileNameByDate(ftype);
			// 创建文件
			File targetFile = new File(strPath, newfilename);
			try {
				if (!targetFile.isFile()) {
					targetFile.createNewFile();
				}
				multipartFile.transferTo(targetFile);

				result = uploadFileToServer(targetFile,moduleName,fileType);
				
				//删除临时上传文件
				deleteFile(new File(strPath));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		PrintWriter out;
		JSONObject res;
		try {
			out = response.getWriter();
			res = new JSONObject(result);
			boolean sucess = res.getBoolean("success");
			String path = res.getString("path");
			if(!path.startsWith("/")){
				path = "/"+path;
			}
			String visitpath = res.getString("visitpath");
			if(visitpath.endsWith("/")){
				visitpath= visitpath.substring(0,visitpath.lastIndexOf("/"));
			}
			String imgpath = visitpath+path;
			if(sucess){
				result = getScript(ckEditorFuncNum,"",imgpath);
			}else{
				result = getScript(ckEditorFuncNum,res.getString("msg"),imgpath);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//返回ckeditor js脚本
	public String getScript(String ckEditorFuncNum,String verify_message,String file_url){
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		if(!verify_message.equals("")){
			sb.append("alert('"+verify_message+"');");
		}
		String CKEditorFuncNum = ckEditorFuncNum;
		sb.append("window.parent.CKEDITOR.tools.callFunction('"+ CKEditorFuncNum+"','"+file_url+"','');");
		sb.append("</script>");
		return sb.toString();
	}
}
