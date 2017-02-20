package com.ldh.service;

import com.ldh.model.User;

public interface UserService {
	//新增用户
	void addUser(User user);
	//判断用户登录
	User login(String username,String password);

}

