package com.xingfugo.business.outapi.movie.module;

import java.util.List;

public class RowSeat {
	// 行编号
	private String rowNo;

	// 内部排号
	private String imgRow;

	//座位信息列表
	private List<Seat> seatList;

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getImgRow() {
		return imgRow;
	}

	public void setImgRow(String imgRow) {
		this.imgRow = imgRow;
	}

	public List<Seat> getSeatList() {
		return seatList;
	}

	public void setSeatList(List<Seat> seatList) {
		this.seatList = seatList;
	}
}
