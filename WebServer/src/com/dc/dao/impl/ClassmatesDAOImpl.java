package com.dc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.dc.bean.Classmate;
import com.dc.dao.BaseDao;
import com.dc.dao.ClassmatesDAO;
import com.tc.util.DButil;

public class ClassmatesDAOImpl extends BaseDao implements ClassmatesDAO {

	@Override
	public int addClassmate(Classmate classmate) {
		Connection conn = DButil.getConnection();
		String sql = "INSERT INTO  `database`.`classmates` (`c_classroom_id`, `c_user_name`, `c_user_account`, `c_row`, `c_col`) VALUES ('"
				+ classmate.getcId()
				+ "', '"
				+ classmate.getUsername()
				+ "', '"
				+ classmate.getUserAccount()
				+ "', '"
				+ classmate.getRow() + "', '" + classmate.getCol() + "')";
		Statement st = null;
		ResultSet rs = null;
		int code = 0;
		try {
			st = conn.createStatement();
			code = st.executeUpdate(sql);
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
	public boolean createFile(String cId, String path) {
		Connection conn = DButil.getConnection();
		String sql = "SELECT c_user_name, c_user_account FROM `database`.classmates where c_classroom_id = "
				+ cId + " into outfile '" + path + "'";
		System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;
		boolean code = false;
		try {
			st = conn.createStatement();
			code = st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			DButil.close(rs, st, conn);
		}
		return code;
	}

	@Override
	public ArrayList<HashMap<String, String>> findClassmatesByClassroomId(
			String cId) {
		return executeQuery("SELECT c_user_name, c_user_account FROM `database`.classmates where c_classroom_id = "
				+ cId +" order by c_user_account");
	}

	public static void main(String[] args) {
		ClassmatesDAOImpl dao = new ClassmatesDAOImpl();
		dao.delClassmatesByClassrommId("27");
		ArrayList<HashMap<String, String>> result = dao.findClassmatesByClassroomId("24");
		System.out.println(result.toString());
//		new FileManageThread("27").start();
	}

	@Override
	public int delClassmatesByClassrommId(String string) {
		String sql = "DELETE FROM `database`.`classmates` WHERE `c_classroom_id`=" + string;
		return executeUpdate(sql);
	}
}
