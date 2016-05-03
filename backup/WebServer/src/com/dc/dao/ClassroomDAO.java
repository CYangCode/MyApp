package com.dc.dao;

import java.util.List;

import com.tc.bean.Classroom;


public interface ClassroomDAO {
	/**
	 * ͨ���༶Id���Ұ༶
	 * @param id
	 * @return
	 */
	public  Classroom findClassroomById(int id);
	/**
	 * ͨ���༶ID�ҵ�����ѧ��
	 * @param classId
	 * @return
	 */
	public List<Integer> findStudentByClassId(int classId);
	
	public Classroom findClassroomByBluetooth(String bluetoothAddr);
	
	public int addClassroom(Classroom classroom);
}
