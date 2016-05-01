package com.tc.service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.tc.resource.ServerInfo;
import com.tc.service.util.StreamTool;

public class RegisterService {
	public static String registerByGet(String userAccount, String username, String password, String position){
		// 提交数据到服务器
		String path = null;
		try {
			path = "http://" + ServerInfo.getHttpServerIpPort() + "/WebServer/RegisterServlet?useraccount="
					+ userAccount + "&username=" + URLEncoder.encode(URLEncoder.encode(username, "utf-8"), "utf-8") + "&password=" + password + "&position=" + position;
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		URL url = null;
		try {
			url = new URL(path);
System.out.println(url);
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
