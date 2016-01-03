package com.xingfugo.file.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.xingfugo.file.module.FileServer;
import com.xingfugo.file.module.ImageServer;
import com.xingfugo.file.module.ImgSpecConfig;
import com.xingfugo.file.util.FileUtil;


@Service
public class StoreFileService {
	private static Logger LOG = LoggerFactory.getLogger(StoreFileService.class);
	private static final int SIZE_1M = 1024 * 1024;
	private static final int SIZE_1G = 1024 * 1024 * 1024;
	//文件扩展名错误（非法文件）
	private static final String MSG_EXT_ERROR = "file_ext_error";
	//文件大小超过最大限制
	private static final String MSG_EXCEED_MAXSIZE = "file_exceed_max_size";
	//未获取到文件
	private static final String MSG_FILE_EMPTY = "file_empty";
	//文件读取失败
	private static final String MSG_READ_FILE_FAILED = "file_read_failed";
	//文件上传失败
	private static final String MSG_UPLOAD_FAILED = "upload_failed";
	//创建缩放图失败
	private static final String MSG_SPEC_FAILED = "img_spec_failed";
	@Resource
	private ConfigService configService ;
	
	/**
	 * 获取存储文件的相对路径（相对于项目的路径）
	 * @param fileType 文件类型（文件或图片）
	 * @param moduleNm 是保存到的文件夹
	 */
	public String getAbsolutePath(String fileType,String proNm, String moduleNm){
		String filepath = configService.getUploadFilePath();
		if(!filepath.endsWith("/")){
			filepath = filepath + "/" ;
		}
		if(proNm.startsWith("/")){
			proNm = proNm.substring(1);
		}
		if(proNm.endsWith("/")){
			proNm = proNm.substring(0,proNm.lastIndexOf("/"));
		}
		filepath += proNm ;
		if(FileUtil.FILETYPE.equals(fileType)){
			//文件服务配置文件夹路径
			filepath += configService.getFileConfig().getFilepath();
		}else if(FileUtil.IMAGETYPE.equals(fileType)){
			filepath += configService.getImageConfig().getFilepath();
		}
		 
		if(StringUtils.isEmpty(moduleNm)){
			if(!filepath.endsWith("/")){
				filepath = filepath +"/";
			}
		}else{
			if(filepath.endsWith("/")){
				filepath = filepath + moduleNm +"/";
			}else{
				filepath = filepath + "/" + moduleNm+"/";
			}
		}
		Calendar rightNow = Calendar.getInstance();
		//年
		int y = rightNow.get(Calendar.YEAR) ;
		//月
		int m = rightNow.get(Calendar.MONTH )+1;
		//日
		int d = rightNow.get(Calendar.DATE);
		filepath = filepath+y+"/"+m+"/"+d+"/" ;
		return filepath;
	}
	/**
	 * 生成默认原图片（当图片宽高大于默认配置是，创建缩略图片为默认原图）
	 * @param updFile
	 * @param targetFile
	 */
	public boolean createDefualtImg(MultipartFile updFile ,File targetFile ,List<String> errorMsg ){
		if(updFile==null){
			LOG.error("未获取到上传图片:{}", updFile.getName());
			errorMsg.add(MSG_FILE_EMPTY);
			return false;
		}
		boolean ok = true ; // 生成图片成功标志；
		try {
			Image image = ImageIO.read(updFile.getInputStream());
			if(image == null){
				LOG.error("读取图片文件失败：{}", updFile.getName());
				errorMsg.add(MSG_READ_FILE_FAILED);
				return false;
			}
			
			if(image.getWidth(null) == -1){
				LOG.error("图片格式未知：{}", updFile.getName());
				errorMsg.add(MSG_READ_FILE_FAILED);
				return false;
			}
			int imageWidth = image.getWidth(null);
			int imageHeight = image.getHeight(null);
			List<ImgSpecConfig> sysimgconfList = configService.getImageConfig().getAutoConf().getSysSpecList();
			ImgSpecConfig specConf = null;
			if(sysimgconfList!=null&&sysimgconfList.size()>0){
				specConf = sysimgconfList.get(0);
			}
			boolean saveDef = specConf==null || (specConf.getWidth()>imageWidth && specConf.getHeigth()> imageHeight) ;
			if(saveDef){
				updFile.transferTo(targetFile);
			}else{
				//创建图片规格对象
				ImageSpec imageSpec = new ImageSpec(imageWidth, imageHeight);
				int[] size = imageSpec.computeSpecSize(specConf);
				int specWidth = size[0];
				int specHeight = size[1];
				//创建此图像的缩放版本
				ok = createSpecImage( image, targetFile, specWidth, specHeight , errorMsg);
			}
			if(!ok){
				errorMsg.add(MSG_UPLOAD_FAILED);
			}
		} catch (IllegalStateException e) {
			LOG.error("图片文件不存在:{}", updFile.getName());
			ok = false;
			errorMsg.add(MSG_FILE_EMPTY);
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error("图片文件不存在:{}", updFile.getName());
			ok = false;
			errorMsg.add(MSG_FILE_EMPTY);
			e.printStackTrace();
		}
		return ok;
	}
	
	/**
	 * 检查上传文件是否合法（扩展名在配置中存在的为合法文件 ,文件大小是否超过限制）
	 * @param targetFile 要上传的文件
	 * @param fileType  要上传文件的类型
	 * @return true = 文件可以上传； false = 文件不符合条件 不能上传
	 */
	public boolean checkUploadFile(MultipartFile targetFile , String fileType,List<String> msg ){
		long maxSize = 0;
		String permit = null;
		if(FileUtil.FILETYPE.equals(fileType)){
			FileServer fs = configService.getFileConfig();
			//允许上传的文件
			permit = fs.getPermitFile();
			maxSize = fs.getUploadFileMaxSize();
		}else if(FileUtil.IMAGETYPE.equals(fileType)){
			ImageServer is = configService.getImageConfig();
			permit = is.getPermitFile();
			maxSize = is.getUploadFileMaxSize();
		}
		String fileNm = targetFile.getOriginalFilename();
		String ext = FileUtil.getFileExt(fileNm).toLowerCase();
		long flen = targetFile.getSize();
		boolean sizecannot = maxSize!=0 && flen > maxSize ;
		if(sizecannot){
			msg.add(MSG_EXCEED_MAXSIZE);
		}
		boolean extcannot = permit!=null && !(permit.toLowerCase().contains(ext));
		if(extcannot){
			msg.add(MSG_EXT_ERROR);
		}
		return !(sizecannot||extcannot);
	}
	
	
	/**
	 * 当上传文件超过最大尺寸时，生成容易理解的描述
	 * @return
	 */
	public String getMaxUploadFileSizeDesc(long maxSize ){
		//1K以内
		if(maxSize <= 1024){
			return String.valueOf(maxSize) + "字节"; 
		}
		
		//1M以内
		if(maxSize <= SIZE_1M){
			return String.valueOf(maxSize/1024) + "K"; 
		}
		
		//1G以内
		if(maxSize <= SIZE_1G){
			return String.valueOf(maxSize/SIZE_1M) + "M"; 
		}
		
		//超过1G
		return String.valueOf(maxSize/SIZE_1G) + "G"; 
	}
	
	/**
	 * 生成指定规格的图片
	 * @param imgConfig 应用系统配置信息
	 * @param moduleName 应用客户端提交模块名称
	 * @param imageFileFullPath 原始图片路径
	 * @throws java.io.IOException
	 */
	public boolean createSpecImagesByConfig(ImageServer imgConfig, String moduleName,
			String imageFileFullPath ,List<String> msg) throws IOException {
		File imageFile = new File(imageFileFullPath);
		if(!imageFile.exists()){
			LOG.warn("图片文件不存在:{}", imageFileFullPath);
			msg.add(MSG_FILE_EMPTY);
			return false;
		}
		
		Image image = ImageIO.read(imageFile);
		if(image == null){
			LOG.error("读取图片文件失败：{}", imageFileFullPath);
			msg.add(MSG_READ_FILE_FAILED);
			return false;
		}
		
		if(image.getWidth(null) == -1){
			LOG.error("图片格式未知：{}", imageFileFullPath);
			msg.add(MSG_READ_FILE_FAILED);
			return false;
		}
		
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		//创建图片规格对象
		ImageSpec imageSpec = new ImageSpec(imageWidth, imageHeight);
		
		List<ImgSpecConfig> imgSpecList = imgConfig.getAutoImageSpect(moduleName);
		
		boolean ok = true;
		if(imgSpecList!=null){
			int specWidth, specHeight = 0;
			for(ImgSpecConfig imgSpecConf : imgSpecList){
				int[] size = imageSpec.computeSpecSize(imgSpecConf);
				specWidth = size[0];
				specHeight = size[1];
				
				//原图片文件名和路径
				String fileName = imageFile.getName();
				String filePath = imageFile.getParent();
				//要生成的图片文件名
				String targetFileNm = createSpecFileName(imgSpecConf, fileName);
				File targetFile = new File(filePath,targetFileNm);
				//创建此图像的缩放版本
				ok = createSpecImage( image ,  targetFile ,  specWidth,   specHeight ,msg);
			}
		}
		return ok;
	}
	
	/**
	 * 创建缩略图
	 * @param image_source  图片对象
	 * @param targetFile 要创建的图片文件
	 * @param width 要生成的图片宽度
	 * @param height 要生成的图片高度
	 */
	private boolean createSpecImage(final Image image_source , File targetFile ,final int width, final int height ,List<String> errorMsg ) {
		//创建此图像的缩放版本
		Image scaledImage = image_source.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		OutputStream specFileStream = null;
		try {
			specFileStream = new FileOutputStream(targetFile);
		} catch (FileNotFoundException e) {
			LOG.error("图片文件不存在:", e);
			errorMsg.add(MSG_SPEC_FAILED);
			return false;
		}
		
		BufferedImage bufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		bufImg.getGraphics().drawImage(scaledImage, 0, 0, null);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(specFileStream);
		try {
			encoder.encode(bufImg);
		} catch (ImageFormatException e) {
			LOG.error("自动图片失败", e);
			errorMsg.add(MSG_SPEC_FAILED);
			return false;
		} catch (IOException e) {
			LOG.error("自动图片失败", e);
			errorMsg.add(MSG_SPEC_FAILED);
			return false;
		} finally {
			try {
				specFileStream.close();
			} catch (IOException e) {
			}
		}
		
		LOG.debug("自动图片处理完成:{}", targetFile.getAbsolutePath());
		return true;
	}
	
	 
	/**
	 * 生成文件路径
	 * @param imgSpecConf 图片宽高配置
	 * @param imageFileName 文件名
	 * @param imageFilePath 文件路径
	 * @return
	 */
	public String createSpecFileFullPath(ImgSpecConfig imgSpecConf, String imageFileName, String imageFilePath){
		StringBuffer buf = new StringBuffer(imageFilePath);
		if(!imageFilePath.endsWith(File.separator)){
			buf.append(File.separator);
		}
		buf.append(createSpecFileName( imgSpecConf, imageFileName));
		return buf.toString();
	}
	/**
	 * 生成缩放图的文件名
	 * @return
	 */
	private String createSpecFileName(ImgSpecConfig imgSpecConf, String imageFileName){
		int lastIndex = imageFileName.lastIndexOf(".");
		String fileName = imageFileName.substring(0, lastIndex);
		String extName = imageFileName.substring(lastIndex);
		StringBuffer buf = new StringBuffer();
		buf.append(fileName).append("_").append(imgSpecConf.getWidth()).append("_").append(imgSpecConf.getHeigth()).append(extName);
		return buf.toString();
	}
	
}
