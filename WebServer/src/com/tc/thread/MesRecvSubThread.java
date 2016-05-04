package com.tc.thread;

import java.io.DataOutputStream;
import java.net.Socket;

import com.tc.global.CIdToIps;


class MesRecvSubThread extends Thread {
	private Socket socket;
	private String mes;
	private String cId;

	public MesRecvSubThread(Socket socket, String cId, String mes) {
		super();
		this.socket = socket;
		this.cId = cId;
		this.mes = mes;
	}

	@Override
	public void run() {
		super.run();
		DataOutputStream dos;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			System.out.println("sendMes：" + mes);
			dos.writeUTF(mes);
		} catch (Exception e) {
			System.out.println("Exception catched");
			// 如果发生异常则认为链接失效
			CIdToIps.RECV_MMAP.remove(cId, socket);
			// e.printStackTrace();
		}
	}
}