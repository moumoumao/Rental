package com.rental.util;

import java.util.List;

public class PageBean {
	private int pageNo;//页码
	private int pageSize;//每页有多少记录
	private int recordsTotal;//一共有多少条记录
	private int recordsFiltered;//一共有多少条记录（datatable需要参数）
	private int pageCount;//一共有多少页
	private List aaData;//数据
	private int draw=1;//是否刷新
	public PageBean() {
		super();
	}
	public PageBean( List aaData, int recordsTotal,int pageNo,int pageSize) {
		super();
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsTotal;
		this.aaData = aaData;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public int getPageCount() {
		return (recordsTotal/pageSize) + ((recordsTotal%pageSize==0)?0:1);
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List getAaData() {
		return aaData;
	}
	public void setAaData(List aaData) {
		this.aaData = aaData;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	
	
}
