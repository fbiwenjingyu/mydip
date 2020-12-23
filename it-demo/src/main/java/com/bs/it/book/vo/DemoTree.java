package com.bs.it.book.vo;

import java.util.List;

public class DemoTree {
	private Long id;
	private String value;
	private String label;
	private List<DemoTree> children;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<DemoTree> getChildren() {
		return children;
	}
	public void setChildren(List<DemoTree> children) {
		this.children = children;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DemoTree(String value, String label) {
		super();
		this.value = value;
		this.label = label;
	}
	public DemoTree() {
		super();
	}
	
	
	

}
