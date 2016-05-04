package com.tc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dc.dao.ClassmatesDAO;
import com.dc.dao.ClassroomDAO;
import com.dc.dao.impl.ClassmatesDAOImpl;
import com.dc.dao.impl.ClassroomDAOImpl;
import com.tc.global.CIdToIps;
import com.tc.thread.MesRecvThread;

/**
 * Servlet implementation class TimeOutSevlet
 */
@WebServlet("/TimeOutServlet")
public class TimeOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassroomDAO clsrmDao;
	private ClassmatesDAO clsmtDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TimeOutServlet() {
		super();
		clsrmDao = new ClassroomDAOImpl();
		clsmtDao = new ClassmatesDAOImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String cId = request.getParameter("classroomid");
System.out.println("Timeout Servlet classroomid：" + cId);
		clsrmDao.delClassroomById(cId);
		clsmtDao.delClassmatesByClassrommId(cId);
		//发送教室结束的信息
		MesRecvThread.broadcast(cId, "##&&classroomtimeout&&##");
		//TODO 将全局容器中所有与这个cId对应的socket删除
		delSocketsByCid(cId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void delSocketsByCid(String cId) {
		synchronized (CIdToIps.RECV_MMAP) {
			CIdToIps.RECV_MMAP.removeAll(cId);
		}
	}

}
