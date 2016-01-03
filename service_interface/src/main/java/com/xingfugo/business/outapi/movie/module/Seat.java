package com.xingfugo.business.outapi.movie.module;


public class Seat {
	// 场区编号
	private String locNo;

	// 列号
	private String colNo;

	// 座位类型, 1--座位 2-情侣座 3-走廊
	private String type;

	// 座位号
	private String no;

	// 座位状态, 0.可选;1.已售;2.维修
	private String status;

	public String getLocNo() {
		return locNo;
	}

	public void setLocNo(String locNo) {
		this.locNo = locNo;
	}

	public String getColNo() {
		return colNo;
	}

	public void setColNo(String colNo) {
		this.colNo = colNo;
	}

	public String getType() {
		return type;
	}
	
	public String getTypeDesc() {
		if("1".equals(type)){
			return "座位";
		}
		
		if("2".equals(type)){
			return "情侣座";
		}
		
		if("3".equals(type)){
			return "走廊";
		}
		
		return "未知座位类型";
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getStatus() {
		return status;
	}
	
	public String getStatusDesc() {
		if("0".equals(status)){
			return "可选";
		}
		if("1".equals(status)){
			return "已售";
		}
		if("2".equals(status)){
			return "维修";
		}
		
		return "未知座位状态";
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("场区:").append(locNo);
		buf.append(";列号:").append(colNo);
		buf.append(";类型:").append(getTypeDesc());
		//buf.append(";座位号:").append(no);
		buf.append(";状态:").append(getStatusDesc());
		
		return buf.toString();
	}
	
	
}
