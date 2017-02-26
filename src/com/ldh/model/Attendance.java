package com.ldh.model;


public class Attendance {
	private int attendance_id;//考勤记录流水号
	private long id;//学号
	private String attendance_type;//考勤类型
	private String course_name;//考勤的课程名称
	private String attendance_time;//考勤日期
	public int getAttendance_id() {
		return attendance_id;
	}
	public void setAttendance_id(int attendance_id) {
		this.attendance_id = attendance_id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAttendance_type() {
		return attendance_type;
	}
	public void setAttendance_type(String attendance_type) {
		this.attendance_type = attendance_type;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getAttendance_time() {
		return attendance_time;
	}
	public void setAttendance_time(String attendance_time) {
		this.attendance_time = attendance_time;
	}
	public Attendance() {
		super();
	}
	public Attendance(long id, String attendance_type, String course_name,
			String attendance_time) {
		super();
		this.id = id;
		this.attendance_type = attendance_type;
		this.course_name = course_name;
		this.attendance_time = attendance_time;
	}
	
}
