package com.ldh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldh.dao.AttendanceDao;
import com.ldh.model.Attendance;
import com.ldh.service.AttendanceService;

@Transactional
@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService{
	@Autowired
	private AttendanceDao attendanceDao;
	
	@Transactional
	public List<Attendance> showAllAttendance(int limit, int offset) {
		offset=(offset-1)*limit;
		return attendanceDao.showAll(limit, offset);
	}
	
	//分页查询-查询记录总数
	@Transactional
	public int selectCount() {
		return attendanceDao.selelctCount();
	}

}
