package com.ldh.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldh.model.Student;
import com.ldh.service.StudentService;

@Controller
@RequestMapping("/page")
public class StudentController {
	//注入service
	@Autowired
	private StudentService studentService;
	public void setService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@RequestMapping(value="/showAllStudents")
	@ResponseBody
	public void getJson(int limit,int offset,String order,String sort,HttpServletRequest request,HttpServletResponse response) throws IOException {	 	
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");	
		List<Student> list=null;
		
		//将GET参数全部放到Map中
		Map map=request.getParameterMap();
		System.out.println(map);
		if (map.get("search")!=null) {
			System.out.println("要查询");
			System.out.println(request.getParameter("search"));
			list=studentService.selectStudentByName(request.getParameter("search"));
		}else if (map.get("sort")!=null) {
			System.out.println("排序");
			list=studentService.showAllStudentsBySort(limit, offset, sort, order);
		}else {
			list=studentService.showAllStudents(limit, offset);
		}
		
		//将List转换为json字符串
		ObjectMapper mapper=new ObjectMapper(); 		
		String result=mapper.writeValueAsString(list);
		//查询整表总记录数
 		int total=studentService.selectCount();
		//需要返回的数据有总记录数和行数据   
 	    String json = "{\"total\":" + total + ",\"rows\":" + result + "}";
 	    System.out.println(json);
		response.getWriter().println(json); 
    }
	
	
	//删除记录控制器
	@RequestMapping(value="/del")
	public void del(HttpServletRequest request,HttpServletResponse response) throws IOException{
		int id= Integer.parseInt(request.getParameter("id"));
		if (studentService.delStudent(id)) {
			response.getWriter().print(0);
		}else {
			response.getWriter().print(1);
		}
		
	}
	
	//新增学生
	@RequestMapping("/addStudent")
	public void addStudent(String name,int age,String classname,HttpServletResponse response) throws IOException{
		Student student=new Student(name, age, classname);
		if (studentService.addStudent(student)) {
			response.getWriter().print(0);
		}else {
			response.getWriter().print(1);
		}		
	}
	
	//更改学生
	@RequestMapping("/updateStudent")
	public void updateStudent(int id,String name,int age,String classname,HttpServletResponse response) throws IOException{
		Student student=new Student(name, age, classname);
		student.setId(id);
		if (studentService.updateStudent(student)) {
			response.getWriter().print(0);
		}else {
			response.getWriter().print(1);
		}		
	}
	
	
}
