package com.tc.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapp.R;
import com.tc.service.CreateClassroomService;
import com.tc.service.UserInfoService;
import com.tc.thread.TimeCheckThread;

public class CreateClassroom extends Activity {
	private EditText mClass; // 教室名称
	private EditText mClassdescribe; // 教室描述
	private TimePicker mEndTime;// 结束时间

	// private EditText conClasstime;// 教室开启时间
	// private String classroom = "教室名称实例";
	// private String classdescribe = "教室简介实例";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createclassroom);

		mClass = (EditText) findViewById(R.id.register_user_edit);
		mClassdescribe = (EditText) findViewById(R.id.register_passwd_edit);
		mEndTime = (TimePicker) findViewById(R.id.end_time);
		// conClasstime = (EditText)
		// findViewById(R.id.register_confirm_passwd_edit);

	}

	/*
	 * private EditText mClass; // 教室名称 private EditText mClassdescribe; // 教室描述
	 * private TimePicker mEndTime;//结束时间 private EditText conClasstime;//
	 * 教室开启时间 private String classroom = "教室名称实例"; private String classdescribe
	 * = "教室简介实例";
	 */
	@SuppressLint("SimpleDateFormat")
	public void create_classroom(View v) { // 创建教室界面
		final String username = UserInfoService.get(this, "useraccount");
		final String cName = mClass.getText().toString();
		final String cContent = mClassdescribe.getText().toString();
		 final String cBluetoothAddr = BluetoothAdapter.getDefaultAdapter()
		 .getAddress();

		//final String cBluetoothAddr = "11:B0:A2:F4";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String cEndTime = sdf.format(new Date()) + " "
				+ mEndTime.getCurrentHour() + ":" + mEndTime.getCurrentMinute()
				+ ":00";
		new Thread() {
			public void run() {
				final String result = CreateClassroomService
						.createClassroomByGet(username, cName, cContent,
								cBluetoothAddr, cEndTime);
				if (result.matches("[0-9]*")) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(CreateClassroom.this, "创建成功",
									Toast.LENGTH_SHORT).show();
						}
					});
					// TODO 创建一个线程检测实时检验当前时间是否超过了课程的结束时间，如果超过了发送关闭指令
					new TimeCheckThread(result, cEndTime).start();
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), MainWeixin.class);
					startActivity(intent);
					finish();
				} else if ("create class error".equals(result)) {
					notice("创建失败", "创建失败，您有可能已经创建了教室，请检查后重新尝试！");
				}
			};
		}.start();

	}

	private void notice(final String title, final String content) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				new AlertDialog.Builder(CreateClassroom.this)
						.setIcon(
								getResources().getDrawable(
										R.drawable.login_error_icon))
						.setTitle(title).setMessage(content).create().show();
			}
		});
	}

	public void login_back(View v) { // 标题 返回按钮
		this.finish();
	}

}