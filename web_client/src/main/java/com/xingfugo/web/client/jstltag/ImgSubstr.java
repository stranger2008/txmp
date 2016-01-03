package com.xingfugo.web.client.jstltag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

//读取商品图片第一张
public class ImgSubstr extends TagSupport {
	
	private static final long serialVersionUID = -4571087482518236219L;
	
	private String imgpath;

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public ImgSubstr() {
		//System.out.println("MyTag构造方法：一个myTag类的对象被构建了....");
	}

	public int doStartTag() {
		JspWriter out = this.pageContext.getOut();
		
		int pos = imgpath.indexOf(",");
		String _imgpath = "";
		if(pos == -1){
			_imgpath = imgpath;
		}else{
			_imgpath = imgpath.substring(0,pos);
		}
		
		try {
			out.print(_imgpath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//System.out.println("对象正在处理开始标记.....");
		return EVAL_BODY_INCLUDE;
	}

	public int doAfterBody() throws JspException {
		//System.out.println("处理标签体（after body）....");
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		//System.out.println("对象正在处理结束标记.....");
		return EVAL_PAGE;
	}
}
