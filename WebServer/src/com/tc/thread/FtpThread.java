package com.tc.thread;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

public class FtpThread extends Thread {
	private FtpServerFactory serverFactory;
	private FtpServer ftpServer;

	public FtpThread(ServletContext servletContext) {
		serverFactory = new FtpServerFactory();
		PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
		userManagerFactory.setFile(new File("ftp.properties"));
		serverFactory.setUserManager(userManagerFactory.createUserManager());
		ftpServer = serverFactory.createServer();
	}

	@Override
	public void run() {
		super.run();
		try {
			ftpServer.start();
		} catch (FtpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}

	public void closeServer() {
		// TODO Auto-generated method stub
		ftpServer.stop();
	}
}
