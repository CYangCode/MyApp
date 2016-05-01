package com.tc.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class LoginClsrmService {
	//public static String loginClsmByGet(Context context, int classroomId) throws Exception {
		
//		String ip = null;
//		if (checkEnable(context)) {
//				ip = getLocalIpAddress(context);
//		} else {
//			throw new Exception("没有打开wifi");
//		}
//		String path = "http://" + ServerInfo.getHttpServerIpPort() + "/WebServer/LoginClsrmServlet?ip=" + ip + "&classroomid=" + classroomId;
//		SocketForOutput.send(path);
//		URL url = null;
//		try {
//			url = new URL(path);
//			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//			conn.setConnectTimeout(5000);
//			conn.setRequestMethod("GET");
//			
//			int code = conn.getResponseCode();
//			if (200 == code) {
//				//请求成功
//				InputStream is = conn.getInputStream();
//				String text = StreamTool.readIntStream(is);
//				return text;
//			} else {
//				//请求失败
//				return null;
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		return null;
//	}

	/**
	 * 检查网络是否可用
	 * 
	 * @param paramContext
	 * @return
	 */
	public static boolean checkEnable(Context paramContext) {
		NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext
				.getSystemService("connectivity")).getActiveNetworkInfo();
		if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
			return true;
		return false;
	}

	/**
	 * 将ip的整数形式转换成ip形式
	 * 
	 * @param ipInt
	 * @return
	 */
	public static String int2ip(int ipInt) {
		StringBuilder sb = new StringBuilder();
		sb.append(ipInt & 0xFF).append(".");
		sb.append((ipInt >> 8) & 0xFF).append(".");
		sb.append((ipInt >> 16) & 0xFF).append(".");
		sb.append((ipInt >> 24) & 0xFF);
		return sb.toString();
	}

	/**
	 * 获取当前ip地址
	 * 
	 * @param context
	 * @return
	 */
	public static String getLocalIpAddress(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int i = wifiInfo.getIpAddress();
		return int2ip(i);

		// return null;
	}
}
