package com.tc.csdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tc.vo.ClassroomUser;

public class CeShiData {

	public static List<ClassroomUser> getClassroomUsers() {
		List<ClassroomUser> classroomUsers = new ArrayList<ClassroomUser>();
		classroomUsers.add(new ClassroomUser("C语言", "这是一门神奇的C语言", new Date()));
		classroomUsers.add(new ClassroomUser("java程序设计", "这是一门神奇的java语言",new Date()));
		classroomUsers.add(new ClassroomUser("B语言", "这是一门神奇的B语言", new Date()));
		classroomUsers.add(new ClassroomUser("go语言", "这是一门神奇的go语言", new Date()));
		classroomUsers.add(new ClassroomUser("C#语言", "这是一门神奇的C#语言", new Date()));
		classroomUsers.add(new ClassroomUser("javascript语言","这是一门神奇的javascript语言", new Date()));
		classroomUsers.add(new ClassroomUser("易语言", "这是一门神奇的易语言",new Date()));
		classroomUsers.add(new ClassroomUser("Ruby语言", "这是一门神奇的Ruby语言",new Date()));
		classroomUsers.add(new ClassroomUser("object-c语言", "这是一门神奇的object-c语言",new Date()));
		classroomUsers.add(new ClassroomUser("C++语言", "这是一门神奇的C++语言",new Date()));

		return classroomUsers;
	}

}
