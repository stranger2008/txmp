package com.xingfugo.file.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.xingfugo.file.module.Config;
import com.xingfugo.file.module.ImageServer;
import com.xingfugo.file.module.ImgSpecConfig;
import com.xingfugo.file.service.ConfigService;
import com.xingfugo.file.service.ImageSpec;

public class CreateImgUtil {
	private Config config;
	private ImageServer imageServer;
	public CreateImgUtil(){
		 XStream xstream = new XStream(new StaxDriver());
		 xstream.processAnnotations(Config.class);
		 config = (Config)xstream.fromXML(this.getClass().getResourceAsStream("/config.xml"));
		 imageServer = config.getImageServer();
	}
	
	public CreateImgUtil(ConfigService configService){ 
		 imageServer = configService.getImageConfig();
	}
	/**
	 * 生成图片
	 * @param moduleName
	 */
	public void creatImg(String moduleName ){
		if(moduleName == null){
			moduleName="goods" ;
		}
		
		List<ImgSpecConfig> imgSpecList = imageServer.getAutoImageSpect(moduleName); 
		if(imgSpecList!=null){
			createNeedCreateFile(imgSpecList ,moduleName,null,false);
		}
	}
	
	/**
	 * 生成图片
	 * @param moduleName
	 */
	public void creatImg(String moduleName ,String relativePath ){
		if(moduleName == null){
			moduleName="goods" ;
		}
		
		List<ImgSpecConfig> imgSpecList = imageServer.getAutoImageSpect(moduleName); 
		if(imgSpecList!=null){
			createNeedCreateFile(imgSpecList ,moduleName,relativePath,false);
		}
	}
	
	/**
	 * 生成图片
	 * @param moduleName
	 */
	public void creatImg(String moduleName ,String relativePath , boolean createAll){
		if(moduleName == null){
			moduleName="goods" ;
		}
		
		List<ImgSpecConfig> imgSpecList = imageServer.getAutoImageSpect(moduleName); 
		if(imgSpecList!=null){
			createNeedCreateFile(imgSpecList ,moduleName,relativePath,createAll);
		}
	}
	
	/**
	 * 读取文件夹下的文件创建需要生成的缩略图
	 * @param imgSpecList 缩略图大小配置
	 * @param moduleName 模块名称， 对应config.xml中的配置的某模块的缩略图宽高配置
	 * @param relativePath 相对路径(要生成缩略图的文件夹路径) (相对路径为空时查询moduleName文件夹一的图片)
	 * @param createAll  是否生成所有配置文件中的缩略图（默认为false，已存在的缩略图不再生成） 
	 */
	public void createNeedCreateFile(List<ImgSpecConfig> imgSpecList , String moduleName , String relativePath , boolean createAll){
		
		String path = imageServer.getFilepath();
		if(relativePath==null){
			if(!path.endsWith("/")){
				path = path +"/"+ moduleName ;
			}else{
				path = path + moduleName ;
			}
		}else{
			if(relativePath.startsWith("/")){
				relativePath = relativePath.substring(1);
			}
			if(!path.endsWith("/")){
				path = path +"/"+ relativePath ;
			}else{
				path = path + relativePath ;
			}
		}
		path = path.replace("/", File.separator);
		
		List<File> allFile = new ArrayList<File>();
		File dir = new File(path);
		if(dir.exists()){
			findImgFile(dir,allFile);
		}
		if(allFile.isEmpty()){
			System.out.println(dir.getAbsolutePath() +" 文件夹下没有文件！");
			return ;
		}
		for(ImgSpecConfig spec : imgSpecList){
			//查看缩略图片是否存在，不存在生成缩略图
			for(File ch_F : allFile){
				String fileNm = ch_F.getName();
				String newspecNm = getSpecFileName(spec,fileNm);
				File specFile = new File(ch_F.getParentFile(),newspecNm);
				if(specFile.exists()&& createAll){
					specFile.delete();
				}
				if(!specFile.exists()){
					try {
						FileInputStream fis = new FileInputStream(ch_F);
						Image image = ImageIO.read(fis);
						int imageWidth = image.getWidth(null);
						int imageHeight = image.getHeight(null);
						//创建图片规格对象
						ImageSpec imageSpec = new ImageSpec(imageWidth, imageHeight);
						int[] size = imageSpec.computeSpecSize(spec);
						int specWidth = size[0];
						int specHeight = size[1];
						createSpecImg(image ,specFile , specWidth ,specHeight);
						System.out.println("targerFileNm: " + specFile.getAbsolutePath());
						fis.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					 
				}
			}
		}
		System.out.println("生成完成 " );
	}
	//生成缩放图的文件名
	private String getSpecFileName(ImgSpecConfig imgSpecConf, String imageFileName){
		int lastIndex = imageFileName.lastIndexOf(".");
		String fileName = imageFileName.substring(0, lastIndex);
		String extName = imageFileName.substring(lastIndex);
		StringBuffer buf = new StringBuffer();
		buf.append(fileName).append("_").append(imgSpecConf.getWidth()).append("_").append(imgSpecConf.getHeigth()).append(extName);
		return buf.toString();
	}
	//创建缩略图
	private boolean createSpecImg(final Image image_source , File targetFile ,final int width, final int height ){
		Image scaledImage = image_source.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		OutputStream specFileStream = null;
		try {
			specFileStream = new FileOutputStream(targetFile);
		} catch (FileNotFoundException e) {
			return false;
		}
		
		BufferedImage bufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		bufImg.getGraphics().drawImage(scaledImage, 0, 0, null);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(specFileStream);
		try {
			encoder.encode(bufImg);
		} catch (ImageFormatException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				specFileStream.close();
			} catch (IOException e) {
			}
		}
		
		return true;
	}
	/**
	 * 查找文件夹的所有原图片文件
	 * @param file
	 * @param files
	 */
	private void findImgFile(File file , List<File> files){
		if(file.exists()&& ! file.isFile()){
			File[] childs = file.listFiles();
			for(File child :childs){
				if(!child.isFile()){
					findImgFile( child , files );
				}else{
					String fileName = child.getName();
					if(fileName.indexOf("_")==-1){
						files.add(child);
//						System.out.println("FileNm : "+child.getAbsolutePath());
					}
				}
			}
			
		}
	}
	
	public void creatImg(){
		creatImg(null);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CreateImgUtil createImgUtil = new  CreateImgUtil();
		createImgUtil.creatImg();
	}

}
