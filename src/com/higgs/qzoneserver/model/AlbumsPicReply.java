package com.higgs.qzoneserver.model;

import java.util.Date;

public class AlbumsPicReply {
	private int id;
	private int picId;
	private int userId;
	private String ReplyContext;
	private Date postTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPicId() {
		return picId;
	}
	public void setPicId(int picId) {
		this.picId = picId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getReplyContext() {
		return ReplyContext;
	}
	public void setReplyContext(String replyContext) {
		ReplyContext = replyContext;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
}
