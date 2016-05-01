package com.tc.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.annotation.SuppressLint;

import com.tc.resource.ServerInfo;
import com.tc.service.util.StreamTool;
import com.tc.vo.ClassroomUser;

public class BluetoothService {
	/**
	 * 通过蓝牙的信息从服务器中找到对应蓝牙信息的教室信息链表
	 * @param bluetoothAddrs 蓝牙的地址链表
	 * @return ClassrooUser的链表
	 */
	@SuppressLint("SimpleDateFormat")
	public static List<ClassroomUser> getClsrmsByBluetoothAddrs(
			List<String> bluetoothAddrs) {
		List<ClassroomUser> clsrmList = new ArrayList<ClassroomUser>();
		String path = "http://" + ServerInfo.getHttpServerIpPort() + "/WebServer/BluetoothServlet?bluetooth_addr=";
		try {
			for (String addr : bluetoothAddrs) {
				URL url = new URL(path + addr);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");

				int code = conn.getResponseCode();
				if (200 == code) {
					InputStream is = conn.getInputStream();
					String jsonStr = StreamTool.readIntStream(is);
					if (!jsonStr.isEmpty() && !jsonStr.equals("{}")) {
						JSONObject jsonObj = new JSONObject(jsonStr);
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						ClassroomUser user = new ClassroomUser(
								jsonObj.getInt("id"),
								jsonObj.getString("name"),
								jsonObj.getString("content"),
								formatter.parse(jsonObj.getString("endtime")));
						clsrmList.add(user);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} 
		return clsrmList;
	}
}
