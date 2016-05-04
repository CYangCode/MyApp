package com.tc.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.tc.resource.ServerInfo;
import com.tc.service.util.StreamTool;

public class TimeOutSerivce {
	/**
	 * 向服务器端发送删除该教室和教室中学生的指令
	 * @param cId 班级的id
	 * @return
	 */
	public static String sendByGet(String cId) {
		// 提交数据到服务器
		String path = "http://" + ServerInfo.getHttpServerIpPort() + "/WebServer/TimeOutServlet?classroomid=" + cId;
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
