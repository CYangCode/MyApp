package com.tc.activity;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapp.R;
import com.tc.service.CreateClassroomService;

public class Createclassroom extends Activity {
	private EditText mClass; // 教室名称
	private EditText mClassdescribe; // 教室描述
	private TimePicker mEndTime;// 结束时间
	private EditText conClasstime;// 教室开启时间
	private String classroom = "教室名称实例";
	private String classdescribe = "教室简介实例";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createclassroom);

		mClass = (EditText) findViewById(R.id.register_user_edit);
		mClassdescribe = (EditText) findViewById(R.id.register_passwd_edit);
		mEndTime = (TimePicker) findViewById(R.id.end_time);
		conClasstime = (EditText) findViewById(R.id.register_confirm_passwd_edit);

	}

	/*
	 * private EditText mClass; // 教室名称 private EditText mClassdescribe; // 教室描述
	 * private TimePicker mEndTime;//结束时间 private EditText conClasstime;//
	 * 教室开启时间 private String classroom = "教室名称实例"; private String classdescribe
	 * = "教室简介实例";
	 */
	@SuppressLint("SimpleDateFormat")
	public void create_classroom(View v) { // 创建教室界面
		Intent intent = new Intent();
		intent.setClass(Createclassroom.this, MainWeixin.class);
		intent.putExtra("classname", mClass.getText().toString());
		intent.putExtra("classdescribe", mClassdescribe.getText().toString());
		startActivity(intent);
		SharedPreferences sp = getSharedPreferences("user_info",
				Context.MODE_PRIVATE);
		final String username = sp.getString("username", "");
		final String cName = mClass.getText().toString();
		final String cContent = mClassdescribe.getText().toString();
//		final String cBluetoothAddr = BluetoothAdapter.getDefaultAdapter()
//				.getAddress();
	
		final String cBluetoothAddr = "11:B0:A2:F4";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String cEndTime = sdf.format(new Date()) + " "
				+ mEndTime.getCurrentHour() + ":" + mEndTime.getCurrentMinute() + ":00";
		new Thread() {
			public void run() {
				String result = null;
				try {
					result = CreateClassroomService.createClassroomByGet(
							username, cName, cContent, cBluetoothAddr, cEndTime);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if ("create class success".equals(result)) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(Createclassroom.this, "创建成功",
									Toast.LENGTH_SHORT).show();
						}
					});

				} else if ("create class error".equals(result)) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							new AlertDialog.Builder(Createclassroom.this)
									.setIcon(
											getResources()
													.getDrawable(
															R.drawable.login_error_icon))
									.setTitle("创建失败").setMessage("创建失败，请重新尝试！")
									.create().show();
						}
					});
				} else if (result.isEmpty()){
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							new AlertDialog.Builder(Createclassroom.this)
									.setIcon(
											getResources()
													.getDrawable(
															R.drawable.login_error_icon))
									.setTitle("创建失败").setMessage("连接服务器失败！")
									.create().show();
						}
					});
				}
			};
		}.start();

		this.finish();
	}

	public void login_back(View v) { // 标题 返回按钮
		this.finish();
	}

}