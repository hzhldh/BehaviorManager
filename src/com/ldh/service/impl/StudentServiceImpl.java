package com.ldh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldh.dao.StudentDao;
import com.ldh.model.Student;
import com.ldh.service.StudentService;

@Transactional
@Service("studentService")
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentDao studentDao;
	
	//分页查询-查所需数据
	@Transactional
	public List<Student> showAllStudents(int limit, int offset) {
		offset=(offset-1)*limit;
		return studentDao.selectAllStudent(limit, offset);
	}
	//分页查询-查询记录总数
	@Transactional
	public int selectCount() {
		return studentDao.selelctCount();
	}

	//带排序的分页查询-有BUG
	@Transactional
	public List<Student> showAllStudentsBySort(int limit, int offset,
			String sort, String order) {
		offset=(offset-1)*limit;
		return studentDao.selectAllStudentBySort(limit, offset, sort, order);
	}

	//根据Id删除学生记录
	@Transactional
	public boolean delStudent(int id) {
		return studentDao.delStudentById(id);		
	}
	
	//根据姓名模糊查询
	@Transactional
	public List<Student> selectStudentByName(String search) {
		return studentDao.selectStudentByName(search);
	}

	//增加学生
    @Transactional
	public boolean addStudent(Student student) {
		return studentDao.addStudent(student);
	}
    
    //更改学生信息
    @Transactional
	public boolean updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}

	
	
}
