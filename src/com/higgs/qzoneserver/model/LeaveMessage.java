package com.higgs.qzoneserver.model;

import java.util.Date;

public class LeaveMessage {
	private int id;
	private String context;
	private int postuser;
	private int formuser;
	private Date postTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getPostuser() {
		return postuser;
	}
	public void setPostuser(int postuser) {
		this.postuser = postuser;
	}
	public int getFormuser() {
		return formuser;
	}
	public void setFormuser(int formuser) {
		this.formuser = formuser;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
}
