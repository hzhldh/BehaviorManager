package com.ldh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ldh.model.Student;

public interface StudentDao {
	//查询所有学生信息
	@Select("select id,name,classname,major,native_place from student LIMIT ${offset},#{limit}")
	List<Student> selectAllStudent(@Param("limit")int limit,@Param("offset")int offset);
	
	//查询总记录数
	@Select("select COUNT(*) from student")
	int selelctCount();
	
	//根据id删除学生信息
	@Delete("delete from student where id=#{id}")
	boolean delStudentById(long id);
	
	//根据学生姓名模糊查询
	@Select("SELECT id,name,classname,major,native_place from student where name LIKE '%${search}%'")
	List<Student> selectStudentByName(@Param("search")String search);
	
	//新增学生记录
	@Insert("insert into student(id,name,classname,major,native_place) values(#{id},#{name},#{classname},#{major},#{native_place})")
	boolean addStudent(Student student);
	
	//更改学生信息,根据id
	@Update("update student set name=#{name},classname=#{classname},major=#{major},native_place=#{native_place} where id=#{id}")
	boolean updateStudent(Student student);
	
	//从excel新增到数据库前查询是否重复id
	@Select("select * from student where id=#{id}")
	Student selectRepeatStudent(long id);
	//从excel表格中读取数据并新增到数据库
	@Insert("insert into student(id,name,classname,major,native_place) values(#{id},#{name},#{classname},#{major},#{native_place})")
	boolean addStudentFromExcel(Student student);
	
	
	
}
