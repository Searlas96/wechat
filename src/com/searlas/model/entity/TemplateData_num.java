package com.searlas.model.entity;

public class TemplateData_num {
	@Override
	public String toString() {
		return "TemplateData_num [value=" + value + ", color=" + color + "]";
	}
	private String value;
	private String color;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
