package com.HATFmusic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HATFmusic.dao.UserRepository;
import com.HATFmusic.model.User;

@Service 
public class UserService {
	@Autowired
	private UserRepository repo;
	public static final Logger logger = LoggerFactory.getLogger(UserService.class);

	/**
	 * �½��û� ���û�ע�ᣩ��
	 * @param user
	 * @return
	 */
	public User createUser(User user) {
		logger.debug("�û�ע��: " +user);
		User result = null;
		result = repo.save(user);
		return result;
	}
	
	public boolean checkUser(String username, String password) {
		boolean result = false;
		User u = repo.findOneByUsernameAndPassword(username, password);
		if(null != u) {
			result = true;
		}
		return result;
	}
}