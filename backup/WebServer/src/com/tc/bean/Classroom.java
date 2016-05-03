package com.tc.bean;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ����
 * @author �� γ
 *
 */
public class Classroom {
	private int id;
	/***
	 * �������û�id�� ��Ӧ��user��
	 */
	private int cUserId;
	/**
	 * ��������
	 */
	private String cName;
	/**
	 * ���Ҽ��
	 */
	private String cContent;
	/**
	 * �û���������ʹ���ֻ�������address
	 */
	private String cBluetoothAddr;
	/**
	 * �������ս�ɢʱ�� ��yyyy-MM-dd hh:mm:ss��
	 */
	private Timestamp cTimeEnd;

	public Classroom(){
		
	}

	public Classroom(int cUserId, String cName, String cContent,
			String cBluetoothAddr, String cTimeEnd) {
		super();
		this.cUserId = cUserId;
		this.cName = cName;
		this.cContent = cContent;
		this.cBluetoothAddr = cBluetoothAddr;
		this.cTimeEnd = Timestamp.valueOf(cTimeEnd);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getcUserId() {
		return cUserId;
	}

	public void setcUserId(int cUserId) {
		this.cUserId = cUserId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public String getcBluetoothAddr() {
		return cBluetoothAddr;
	}

	public void setcBluetoothAddr(String cBluetoothAddr) {
		this.cBluetoothAddr = cBluetoothAddr;
	}

	public Timestamp getcTimeEnd() {
		return cTimeEnd;
	}

	public void setcTimeEnd(Timestamp cTimeEnd) {
		this.cTimeEnd = cTimeEnd;
	}

	@Override
	public String toString() {
		return "Classroom [id=" + id + ", cUserId=" + cUserId + ", cName="
				+ cName + ", cContent=" + cContent + ", cBluetoothAddr="
				+ cBluetoothAddr + ", cTimeEnd=" + cTimeEnd + "]";
	}

	public String toJsonString() {
		Map<String, String> map = new HashMap<>();
		map.put("id", ""+id);
		map.put("userid", ""+cUserId);
		map.put("name", cName);
		map.put("content", cContent);
		map.put("bluetoothaddr", cBluetoothAddr);
		map.put("endtime", ""+cTimeEnd);
		JSONArray array = JSONArray.fromObject(map);
		String jsonStr = array.toString();
		return jsonStr.substring(1, jsonStr.length() - 1);
	}
	
	public static void main(String[] args) {
		Classroom classroom = new Classroom(1, "高数", "经信F4", "AC:F7:F3:D1:FF:95", "2016-04-23 23:58:00");
		String jsonStr = classroom.toJsonString();
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		String content = jsonObject.getString("content");
		System.out.println(content);
	}
	

}
