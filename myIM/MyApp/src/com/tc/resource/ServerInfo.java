package com.tc.resource;


public class ServerInfo {
	public static final String SERVER_IP = "10.151.138.20";
	public static final String FTP_USER_NAME = "anonymous";
	public static final String FTP_USER_PWD = "1234";
	public static final int TCP_SEND_PORT = 6666;
	public static final int TCP_RECV_PORT = 8888;
	public static final int HTTP_PORT = 8080;
	
	public static String getHttpServerIpPort() {
		return SERVER_IP + ":" + HTTP_PORT;
	}
	
	public static String getFtpUrl() {
		return "ftp://" + FTP_USER_NAME + ":" + FTP_USER_PWD + "@" + SERVER_IP + "/";
	}
}
