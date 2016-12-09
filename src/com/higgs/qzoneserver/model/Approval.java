package com.higgs.qzoneserver.model;

import java.util.Date;

public class Approval {
	private int id;
	private int filedid;
	private int filedType;
	private int userid;
	private Date postTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFiledid() {
		return filedid;
	}
	public void setFiledid(int filedid) {
		this.filedid = filedid;
	}
	public int getFiledType() {
		return filedType;
	}
	public void setFiledType(int filedType) {
		this.filedType = filedType;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
}
