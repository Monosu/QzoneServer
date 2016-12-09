package com.higgs.qzoneserver.model;

import java.util.Date;

public class UserFiles {
	private int id;
	private String fileExp;
	private String filePath;
	private String fileName;
	private long fileSize;
	private Date uploadTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileExp() {
		return fileExp;
	}
	public void setFileExp(String fileExp) {
		this.fileExp = fileExp;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
}
