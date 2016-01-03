package com.xingfugo.business.common.codeBuild;

import java.util.ArrayList;
import java.util.HashMap;

import com.xingfugo.util.DbUtil;
import com.xingfugo.util.FileUtil;
import com.xingfugo.util.PropertiesUtil;


/**
 * 功能：生成本框架的基础代码，包括java类，xml文件，前台文件
 * date:2011-07-10
 * @author 李良林
 *
 */
public class CodeEngine {
	
	//数据库表名
	private static final String TABLENAME = "course_video";
	
	//表主键
	private static final String TABLEKEY = "info_id";
	
	//列表显示字段，列表搜索字段
	private String INDEXDISFIELD = "video_name,video_path",SEARCHFIELD = "ideo_name";
	
	//功能名称
	private static final String FUNNAME = "课程视频管理";
	
	//功能名称
	private static final String AUTHOR = "李良林";
	
	//表名第一个字母大写
	private final String CLASSNAME;
	
	//主键第一个字母大写
	private final String TABLEKEYUPPER;
	
	//当前日期
	private final String DATE;
	
	//项目的根目录
	private static String rootpath = PropertiesUtil.getRootpath().replace("target/classes/", "");
	
	//模板路径
	private static String templatePath = Constants.TEMPLATEPATH;
	
	//文件工具类
	private FileUtil fileUtil;
	
	//数据库工具类
	private DbUtil dbOperate;
	
	//替换内容
	private String fileContent;
	
	public CodeEngine(){
		CLASSNAME = oneWordUpper(TABLENAME);
		TABLEKEYUPPER = oneWordUpper(TABLEKEY);
		DATE = getDate();
		fileUtil = new FileUtil();
		dbOperate = new DbUtil();
	}
	
	/*
	 * 主入口方法
	 */
	public static void main(String args[]){
		
		CodeEngine codeEngine = new CodeEngine();
		String className=codeEngine.oneWordUpper(TABLENAME);
		//java类、ibatis sql文件自动生成
		codeEngine.javaCode(codeEngine,className);
		
		//前端生成
		codeEngine.webCode(codeEngine,className);
	}
	
	public void javaCode(CodeEngine codeEngine,String className){
		codeEngine.createFile("daoInterface.txt",className+"Dao.java",Constants.DAO_SAVEPATH);
		codeEngine.createFile("serviceImpl.txt",className+"Service.java",Constants.SERVICEIMPL_SAVEPATH);
		codeEngine.createFile("pojoClass.txt",className+".java",Constants.POJO_SAVEPATH);
		codeEngine.createFile("queryFormClass.txt",className+"QueryForm.java",Constants.QUERYFORM_SAVEPATH);
		codeEngine.createFile("sqlmap.txt",className+"Dao.xml",Constants.SQLMAP_SAVEPATH);
	}
	
	public void webCode(CodeEngine codeEngine,String className){
		codeEngine.createFile("controller.txt",className+"Controller.java",Constants.TEMPLATEPATH+"/"+TABLENAME);
		codeEngine.createFile("view_add.txt","add.jsp",Constants.TEMPLATEPATH+TABLENAME);
		codeEngine.createFile("view_index.txt","index.jsp",Constants.TEMPLATEPATH+"/"+TABLENAME);
		codeEngine.createFile("view_update.txt","update.jsp",Constants.TEMPLATEPATH+TABLENAME);
	}
	
	public void createFoldFile(String creathPath,String templateName,String fileName){
		//模板的详细地址
		String file_path = creathPath+CLASSNAME;
		//创建文件夹
		fileUtil.createFolder(file_path);
		//模板的详细地址
		String template_path = rootpath+templatePath+templateName;
		//得到模板的内容
		String fileCon = fileUtil.readTxt(template_path);
		//替换模板中的标签为具体代码
		fileCon = replaceTemplate(fileCon);
		//创建文件 和 写入文件内容
		fileUtil.writeTxt(file_path,fileName,fileCon);
		System.out.println(file_path+"/"+fileName+"文件生成成功");
	}
	
	
	
	public void createSqlMapFile(String templateName,String sqlMapFile,String fileName,String filePath){
		//模板的详细地址
		String file_path = rootpath+templatePath+templateName;
		//得到模板的内容
		String fileCon = fileUtil.readTxt(file_path);
		//替换模板中的标签为具体代码
		fileContent = replaceTemplate(fileCon);				
		String oldCon= fileUtil.readTxt(sqlMapFile);
		if(oldCon.indexOf(fileContent)==-1){
			oldCon = replaceTemplate(oldCon);
		}		
		//创建文件 和 写入文件内容
		fileUtil.writeTxt(sqlMapFile,"", oldCon);
		System.out.println(sqlMapFile+"文件更新成功");
	}
	
	/*
	 * templateName:模板名称
	 * fileName：生成后的文件名
	 * fileCon：文件内容
	 * filePath：生成后的文件存放地址
	 */
	public void createFile(String templateName,String fileName,String filePath){
		//模板的详细地址
		String file_path = rootpath+templatePath+templateName;
		//得到模板的内容
		String fileCon = fileUtil.readTxt(file_path);
		//替换模板中的标签为具体代码
		fileCon = replaceTemplate(fileCon);
		//创建文件 和 写入文件内容
		fileUtil.writeTxt(rootpath+filePath,fileName, fileCon);
		System.out.println(rootpath+filePath+"/"+fileName+"文件生成成功");
	}
	
	public String replaceTemplate(String fileCon){
		fileCon = fileCon.replace("{TABLENAME}", TABLENAME);
		fileCon = fileCon.replace("{CLASSNAME}", CLASSNAME);
		fileCon = fileCon.replace("{FUNNAME}", FUNNAME);
		fileCon = fileCon.replace("{AUTHOR}", AUTHOR);
		fileCon = fileCon.replace("{DATE}", DATE);
		fileCon = fileCon.replace("{TABLEKEYUPPER}", TABLEKEYUPPER);
		fileCon = fileCon.replace("{TABLEKEY}", TABLEKEY);
		fileCon = fileCon.replace("<!--tagbody-->", fileContent+"<!--tagbody-->");		
		
		String tagname = "fieldlist";
		while(fileCon.indexOf(tagname) > -1){
			fileCon = replaceLoopField(fileCon,tagname);
		}
		tagname = "fieldkeylist";
		while(fileCon.indexOf(tagname) > -1){
			fileCon = replaceLoopField(fileCon,tagname);
		}
		tagname = "searchlist";
		while(fileCon.indexOf(tagname) > -1){
			fileCon = replaceLoopField(fileCon,tagname);
		}
		tagname = "indexdislist";
		while(fileCon.indexOf(tagname) > -1){
			fileCon = replaceLoopField(fileCon,tagname);
		}
		return fileCon;
	}
	
	//循环替换表字段
	@SuppressWarnings("unchecked")
	public String replaceLoopField(String fileCon,String tagname){
		String startTag = "{"+tagname+"}";
		String enTag = "{/"+tagname+"}";
		if(fileCon.indexOf(startTag) == -1) return fileCon;
		int i = fileCon.indexOf(startTag);
		int j = fileCon.indexOf(enTag)+enTag.length();
		//得到标签体
		String tagBody = fileCon.substring(i,j);
		String bodyCon = tagBody.replace(startTag, "").replace(enTag, "");
		
		//取出此表数据库描述
		ArrayList fieldList = dbOperate.getTableDescList(TABLENAME);
		
		StringBuffer sb = new StringBuffer();
		
		if(fieldList!=null && fieldList.size()>0){
			HashMap fieldMap = new HashMap();
			for(int k=0;k<fieldList.size();k++){
				fieldMap = (HashMap)fieldList.get(k);
				String field = "",Null = "";
				if(fieldMap.get("IS_NULLABLE")!=null){
					Null = fieldMap.get("IS_NULLABLE").toString();
				}
				if(fieldMap.get("COLUMN_NAME")!=null){
					field = fieldMap.get("COLUMN_NAME").toString();
				}
				if(field.equals("")) continue;
				if(tagname.equals("fieldlist") && field.equals(TABLEKEY)){
					continue;
				}
				if(tagname.equals("searchlist") && !isExistGroup(SEARCHFIELD,field)){
					continue;
				}
				if(tagname.equals("indexdislist") && !isExistGroup(INDEXDISFIELD,field)){
					continue;
				}
				String temp = bodyCon;
				//直接替换字段
				temp = temp.replace("[field_name]", field);
				//替换第一个字母大写的字段
				temp = temp.replace("[fieldname]", oneWordUpper(field));
				
				temp = temp.replace("[field_name_must]", Null.equalsIgnoreCase("NO")?"*":"&nbsp;");
				
				//替换SQLMap.txt
				temp = temp.replace("[field_node_u]", field);	
				if(k==fieldList.size()-1){
					temp = temp.replace("[field_node],", field);	
				}else{
					temp = temp.replace("[field_node],", field+",");	
				}			
				if(k==fieldList.size()-1){
					temp = temp.replace("#{[field_u_node]},", "#{"+field+"}");	
				}else{
					temp = temp.replace("#{[field_u_node]},", "#{"+field+"},");	
				}		
				
				
				
				sb.append(temp);
			}
		}
		fileCon = fileCon.replace(tagBody, sb.toString());
		
		return fileCon;
	}
	
	//让传进来的字符串第一个字母大写
	public String oneWordUpper(String str){
		if(str.length()==0) return "";
		String oneWord = str.substring(0,1);
		return oneWord.toUpperCase()+str.substring(1,str.length());
	}
	
	//得到当前的日期
	public String getDate(){
		return new java.util.Date().toString();
	}
	
	public boolean isExistGroup(String fieldGro,String field){
		String fid[] = fieldGro.split(",");
		boolean ret = false;
		for(int i=0;i<fid.length;i++){
			if(fid[i].equals(field)){
				ret = true;
				break;
			}
		}
		return ret;
	}
	
}
