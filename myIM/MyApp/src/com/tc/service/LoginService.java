package com.tc.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

import com.tc.resource.ServerInfo;
import com.tc.service.util.StreamTool;

public class LoginService {
	/**
	 * 
	 * @param username
	 * @param password
	 * @return 返回null登录异常， 返回string操作成功
	 */
	public static String loginByGet(String username, String password) {
		// 提交数据到服务器
		String path = "http://" + ServerInfo.getHttpServerIpPort() + "/WebServer/LoginServlet?username="
				+ username + "&password=" + password;
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
