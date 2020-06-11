package com.HATFmusic.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.HATFmusic.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	public User findOneByUsernameAndPassword(String username, String password);
}
