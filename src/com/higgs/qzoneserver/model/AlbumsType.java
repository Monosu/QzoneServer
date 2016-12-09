package com.higgs.qzoneserver.model;

import java.util.Date;

public class AlbumsType {
	private int id;
	private String typeName;
	private String typeDescript;
	private int userId;
	private String readCount;
	private int typeBgImg;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeDescript() {
		return typeDescript;
	}
	public void setTypeDescript(String typeDescript) {
		this.typeDescript = typeDescript;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getReadCount() {
		return readCount;
	}
	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}
	public int getTypeBgImg() {
		return typeBgImg;
	}
	public void setTypeBgImg(int typeBgImg) {
		this.typeBgImg = typeBgImg;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	private Date addTime;
	
}
