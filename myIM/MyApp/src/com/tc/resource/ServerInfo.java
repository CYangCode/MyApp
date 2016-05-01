package com.tc.resource;


public class ServerInfo {
	public static String SERVER_IP = "49.140.58.25";
	public static int TCP_SEND_PORT = 6666;
	public static int TCP_RECV_PORT = 8888;
	public static int HTTP_PORT = 8080;
	
	public static String getHttpServerIpPort() {
		return SERVER_IP + ":" + HTTP_PORT;
	}
}
