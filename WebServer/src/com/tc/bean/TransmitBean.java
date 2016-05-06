package com.tc.bean;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public 
class TransmitBean {
	private String cId;
	private String username;
	private String userAccount;
	private String mes;
	private String time;

	// 从json串中找到所有信息
	public TransmitBean(String jsonStr) throws JSONException {
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		cId = jsonObj.getString("classroomid");
		username = jsonObj.getString("username");
		userAccount = jsonObj.getString("useraccount");
		mes = jsonObj.getString("message");
		time = jsonObj.getString("time");
	}

	
	public String getcId() {
		return cId;
	}

	public String getUsername() {
		return username;
	}

	public String getMes() {
		return mes;
	}

	public String getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "TransmitBean [cId=" + cId + ", username=" + username + ", mes="
				+ mes + ", time=" + time + "]";
	}
	/**
	 * 将信息转换为服务器进行广播的字符串模式
	 * @return 需要发送的json字符串格式
	 */
	public String getSendModelJsonString() {
		String sendMes = "{'useraccount':'" + userAccount + "','username':'" + username + "','time':'" + time
				+ "', 'mes':'" + mes + "'}";
		return sendMes;
	}

}