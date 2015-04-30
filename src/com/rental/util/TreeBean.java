package com.rental.util;

import java.util.List;

public class TreeBean {
	private String text;//标题
	private String icon;//列表树节点上的图标，通常是节点坐标的图标
	private String backColor;//节点的背景色，覆盖全局的背景色选项
	private String  href;//结合全局enableLinks选项为列表树节点指定UR
	private List<TreeBean> nodes;//数据
	private String tags;
	public TreeBean() {
	}
	public TreeBean(String text) {
		this.text = text;
	}
	public TreeBean(String text,String tags) {
		this.text = text;
		this.tags = tags;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getBackColor() {
		return backColor;
	}
	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public List<TreeBean> getNodes() {
		return nodes;
	}
	public void setNodes(List<TreeBean> nodes) {
		this.nodes = nodes;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	}
