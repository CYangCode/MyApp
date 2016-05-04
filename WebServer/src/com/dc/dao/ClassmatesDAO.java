package com.dc.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.dc.bean.Classmate;

public interface ClassmatesDAO {
	public int addClassmate(Classmate classmate);
	
	public boolean createFile(String cId, String path);
	
	public ArrayList<HashMap<String, String>> findClassmatesByClassroomId(String id);
	
	public int delClassmatesByClassrommId(String cId);
}
