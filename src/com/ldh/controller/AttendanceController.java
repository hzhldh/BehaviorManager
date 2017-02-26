package com.ldh.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldh.model.Attendance;
import com.ldh.service.AttendanceService;;

   @Controller
   @RequestMapping("/page")
   public class AttendanceController {
	 //注入service
	 @Autowired
	 private AttendanceService attendanceService;
	 public void setService(AttendanceService attendanceService) {
	 this.attendanceService = attendanceService;
	 }
	 
	//加载全部考勤记录
	@RequestMapping(value="/showAllAttendance")
	@ResponseBody
	public void getJson(int limit,int offset,HttpServletRequest request,HttpServletResponse response) throws IOException {	 	
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");	
		List<Attendance> list=null;
			
		//将GET参数全部放到Map中
		Map map=request.getParameterMap();
		if (map.get("search")!=null) {
			System.out.println("要查询");
			//list=attendanceService.selectStudentByName(request.getParameter("search"));
		}else {
			list=attendanceService.showAllAttendance(limit, offset);
		}
			
		//将List转换为json字符串
		ObjectMapper mapper=new ObjectMapper(); 		
		String result=mapper.writeValueAsString(list);
		System.out.println("list集合为："+list);
		System.out.println("转换为json："+result);
		//查询整表总记录数
	 	int total=attendanceService.selectCount();
		//需要返回的数据有总记录数和行数据   
	 	String json = "{\"total\":" + total + ",\"rows\":" + result + "}";
		response.getWriter().println(json); 
	}  
	
	
	
}
