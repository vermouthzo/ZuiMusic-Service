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
	 *  ��ȡ���и���
	 * @return
	 */
	public List<Song> getAllSongs() {
		return repo.findAll();
	}

	/**
	 * ��ȡһ�׸���������
	 * 
	 * @param  songId �������
	 * @return ��������,��δ�ҵ��򷵻�null
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
	 * ��ָ��Ƶ��׷��һ������
	 * @param channelId	Ŀ�����۵ı��
	 * @param comment	������ӵ�����
	 */
	public Song addComment(String songId, Comment comment) {
		Song result = null;
		Song saved = getSong(songId);
		if (null != saved) {	//�������и�����
			saved.addComment(comment);
			result = repo.save(saved);
		}
		return result;
	}
	/**
	 * ��ȡĿ��Ƶ�����������ۡ�
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
					//��o1��o2С���򷵻ظ�������o1��o2���򷵻���������o1����o2���򷵻�0��
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
			logger.debug("����������" +result.size()+ "��...");
			logger.debug(result.toString());
		}else {
			logger.warn("ָ����Ƶ�������ڣ�id=" + songId);
		}
		return result;
	}

	/**
	 * ɾ��ָ����Ƶ��
	 * 
	 * @param channelId ��ɾ����Ƶ�����
	 * @return ��ɾ���ɹ��򷵻�true�����򷵻�false
	 */
	public boolean deleteSong(String songlId){
		boolean result = true;
		repo.deleteById(songlId);
		return result;
	}
	
	/**
	 * ����Ƶ��
	 * 
	 * @param c �������Ƶ������û��idֵ��
	 * @return ������Ƶ������idֵ��
	 */
	public Song createSong(Song c){
		return repo.save(c); //������º��ʵ�����
	}
	
	/**
	 * ����ָ����Ƶ����Ϣ
	 * 
	 * @param c �µ�Ƶ����Ϣ�����ڸ����Ѵ��ڵ�ͬһƵ��
	 * @return ���º��Ƶ����Ϣ
	 */
	public Song updateSong(Song c) {
		//TODO ���޸��û�ָ��������
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
		// FIXME ��������׷�ӵ������ۺ�
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
