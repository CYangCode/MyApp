package com.tc.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.tc.resource.ServerInfo;

public class TransmitService {
	public static Socket sendSocket = null;
	public static Socket recvSocket = null;
	static {
		try {
			sendSocket = new Socket(ServerInfo.SERVER_IP, ServerInfo.TCP_SEND_PORT);
			recvSocket = new Socket(ServerInfo.SERVER_IP, ServerInfo.TCP_RECV_PORT);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 信息发送，json串的格式"{'classroomid':'???','username':'???', 'message':'???'}"
	 * @param cId
	 * @param userAccount
	 * @param mes
	 * @param time
	 */
	public static void send(int cId, String userAccount, String mes, String time) {
		final String jsonStr = "{'classroomid':'" + cId + "','username':'" + userAccount
				+ "', 'message':'" + mes + "', 'time':'" + time + "'}";
		try {
			DataOutputStream os = new DataOutputStream(sendSocket.getOutputStream());
System.out.println(jsonStr);
			os.writeUTF(jsonStr);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 接受用的socket将自己的信息发到服务器端
	 * @param cId
	 */
	public static void send(int cId) {
		try {
			DataOutputStream os = new DataOutputStream(recvSocket.getOutputStream());
			os.writeUTF("" + cId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String resv() {
		DataInputStream dis;
		try {
			dis = new DataInputStream(recvSocket.getInputStream());
			return dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
