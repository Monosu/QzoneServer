package com.higgs.qzoneserver.model;

public class UserQzone {
	private int id;
	private String qzoneName;
	private String qzoneDescript;
	private int readCount;
	private int userId;
	private int dressUpId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQzoneName() {
		return qzoneName;
	}
	public void setQzoneName(String qzoneName) {
		this.qzoneName = qzoneName;
	}
	public String getQzoneDescript() {
		return qzoneDescript;
	}
	public void setQzoneDescript(String qzoneDescript) {
		this.qzoneDescript = qzoneDescript;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getDressUpId() {
		return dressUpId;
	}
	public void setDressUpId(int dressUpId) {
		this.dressUpId = dressUpId;
	}
	
}
