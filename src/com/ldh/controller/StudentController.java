package com.ldh.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import org.springframework.web.multipart.MultipartFile;

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
	
	//加载全部学籍信息
	@RequestMapping(value="/showAllStudents")
	@ResponseBody
	public void getJson(int limit,int offset,HttpServletRequest request,HttpServletResponse response) throws IOException {	 	
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");	
		List<Student> list=null;
		
		//将GET参数全部放到Map中
		Map map=request.getParameterMap();
		if (map.get("search")!=null) {
			System.out.println("要查询");
			list=studentService.selectStudentByName(request.getParameter("search"));
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
		response.getWriter().println(json); 
    }
	
	
	//删除记录控制器
	boolean falg;
	@RequestMapping(value="/del")
	public void del(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String[] idString=request.getParameterValues("id");
		for (int i = 0; i < idString.length; i++) {
			long id= Long.parseLong(idString[i]);
			falg = studentService.delStudent(id);
		}
		if (falg) {
			response.getWriter().print(0);
		}else {
			response.getWriter().print(1);
		}
	}
	
	//新增学生
	@RequestMapping("/addStudent")
	public void addStudent(Long id,String name,String classname,String major,String native_place,HttpServletResponse response) throws IOException{
		Student student=new Student(name,classname,major,native_place);
		student.setId(id);
		if (studentService.addStudent(student)) {
			response.getWriter().print(0);
		}else {
			response.getWriter().print(1);
		}		
	}
	
	//更改学生
	@RequestMapping("/updateStudent")
	public void updateStudent(long id,String name,String classname,String major,String native_place,HttpServletResponse response) throws IOException{
		Student student=new Student(name,classname,major,native_place);
		student.setId(id);
		if (studentService.updateStudent(student)) {
			response.getWriter().print(0);
		}else {
			response.getWriter().print(1);
		}		
	}
	
	//下载导入模板
	@RequestMapping("/downloadTemplate")  
    public void downloadTemplate(HttpServletResponse response,HttpServletRequest request) throws IOException{  
		//获取所要下载文件的路径  
        String path = request.getServletContext().getRealPath("/upload/导入模板.xls");        
        File file=new File(path);
        //判断该路径下文件是否存在
        if (file.exists()) {
        	FileInputStream fileInputStream=new FileInputStream(file);
            String filename=URLEncoder.encode(file.getName(),"utf-8");//解决中文文件名下载后乱码的问题  
            byte[] b = new byte[fileInputStream.available()];  
            fileInputStream.read(b);  
            response.setCharacterEncoding("utf-8");  
            response.setHeader("Content-Disposition","attachment; filename="+filename+"");  
            //获取响应报文输出流对象  
            ServletOutputStream  out =response.getOutputStream();  
            //输出  
            out.write(b);  
            out.flush();  
            out.close();
		}else {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("<script language='JavaScript'>alert('导入模板.xls不存在');</script>");
		}
          
    }
	
	//导出全部学生信息
	@RequestMapping("/exportExcel")  
    public void exportExcel(HttpServletResponse response) throws IOException{  		
        //查询全部数据，并进行excel填充
        List<Student> list = studentService.showAllStudents(500, 1);  
        HSSFWorkbook wb=studentService.export(list);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String filename = new String("中文文件名称".getBytes(),"iso8859-1");
        response.setHeader("Content-Disposition", "attachment;"+filename+".xls");
        OutputStream ouputStream = response.getOutputStream();    
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }
	
	//上传文件，并解析excel数据，插入数据库
	@RequestMapping("/upload")
	public void upload(@RequestParam("file")MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{	
	// 拼接上传文件路径
	String path = request.getServletContext().getRealPath("/upload/");
	// 获取上传文件名
	String filename = file.getOriginalFilename();
	File filepath = new File(path,filename);
	// 判断目录是否存在，如果不存在就创建一个
    if (!filepath.getParentFile().exists()) { 
     filepath.getParentFile().mkdirs();
    }
    //拼接该文件的绝对路径,分隔符用File.separator 代替
    String absolutePath=path+File.separator+filename;
    // 将上传文件保存到一个目标文件当中，替换该目标文件
	file.transferTo(new File(absolutePath));	
	//开始解析该文件
	List<Student> list=studentService.readExcel(absolutePath);
	int count=studentService.savaDataToDB(list);
	response.setContentType("text/html; charset=UTF-8");;
	response.getWriter().print("<script language='JavaScript'>alert('导入成功,共导入"+count+"条记录');location.href='ShowAllStudent.jsp';</script>");
	}
	

}
