package com.searlas.model.entity;

public class TemplateData {
	private  FirstData first;
	private TemplateData_time keyword1;
	private TemplateData_num keyword2;
	private TemplateData_amount keyword3;
	private  RemarkData remark;
	public FirstData getFirst() {
		return first;
	}
	public void setFirst(FirstData first) {
		this.first = first;
	}
	public TemplateData_time getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(TemplateData_time keyword1) {
		this.keyword1 = keyword1;
	}
	public TemplateData_num getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(TemplateData_num keyword2) {
		this.keyword2 = keyword2;
	}
	public TemplateData_amount getKeyword3() {
		return keyword3;
	}
	public void setKeyword3(TemplateData_amount keyword3) {
		this.keyword3 = keyword3;
	}
	public RemarkData getRemark() {
		return remark;
	}
	public void setRemark(RemarkData remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "TemplateData [first=" + first + ", keyword1=" + keyword1 + ", keyword2=" + keyword2 + ", keyword3="
				+ keyword3 + ", remark=" + remark + "]";
	}
	
	
	
	
}
