package com.higgs.qzoneserver.model;

import java.util.Date;

public class AlbumsPic {
	private int id;
	private int fid;
	private String picDescript;
	private String picName;
	private int typeId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getPicDescript() {
		return picDescript;
	}
	public void setPicDescript(String picDescript) {
		this.picDescript = picDescript;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	private Date addTime;
	
}
