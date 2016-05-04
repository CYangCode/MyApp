package com.dc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dc.dao.BaseDao;
import com.dc.dao.ClassroomDAO;
import com.tc.bean.Classroom;
import com.tc.util.DButil;

public class ClassroomDAOImpl extends BaseDao implements ClassroomDAO{

	@Override
	public Classroom findClassroomById(int id) {
		Classroom classroom = null;
		Connection conn = DButil.getConnection();

		String sql = "select id"
				+ ",c_user_id,c_name,"
				+ "c_content,c_bluetooth_addr,c_time_end from classrooms where id=?";

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				classroom = new Classroom();
				classroom.setId(rs.getInt("id"));
				classroom.setcBluetoothAddr(rs.getString("c_bluetooth_addr"));
				classroom.setcContent(rs.getString("c_content"));
				classroom.setcName(rs.getString("c_name"));
				classroom.setcTimeEnd(rs.getTimestamp("c_time_end"));
				classroom.setcUserId(rs.getInt("c_user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(rs, st, conn);
		}

		return classroom;
	}

	@Override
	public List<Integer> findStudentByClassId(int classId) {

		List<Integer> list = new ArrayList<Integer>();
		Connection conn = DButil.getConnection();

		String sql = "select c_user_id from classmates where c_classroom_id=?";

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, classId);
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("c_user_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButil.close(rs, st, conn);
		}

		return list;
	}

	@Override
	public int addClassroom(Classroom classroom) {
		Connection conn = DButil.getConnection();

		String sql = "INSERT INTO classrooms (`c_user_id`, `c_name`, `c_content`, `c_bluetooth_addr`, `c_time_end`) VALUES ('"
				+ classroom.getcUserId()
				+ "', '"
				+ classroom.getcName()
				+ "', '"
				+ classroom.getcContent()
				+ "', '"
				+ classroom.getcBluetoothAddr()
				+ "', '"
				+ classroom.getcTimeEnd() + "')";
		Statement st = null;
		ResultSet rs = null;
		int code = 0;
		try {
			st = conn.createStatement();
			code = st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			DButil.close(rs, st, conn);
		}
		return code;

	}

	@Override
	public Classroom findClassroomByBluetooth(String bluetoothAddr) {
		Classroom classroom = null;
		Connection conn = DButil.getConnection();

		String sql = "select id"
				+ ",c_user_id,c_name,"
				+ "c_content,c_bluetooth_addr,c_time_end from classrooms where c_bluetooth_addr=?";

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, bluetoothAddr);
			rs = st.executeQuery();
			if (rs.next()) {
				classroom = new Classroom();
				classroom.setId(rs.getInt("id"));
				classroom.setcUserId(rs.getInt("c_user_id"));
				classroom.setcName(rs.getString("c_name"));
				classroom.setcContent(rs.getString("c_content"));
				classroom.setcBluetoothAddr(bluetoothAddr);
				classroom.setcTimeEnd(rs.getTimestamp("c_time_end"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(rs, st, conn);
		}
		return classroom;
	}
//	public static void main(String[] args) {
//		ClassroomDAO dao = new ClassroomDAOImpl();
//	
//		Classroom clsrm = dao.findClassroomByBluetooth("AC:F7:F3:D1:FF:95");
//		if (clsrm != null)
//		System.out.println(clsrm.toString());
//	}

	@Override
	public int delClassroomById(String id) {
		String sql = "DELETE FROM `database`.`classrooms` WHERE `id`=" + id;
		return executeUpdate(sql);
	}
}
