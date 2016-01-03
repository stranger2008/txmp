package com.xingfugo.file.service;

import com.xingfugo.file.module.ImgSpecConfig;

/**
 * 图片规格对象，用于自动生成该规格图片 
 */
public class ImageSpec {
	//是否按比例缩放
	private boolean proportion = true;
	//原图片宽度
	private int origImageWidth = 0;
	//原图片高度
	private int origImageHeight = 0;

	public ImageSpec(int origImageWidth, int origImageHeight){
		this.origImageWidth = origImageWidth;
		this.origImageHeight = origImageHeight;
	}
	
	public ImageSpec(boolean proportion, int origImageWidth, int origImageHeight){
		this.proportion = proportion;
		this.origImageWidth = origImageWidth;
		this.origImageHeight = origImageHeight;
	}
	/**
	 * 计算缩放后的宽高
	 */
	public int[] computeSpecSize(ImgSpecConfig spec){
		//等比例缩放
		if(proportion){
			double rateWidth = (double)spec.getWidth() / (double)origImageWidth;
			double rateHeight = (double)spec.getHeigth() / (double)origImageHeight;
			double rate = (rateWidth > rateHeight) ? rateHeight : rateWidth;
			
			double specWidth = (double)origImageWidth * rate;
			double specHeight = (double)origImageHeight * rate;
			
			return new int[]{(int)specWidth, (int)specHeight};
		}
		
		return new int[]{spec.getWidth(), spec.getHeigth()};
	}
}
