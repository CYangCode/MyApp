package com.tc.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.tc.thread.FtpThread;

/**
 * Application Lifecycle Listener implementation class FtpContextListener
 *
 */
@WebListener
public class FtpContextListener implements ServletContextListener {


	private FtpThread ftpThread;
    /**
     * Default constructor. 
     */
    public FtpContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
		if (ftpThread != null && ftpThread.isInterrupted()) {
			ftpThread.closeServer();
			ftpThread.interrupt();
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent e)  { 
    	ServletContext servletContext = e.getServletContext();
		System.out.println("Server contextInitialized over");
		if (ftpThread == null) {
			ftpThread = new FtpThread(servletContext);
			ftpThread.start();
		}
    }
	
}
