package com.dc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dc.bean.Classmate;
import com.dc.dao.ClassmatesDAO;
import com.tc.util.DButil;

public class ClassmatesDAOImpl implements ClassmatesDAO{

	@Override
	public int addClassmate(Classmate classmate) {
		Connection conn = DButil.getConnection();
//INSERT INTO `database`.`classmates` (`c_classroom_id`, `c_user_name`, `c_user_account`, `c_row`, `c_col`) VALUES ('1', '1', '1', '1', '1');

		String sql = "INSERT INTO  `database`.`classmates` (`c_classroom_id`, `c_user_name`, `c_user_account`, `c_row`, `c_col`) VALUES ('"
				+ classmate.getcId()
				+ "', '"
				+ classmate.getUsername()
				+ "', '"
				+ classmate.getUserAccount()
				+ "', '"
				+ classmate.getRow()
				+ "', '"
				+ classmate.getCol() + "')";
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
	
	public static void main(String[] args) {
		Classmate classmate = new Classmate("2", "陈阳", "54130304", "1", "2");
		ClassmatesDAOImpl dao = new ClassmatesDAOImpl();
		dao.addClassmate(classmate);
	}

}
