package com.tc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapp.R;
import com.tc.service.UserInfoService;

public class Personalinfo extends Activity {
	private TextView tv_username;
	private TextView tv_useraccount;
	private TextView tv_position;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personalinfo);
		tv_username = (TextView) findViewById(R.id.tv_username);
		tv_useraccount = (TextView) findViewById(R.id.tv_useraccount);
		tv_position = (TextView) findViewById(R.id.tv_position);
		tv_username.setText(UserInfoService.get(this, "username"));
		tv_useraccount.setText(UserInfoService.get(this, "useraccount"));
		String position = UserInfoService.get(this, "position").equals(
				"teacher") ? "老师" : "学生";
		tv_position.setText(position);
	}

	/**
	 * @param args
	 */
	public void btn_back(View v) { // 标题 返回按钮
		this.finish();
	}

}
