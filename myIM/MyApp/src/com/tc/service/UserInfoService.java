package com.tc.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserInfoService {
	/**
	 * 
	 * @param context
	 * @param userName
	 * @param password
	 * @param password2 
	 * @return 登陆次数
	 */
	public static int saveUserInfo(Context context, String userAccount, String userName, String position, String password) {
		SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
		//获得登陆次数
		int times = 0;
		if (!sp.getString("useraccount", "").equals(userAccount)) {
			//现在登陆的之前不是一个人
			times = -1;
		} else {
			times = sp.getInt("logintimes", -1);
		}
		Editor editor = sp.edit();
		editor.putString("username", userName);
		editor.putString("useraccount", userAccount);
		editor.putString("position", position);
		editor.putString("password", password);
		//第一次登陆times为0
		editor.putInt("logintimes", (++times));
		editor.commit();
		return times;
	}
	/**
	 * 通过字段获得信息
	 * @param context 上下文
	 * @param param 想要获取的参数
	 * @return 正确，获得param对应的内容，失败得到""
	 */
	public static String get(Context context, String param) {
		SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
		return sp.getString(param, "");
	}
}
