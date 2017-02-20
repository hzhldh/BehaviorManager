package com.ldh.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ldh.model.User;

public interface UserDao {
	
	//验证登录用户
	@Select("select * from user where username=#{username} and password=#{password}")
	User selectUserByNamePwd(@Param("username")String username,@Param("password")String password);
	
	//注册用户，新增用户
	@Insert("insert into user(username,password) values(#{username}, #{password})")
	void insertUser(User user);
	
}
