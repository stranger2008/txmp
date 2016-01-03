package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class CinemaSeat {
	// 影厅名称
	@XStreamAlias("HallName")
	private String hallName;

	// 影厅编号
	@XStreamAlias("HallNo")
	private String hallNo;

	// 场区编号
	@XStreamAlias("LocNo")
	private String locNo;

	// 行编号
	@XStreamAlias("RowNo")
	private String rowNo;

	// 列数据(集合)
	@XStreamAlias("ColumnNo")
	private String columnNo;

	// 座位类型(集合), 1--座位 2-情侣座 3-走廊
	@XStreamAlias("SeatType")
	private String seatType;

	// 内部排序号
	@XStreamAlias("SeatImgRow")
	private String seatImgRow;

	// 座位号(集合)
	@XStreamAlias("SeatNo")
	private String seatNo;

	// 座位状态(集合), 0.可选;1.已售;2.维修
	@XStreamAlias("SeatStatus")
	private String seatStatus;

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public String getHallNo() {
		return hallNo;
	}

	public void setHallNo(String hallNo) {
		this.hallNo = hallNo;
	}

	public String getLocNo() {
		return locNo;
	}

	public void setLocNo(String locNo) {
		this.locNo = locNo;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(String columnNo) {
		this.columnNo = columnNo;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getSeatImgRow() {
		return seatImgRow;
	}

	public void setSeatImgRow(String seatImgRow) {
		this.seatImgRow = seatImgRow;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}
}
