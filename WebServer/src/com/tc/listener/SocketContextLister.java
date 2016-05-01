package com.tc.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.tc.thread.SocketThread;

/**
 * Application Lifecycle Listener implementation class TestContextLister
 *
 */
@WebListener
public class SocketContextLister implements ServletContextListener {

	private SocketThread socketThread;

	/**
	 * Default constructor.
	 */
	public SocketContextLister() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		if (socketThread != null && socketThread.isInterrupted()) {
			socketThread.closeServerSocket();
			socketThread.interrupt();
		}
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent e) {
		ServletContext servletContext = e.getServletContext();
		System.out.println("Server contextInitialized over");
		if (socketThread == null) {
			socketThread = new SocketThread(null, servletContext);
			socketThread.start();
		}
	}

}
