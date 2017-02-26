package com.ldh.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ldh.model.Student;

public interface StudentService {
	//显示所有学生信息
	List<Student> showAllStudents(int limit, int offset);
	
	//分页查询，查询记录总数
	int selectCount();
	
	//根据Id删除学生记录
	boolean delStudent(long id);
	
	//模糊查询
	List<Student> selectStudentByName(String search);
	
	//增加学生
	boolean addStudent(Student student);
	
	//更改学生信息，根据id
	boolean updateStudent(Student student);
	
	//导出学生信息excel表格
	HSSFWorkbook export(List<Student> list);
	
	//读取excel表格信息返回List
	List<Student> readExcel(String excel_path) throws IOException;
	
	//保存数据到数据库，返回成功导入的记录数
	int savaDataToDB(List<Student> list);
	
}
