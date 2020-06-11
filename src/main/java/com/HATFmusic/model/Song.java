package com.HATFmusic.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

/**
 * @author lenovo
 *
 */
public class Song {
	@Id
	private String id;		 //音乐id
	private String auther;   //作者
	private String songname; //歌曲名称
	private String cover;    //图片
	private String path;     //网址
	private int duration; 	 //歌曲长度
    private long size; 		 //歌曲的大小
    
	private List<Comment> comments;
	/**
	 * 返回此频道的所有评论
	 * @return
	 */
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * 向当前评论增加一条评论
	 * @param comment 待增加的评论
	 */
	public void addComment(Comment comment) {
		if (null == this.comments) {
			this.comments = new ArrayList<>();
		}
		this.comments.add(comment);
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	public String getSongname() {
		return songname;
	}
	public void setSongname(String songname) {
		this.songname = songname;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Song [id=" + id + ", auther=" + auther + ", songname=" + songname + ", cover=" + cover + ", path="
				+ path + ", duration=" + duration + ", size=" + size + ", comments=" + comments + "]";
	}
	
	
}
