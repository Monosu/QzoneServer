package com.higgs.qzoneserver.model;

import java.util.Date;

public class FriendMoving {
	private int id;
	private int movingType;
	private int movingId;
	private int userid;
	private Date postTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMovingType() {
		return movingType;
	}
	public void setMovingType(int movingType) {
		this.movingType = movingType;
	}
	public int getMovingId() {
		return movingId;
	}
	public void setMovingId(int movingId) {
		this.movingId = movingId;
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
