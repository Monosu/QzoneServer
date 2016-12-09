package com.higgs.qzoneserver.model;

import java.util.Date;

public class MoodReply {
	private int id;
	private int moodid;
	private String replyContext;
	private int userid;
	private Date postTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMoodid() {
		return moodid;
	}
	public void setMoodid(int moodid) {
		this.moodid = moodid;
	}
	public String getReplyContext() {
		return replyContext;
	}
	public void setReplyContext(String replyContext) {
		this.replyContext = replyContext;
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
