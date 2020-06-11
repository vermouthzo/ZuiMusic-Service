package com.HATFmusic.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Comment {
	private String auther;  //评论作者
	private String content;	//评论内容
	private int star = 0;	//评论点赞数
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dt = LocalDateTime.now(); //评论时间

	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public LocalDateTime getDt() {
		return dt;
	}

	public void setDt(LocalDateTime dt) {
		this.dt = dt;
	}

	@Override
	public String toString() {
		return "Comment [auther=" + auther + ", content=" + content + ", star=" + star + ", dt=" + dt + "]";
	}
	
	
}
