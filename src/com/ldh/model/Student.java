package com.ldh.model;

public class Student {
	private long id;
	private String name;
	private String major;
	private String classname;
	private String native_place;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getNative_place() {
		return native_place;
	}
	public void setNative_place(String native_place) {
		this.native_place = native_place;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String name, String classname, String major,
			String native_place) {
		super();
		this.name = name;
		this.classname = classname;
		this.major = major;
		this.native_place = native_place;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", major=" + major
				+ ", classname=" + classname + ", native_place=" + native_place
				+ "]";
	}
	
	
	
	
	
}
