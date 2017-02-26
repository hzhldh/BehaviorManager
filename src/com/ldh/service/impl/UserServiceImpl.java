package com.ldh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldh.dao.UserDao;
import com.ldh.model.User;
import com.ldh.service.UserService;

//@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao dao;
	
	@Transactional
	@Override
	public void addUser(User user) {		
		dao.insertUser(user);
	}
	
	@Transactional
	@Override
	public User login(String username, String password) {
		
		return dao.selectUserByNamePwd(username, password);
	}
}