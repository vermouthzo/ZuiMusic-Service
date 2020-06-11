package com.HATFmusic.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.HATFmusic.model.Song;

@Repository
public interface SongRepository extends MongoRepository<Song, String>{

	public List<Song> findBySongname(String songname);
	
}
