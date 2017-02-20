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
	@Select("select id,name,age,classname from student order by id desc LIMIT ${offset},#{limit}")
	List<Student> selectAllStudent(@Param("limit")int limit,@Param("offset")int offset);
	
	//带排序查询所有学生信息
	@Select("select id,name,age,classname from student order by #{sort} #{order} LIMIT ${offset},#{limit}")
	List<Student> selectAllStudentBySort(@Param("limit")int limit,@Param("offset")int offset,@Param("sort")String sort,@Param("order")String order);
	
	//查询总记录数
	@Select("select COUNT(*) from student")
	int selelctCount();
	
	//根据id删除学生信息
	@Delete("delete from student where id=#{id}")
	boolean delStudentById(int id);
	
	//根据学生姓名模糊查询
	@Select("SELECT id,name,age,classname from student where name LIKE '%${search}%'")
	List<Student> selectStudentByName(@Param("search")String search);
	
	//新增学生记录
	@Insert("insert into student(name,age,classname) values(#{name},#{age},#{classname})")
	boolean addStudent(Student student);
	
	//更改学生信息,根据id
	@Update("update student set name=#{name},age=#{age},classname=#{classname} where id=#{id}")
	boolean updateStudent(Student student);
	
	
}
