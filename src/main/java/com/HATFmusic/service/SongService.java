package com.HATFmusic.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HATFmusic.dao.SongRepository;
import com.HATFmusic.model.Comment;
import com.HATFmusic.model.Song;

@Service
public class SongService {
	
	public static final Logger logger = LoggerFactory.getLogger(SongService.class);
	@Autowired
	private SongRepository repo;
	
	/**
	 *  获取所有歌曲
	 * @return
	 */
	public List<Song> getAllSongs() {
		return repo.findAll();
	}

	/**
	 * 获取一首歌曲的数据
	 * 
	 * @param  songId 歌曲编号
	 * @return 歌曲对象,若未找到则返回null
	 */
	public Song getSong(String songId) {
		Optional<Song> result = repo.findById(songId);

		if(result.isPresent()){
			return result.get();
		}else {
			return null;
		}
	}

	/**
	 * 向指定频道追加一条评论
	 * @param channelId	目标评论的编号
	 * @param comment	即将添加的评论
	 */
	public Song addComment(String songId, Comment comment) {
		Song result = null;
		Song saved = getSong(songId);
		if (null != saved) {	//数据中有该评论
			saved.addComment(comment);
			result = repo.save(saved);
		}
		return result;
	}
	/**
	 * 获取目标频道的热门评论。
	 * @param channelId
	 * @return
	 */
	public List<Comment> hotComments(String songId){
		List<Comment> result = null;
		Song saved = getSong(songId);
		if(saved != null) {
				result = saved.getComments();
				result.sort(new Comparator<Comment>() {
				@Override
				public int compare(Comment o1, Comment o2) {
					//若o1比o2小，则返回负数；若o1比o2大，则返回正数；若o1等于o2，则返回0。
					int re = 0;
					if (o1.getStar() > o2.getStar()) {
						re = -1;
					}else if(o1.getStar() < o2.getStar()) {
						re = 1;
					}
					return re;
				}
			});
			if (result.size()>3) {
				result = result.subList(0, 3);
			}
			logger.debug("热门评论有" +result.size()+ "条...");
			logger.debug(result.toString());
		}else {
			logger.warn("指定的频道不存在，id=" + songId);
		}
		return result;
	}

	/**
	 * 删除指定的频道
	 * 
	 * @param channelId 待删除的频道编号
	 * @return 若删除成功则返回true，否则返回false
	 */
	public boolean deleteSong(String songlId){
		boolean result = true;
		repo.deleteById(songlId);
		return result;
	}
	
	/**
	 * 保存频道
	 * 
	 * @param c 待保存的频道对象（没有id值）
	 * @return 保存后的频道（有id值）
	 */
	public Song createSong(Song c){
		return repo.save(c); //保存更新后的实体对象
	}
	
	/**
	 * 更新指定的频道信息
	 * 
	 * @param c 新的频道信息，用于更新已存在的同一频道
	 * @return 更新后的频道信息
	 */
	public Song updateSong(Song c) {
		//TODO 仅修改用户指定的属性
		Song saved = getSong(c.getId());
		if (c.getAuther() != null) {
			saved.setAuther(c.getAuther());
		}
		if(c.getSongname() != null) {
			saved.setSongname(c.getSongname());
		}
		if (c.getCover() != null) {
			saved.setCover(c.getCover());
		}
		// FIXME 把新评论追加到老评论后
		if (c.getComments() != null) {
			if(saved.getComments() != null) {
				saved.getComments().addAll(c.getComments());
			}else{
				saved.setComments(c.getComments());
			}
		}
		return repo.save(saved);
	}
	

	public List<Song> findSongName(String songname) {
		return repo.findBySongname(songname);
	}
	
}
