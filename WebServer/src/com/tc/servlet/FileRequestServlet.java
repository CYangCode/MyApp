package com.tc.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dc.dao.ClassmatesDAO;
import com.dc.dao.impl.ClassmatesDAOImpl;

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
		String path = "D:\\代码\\GitHub\\MyApp\\WebServer\\WebContent\\Download" + cId + ".txt";
System.out.println(path);
		try {
			convertResultToFile(result, path);
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

	/**
	 * 将查询的结果导入到指定目录下的文件中
	 * 
	 * @param result
	 * @param path
	 */
	private void convertResultToFile(ArrayList<HashMap<String, String>> result,
			String path) throws IOException {
		File file = new File(path);
		System.out.println(file.getPath());
		if (!file.exists())
			file.createNewFile();
		FileOutputStream out = null;
		out = new FileOutputStream(file, false);
		StringBuffer sb = new StringBuffer();
		for (HashMap<String, String> map : result) {
			Iterator<Entry<String, String>> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, String> entry = (Entry<String, String>) iter
						.next();
				String val = entry.getValue();
				sb.append(val + "\t");
			}
			sb.append("\n");
		}
		out.write(sb.toString().getBytes("utf-8"));
		out.close();
		System.out.println("file finished!");
	}
}
