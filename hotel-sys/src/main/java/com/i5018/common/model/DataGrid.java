package com.i5018.common.model;

import java.util.ArrayList;

import java.util.List;

public class DataGrid<T> {

	private Long total = 0L; // 总条数

	private List<T> rows = new ArrayList<T>(); // 显示的条目

	private List<T> footer = new ArrayList<T>();

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public List<T> getFooter() {
		return footer;
	}

	public void setFooter(List<T> footer) {
		this.footer = footer;
	}

}
