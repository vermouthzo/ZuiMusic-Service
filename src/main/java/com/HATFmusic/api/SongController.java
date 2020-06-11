package com.HATFmusic.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HATFmusic.model.Comment;
import com.HATFmusic.model.Song;
import com.HATFmusic.service.SongService;
import com.HATFmusic.api.SongController;

@RestController
@RequestMapping("/song")
public class SongController {
	public static final Logger logger = LoggerFactory.getLogger(SongController.class);
	
	@Autowired
	private SongService service;	
	
	@GetMapping
	public List<Song> getAllSongs() {
		logger.info("正在查找本地所有歌曲信息：");
		List<Song> results = service.getAllSongs();
		return results;
	}
	
	@GetMapping("/id")
	public Song getSong(@PathVariable String id){
		logger.info("正在读取歌曲："+id);
		Song c = service.getSong(id);
		if(c != null) {
			return c;
		}else {
			logger.error("未找到！");
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteChannel(@PathVariable String id) {
		System.out.println("即将删除频道，id="+id);
		boolean result = service.deleteSong(id);
		if(result) {
			return ResponseEntity.ok().body("删除成功");
		}else {
			return ResponseEntity.ok().body("删除失败");
		}
	}
	
	@PostMapping
	public Song createSong(@RequestBody Song c) {
		System.out.println("即将新建频道:" +c);
		Song saved = service.createSong(c);
		return saved;
	}
	
	@PutMapping
	public Song updateChannel(@RequestBody Song c) {
		System.out.println("即将新建频道:" +c);
		Song update = service.updateSong(c);
		return update;
	}
	
	@PostMapping("{songId}/comment")
	public Song addComment(@PathVariable String songId, @RequestBody Comment comment) {
		Song result = null;
		logger.debug("即将评论频道：" +songId+ ",评论对象："+comment);
		//TODO 把评论保存到数据库
		result = service.addComment(songId, comment);
		return result;
	}
	
	@GetMapping("{songId}/hotcomments")
	public List<Comment> hotComments(@PathVariable String songId){
		List<Comment> result = null;
		logger.debug("获取频道" +songId+ "的热门评论");
		result = service.hotComments(songId);
		return result;
	}
	
	@GetMapping("{songname}")
	public List<Song> getSongsPage(@PathVariable String songname){
		return service.findSongName(songname);
	}
	
}
