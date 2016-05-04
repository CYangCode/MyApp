package com.tc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dc.dao.ClassroomDAO;
import com.dc.dao.impl.ClassroomDAOImpl;
import com.tc.bean.Classroom;

/**
 * Servlet implementation class BluetoothServlet
 */
@WebServlet("/BluetoothServlet")
public class BluetoothServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ClassroomDAO clsrmDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BluetoothServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clsrmDao = new ClassroomDAOImpl();
		//获得客户端的参数
		String bluetoothAddr = request.getParameter("bluetooth_addr");
		//通过蓝牙地址找到Classroom
		Classroom classroom = clsrmDao.findClassroomByBluetooth(bluetoothAddr);
		if (classroom != null) {
			String jsonStr = classroom.toJsonString();
			response.getOutputStream().write(jsonStr.getBytes());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
