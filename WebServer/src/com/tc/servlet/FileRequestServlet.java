package com.tc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dc.dao.ClassmatesDAO;
import com.dc.dao.impl.ClassmatesDAOImpl;
import com.tc.util.FileTool;

/**
 * Servlet implementation class FileRequestServlet
 */
@WebServlet("/FileRequestServlet")
public class FileRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassmatesDAO dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileRequestServlet() {
		super();
		dao = new ClassmatesDAOImpl();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String cId = request.getParameter("classroomid");
		ArrayList<HashMap<String, String>> result = dao
				.findClassmatesByClassroomId(cId);
		if (result.isEmpty()) {
			//说明该教室已经关闭
			response.getOutputStream().write("success".getBytes());
			return;
		}
		String path = "d:\\test\\checkin" + cId + ".txt";
		System.out.println(path);
		try {
			FileTool.convertResultToFile(result, path);
		} catch (IOException e) {
			e.printStackTrace();
			response.getOutputStream().write("failed".getBytes());
			return;
		}
		response.getOutputStream().write("success".getBytes());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
