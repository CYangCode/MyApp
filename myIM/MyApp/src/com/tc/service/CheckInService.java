package com.tc.service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.tc.resource.ServerInfo;
import com.tc.service.util.StreamTool;

public class CheckInService {
	public static String checkInByGet(int cId, String username, String stuNum, String row, String col) {
		//要发送的json字符串
		String jsonStr = null;
		try {
			jsonStr = "{'classroomid':'" + cId+ "','username':'" + URLEncoder.encode(URLEncoder.encode(username, "utf-8"), "utf-8") + "','studentnumber':'"
					+ stuNum + "','row':'" + row + "','col':'" + col + "'}";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = "http://" + ServerInfo.getHttpServerIpPort() + "/WebServer/CheckInServlet?message=" + jsonStr;
System.out.println(path);
		URL url = null;
		try {
			url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			
			int code = conn.getResponseCode();
			if (200 == code) {
				//请求成功
				InputStream is = conn.getInputStream();
				String text = StreamTool.readIntStream(is);
				return text;
			} else {
				//请求失败
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
}
