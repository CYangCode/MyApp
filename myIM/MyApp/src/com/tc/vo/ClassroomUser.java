package com.tc.vo;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClassroomUser {
	
	private String title = "";
	private String description="";
	private Date date = null;
	private int id;
	
	public ClassroomUser() {
		super();
	}
	public ClassroomUser(int id, String title, String description, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
	}
	//为了测试能好用
	public ClassroomUser(String title, String description, Date date) {
		super();
		this.title = title;
		this.description = description;
		this.date = date;
	}
	
	
	@SuppressLint("SimpleDateFormat")
	@Override
	public String toString() {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "ClassroomUser [ id=" + id + ", title=" + title + ", description=" + description
				+ ", date=" + formater.format(date) + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
