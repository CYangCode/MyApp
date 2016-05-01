package com.tc.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.dc.bean.Classmate;
import com.dc.dao.ClassmatesDAO;
import com.dc.dao.impl.ClassmatesDAOImpl;

/**
 * Servlet implementation class CheckInServlet
 */
@WebServlet("/CheckInServlet")
public class CheckInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ClassmatesDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = new ClassmatesDAOImpl();
		String jsonStr = request.getParameter("message");
System.out.println(URLDecoder.decode(jsonStr, "utf-8"));
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		String cId = jsonObj.getString("classroomid");
		String username = URLDecoder.decode(jsonObj.getString("username"), "utf-8");
		String stuNum = jsonObj.getString("studentnumber");
		String row = jsonObj.getString("row");
		String col = jsonObj.getString("col");
		int code = dao.addClassmate(new Classmate(cId, username, stuNum, row, col));
		if (code > 0) {
			response.getOutputStream().write("check in success".getBytes());
		} else {
			response.getOutputStream().write("check in error".getBytes());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
