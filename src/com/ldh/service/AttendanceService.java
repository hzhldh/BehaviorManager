package com.ldh.service;

import java.util.List;

import com.ldh.model.Attendance;

public interface AttendanceService {
	//显示所以考勤记录
	public List<Attendance> showAllAttendance(int limit, int offset);
	
	//分页查询，查询记录总数
	int selectCount();
}
