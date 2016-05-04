package com.tc.thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import com.tc.bean.TransmitBean;
import com.tc.global.CIdToIps;

public class MesRecvThread extends Thread {
	private Socket socket;

	/**
	 * 
	 * @param s
	 *            服务器端截获的socket
	 */
	public MesRecvThread(Socket s) {
		this.socket = s;
	}

	@Override
	public void run() {
		super.run();
		// 接收到的socket进行监听
		DataInputStream is = null;
		try {
			is = new DataInputStream(socket.getInputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (true) {
			// 客户端发来的json格式的字符串
			String jsonStr = null;
			try {
				jsonStr = is.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
System.out.println(jsonStr);
			TransmitBean bean = new TransmitBean(jsonStr);
System.out.println(bean.toString());
			broadcast(bean.getcId(), bean.getSendModelJsonString());
		}
	}

	public static void broadcast(String cId, String mes) {
		// 对bean的 信息进行广播
		synchronized (CIdToIps.RECV_MMAP) {
System.out.println(CIdToIps.RECV_MMAP.toString());
			// 按照班级id获得所有需要广播的连接
			Collection<Socket> broacastSockets = CIdToIps.RECV_MMAP.get(cId);
System.out.println(broacastSockets);
			for (Socket socket : broacastSockets) {
				// 每个socket占用一个线程进行信息的发送
				new MesRecvSubThread(socket, cId, mes).start();
			}
		}
	}
}
