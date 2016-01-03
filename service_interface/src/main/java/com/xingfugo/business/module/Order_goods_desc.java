package com.xingfugo.business.module;

//订单里的商品json数据，方便在会员中心订单列表显示不同类型的订单
//如商品、机票、电影票、彩票
//商品：设定 id，name，img
//机票：设定 name，如上海到北京
//电影票： 设定 name，如 天下无贼-万达影城
public class Order_goods_desc {
	private Integer id;
	private String name;
	private String img;
	private String remark;
	//是否可申请退换货
	private boolean canApply;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public boolean isCanApply() {
		return canApply;
	}
	public void setCanApply(boolean canApply) {
		this.canApply = canApply;
	}
	 
}
