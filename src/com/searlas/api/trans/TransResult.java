package com.searlas.api.trans;

import java.util.List;

public class TransResult {
	private String from;
	private String to;
	private List<String> trans_result;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public List<String> getTrans_result() {
		return trans_result;
	}
	public void setTrans_result(List<String> trans_result) {
		this.trans_result = trans_result;
	}
	@Override
	public String toString() {
		return "TransResult [from=" + from + ", to=" + to + ", trans_result=" + trans_result + ", getFrom()="
				+ getFrom() + ", getTo()=" + getTo() + ", getTrans_result()=" + getTrans_result() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
