package com.tc.thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.tc.global.CIdToIps;

public class MesSendThread extends Thread {
	private Socket socket;
	
	public MesSendThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			DataInputStream is = new DataInputStream(socket.getInputStream());
			//要受到一个classroom id
			String cId = is.readUTF();
			synchronized (CIdToIps.RECV_MMAP) {
				if (!CIdToIps.RECV_MMAP.containsValue(socket)) {
					//没有该连接的话，就将<cid, socket>这个entry放到全局容器中
					CIdToIps.RECV_MMAP.put(cId, socket);
				}
System.out.println(CIdToIps.RECV_MMAP);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
}
