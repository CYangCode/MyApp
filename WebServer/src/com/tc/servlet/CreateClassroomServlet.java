package com.tc.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dc.dao.ClassroomDAO;
import com.dc.dao.UserDAO;
import com.dc.dao.impl.ClassroomDAOImpl;
import com.dc.dao.impl.UserDAOImpl;
import com.tc.bean.Classroom;
import com.tc.bean.User;

/**
 * Servlet implementation class CreateClassroomServlet
 */
@WebServlet("/CreateClassroomServlet")
public class CreateClassroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
	private ClassroomDAO clsrmDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClassroomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userDao  = new UserDAOImpl();
		clsrmDao = new ClassroomDAOImpl();
		String username = URLDecoder.decode(request.getParameter("username"), "utf-8");
		User user = userDao.findUserByAccount(username);
		
		String cNameUtf8 = request.getParameter("classroomname");
		System.out.println(cNameUtf8);
		String cName = URLDecoder.decode(cNameUtf8, "utf-8");
		String cContent = URLDecoder.decode(request.getParameter("classroomcontent"), "utf-8");
		String cBluetoothAddr = URLDecoder.decode(request.getParameter("bluetoothaddr"), "utf-8");
		String cEndTime = URLDecoder.decode(request.getParameter("classroomendtime"), "utf-8");
		System.out.println(cName);
		System.out.println(cContent);
		System.out.println(cBluetoothAddr);
		System.out.println(cEndTime);
		Classroom clsrm = new Classroom(user.getId(), cName, cContent, cBluetoothAddr, cEndTime);
System.out.println(clsrm);
		if (clsrmDao.addClassroom(clsrm) == 1) {
			System.out.println("1");
			//插入成功
			response.getOutputStream().write("create class success".getBytes());
		} else {
			System.out.println("0");
			response.getOutputStream().write("create class error".getBytes());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
