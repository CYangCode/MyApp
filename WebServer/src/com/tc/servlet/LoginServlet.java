
package com.tc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dc.dao.UserDAO;
import com.dc.dao.impl.UserDAOImpl;
import com.tc.bean.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		userDao = new UserDAOImpl();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username:" + username);
		System.out.println("password:" + password);
		User user = userDao.findUserByAccount(username);
		if (user != null && user.getuPassword().equals(password)) {
			//密码账户匹配
			response.getOutputStream().write(user.toJsonString().getBytes());
		} else {
			response.getOutputStream().write("login error".getBytes());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
