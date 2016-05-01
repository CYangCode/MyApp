package com.tc.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dc.dao.UserDAO;
import com.dc.dao.impl.UserDAOImpl;
import com.tc.bean.User;

/**
 * Servlet implementation class RegisteServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		userDao = new UserDAOImpl();
		String uAccount = request.getParameter("useraccount");
		String uName = URLDecoder.decode(request.getParameter("username"), "utf-8");
		String uPassword = request.getParameter("password");
		String uPosition = request.getParameter("position");
		System.out
				.println("user account: " + uAccount + "\nuser name: " + uName
						+ "\nuser password: " + uPassword + "\nuser position: "
						+ uPosition);
		User user = new User(uAccount, uPassword, uName, uPosition, "");

		if (userDao.addUser(user) == 1) {
			// 插入成功
			response.getOutputStream().write("register success".getBytes());
		} else {
			response.getOutputStream().write("register error".getBytes());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
