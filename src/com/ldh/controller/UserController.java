package com.ldh.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




import org.springframework.web.servlet.ModelAndView;

import com.ldh.model.User;
import com.ldh.service.UserService;

@Controller
@RequestMapping("/page")
public class UserController{
	//注入service
	@Autowired
	private UserService service;
	public void setService(UserService service) {
		this.service = service;
	}


	@RequestMapping("/register")
	public String doRegister(String username,String password){

		User user=new User(username, password);
		
		service.addUser(user);

		return "/welcome";
	}
	
	@RequestMapping("/login")
	public ModelAndView login(String username,String password,ModelAndView mv,HttpSession session){
		User user=service.login(username, password);
		if (user!=null) {
			// 登录成功，将用户名设置到HttpSession作用范围域
			String loginName=user.getUsername();
			session.setAttribute("loginName", loginName);
			//跳转到登录页面
			mv.setViewName("index");
		}else {
			//登录失败，设置失败信息，并跳转到登录页面
			mv.addObject("message", "登录名或密码错误，请重新输入!");
			mv.setViewName("login");
		}
		return mv;
	}

}
