package com.ldh.service;

import java.util.List;

import com.ldh.model.Student;

public interface StudentService {
	//显示所有学生信息
	List<Student> showAllStudents(int limit, int offset);
	
	//分页查询，查询记录总数
	int selectCount();
	
	//带排序和分页显示信息
	List<Student> showAllStudentsBySort(int limit, int offset,String sort,String order);
	
	//根据Id删除学生记录
	boolean delStudent(int id);
	
	//模糊查询
	List<Student> selectStudentByName(String search);
	
	//增加学生
	boolean addStudent(Student student);
	
	//更改学生信息，根据id
	boolean updateStudent(Student student);
}
