package com.tc.service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.tc.resource.ServerInfo;
import com.tc.service.util.StreamTool;

public class CreateClassroomService {
	/**
	 * 
	 * @param username
	 * @param password
	 * @return 返回null登录异常， 返回string操作成功
	 * @throws UnsupportedEncodingException
	 */
	public static String createClassroomByGet(String username, String cName,
			String cContent, String cBluetoothAddr, String cEndTime)
			{
		// 提交数据到服务器
		String path = null;
		try {
			path = "http://" + ServerInfo.getHttpServerIpPort() + "/WebServer/CreateClassroomServlet?username="
					+ URLEncoder.encode(username, "utf-8")
					+ "&classroomname="
					+ URLEncoder.encode(URLEncoder.encode(cName, "utf-8"), "utf-8")// 服务端会自动decode一次所以要encode两次
					+ "&classroomcontent="
					+ URLEncoder.encode(URLEncoder.encode(cContent, "utf-8"),// 同理
							"utf-8")
					+ "&bluetoothaddr="
					+ URLEncoder.encode(cBluetoothAddr, "utf-8")
					+ "&classroomendtime=" + URLEncoder.encode(cEndTime, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		URL url = null;
		try {
			url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");

			int code = conn.getResponseCode();
			if (200 == code) {
				// 请求成功
				InputStream is = conn.getInputStream();
				String text = StreamTool.readIntStream(is);
				return text;
			} else {
				// 请求失败
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
