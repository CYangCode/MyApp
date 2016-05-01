   package com.tc.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContext;

public class SocketThread extends Thread {
	/**
	 * 用来接收信息的服务器socket
	 */
	private ServerSocket serverRecvSocket;
	/**
	 * 用来广播的服务器socket
	 */
	private ServerSocket serverSendSocket;

	public SocketThread(ServerSocket serverSocket, ServletContext servletContext) {
		if (serverSocket == null) {
			// server socket的初始化
			try {
				this.serverRecvSocket = new ServerSocket(6666);
				this.serverSendSocket = new ServerSocket(8888);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		// 启动两个线程对两个server socekt分别启动监听
		new Thread() {
			public void run() {
				//无限循环进行监听
				while (true) {
					Socket socket = null;
					try {
						socket = serverRecvSocket.accept();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new MesRecvThread(socket).start();
				}
			};
		}.start();
		try {
			while (true) {
				Socket socket = null;
				socket = serverSendSocket.accept();
				new MesSendThread(socket).start();
			}
		} catch (Exception ex) {
			System.out.println("SocketThread err:" + ex.getMessage());
		}
	}

	public void closeServerSocket() {
		try {
			if (serverRecvSocket != null && !serverRecvSocket.isClosed()) {
				serverRecvSocket.close();
			}

		} catch (Exception ex) {
			System.out.println("SocketThread err:" + ex.getMessage());
		}
	}

}
