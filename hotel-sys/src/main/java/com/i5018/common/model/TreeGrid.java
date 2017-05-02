package com.i5018.common.model;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("all")
public class TreeGrid implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5000343604848016510L;
	private String id;
	private String text;
	private String parentId;
	private String parentText;
	private Map<String, Object> attributes;// 其他参数
	private String state = "open";// 是否展开(open,closed)
	private String order;// 排序

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getParentText() {
		return parentText;
	}

	public void setParentText(String parentText) {
		this.parentText = parentText;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
