package com.ldh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ldh.model.Attendance;

public interface AttendanceDao {
	//查询所有考勤记录
	@Select("select id,attendance_type,course_name,attendance_time from attendance LIMIT ${offset},#{limit}")
	public List<Attendance> showAll(@Param("limit")int limit,@Param("offset")int offset);
	
	//查询总记录数
	@Select("select COUNT(*) from attendance")
	int selelctCount();
}
