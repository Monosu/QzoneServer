package com.higgs.qzoneserver.model;

import java.util.Date;

public class JournalReply {
	private int id;
	private String replycontext;
	private Date postTime;
	private int jid;
	private int userId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReplycontext() {
		return replycontext;
	}
	public void setReplycontext(String replycontext) {
		this.replycontext = replycontext;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public int getJid() {
		return jid;
	}
	public void setJid(int jid) {
		this.jid = jid;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIsShowName() {
		return isShowName;
	}
	public void setIsShowName(int isShowName) {
		this.isShowName = isShowName;
	}
	private int isShowName;
}
