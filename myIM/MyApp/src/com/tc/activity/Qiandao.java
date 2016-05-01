package com.tc.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.R;
import com.tc.service.CheckInService;
import com.tc.service.UserInfoService;

public class Qiandao extends Activity {
	private EditText et_username;// 姓名
	private EditText et_student_number;// 账号，对应学号
	private EditText et_row;// 行
	private EditText et_col;// 列

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qiandao);
		et_username = (EditText) findViewById(R.id.et_username);
		et_student_number = (EditText) findViewById(R.id.et_student_number);
		et_row = (EditText) findViewById(R.id.et_row);
		et_col = (EditText) findViewById(R.id.et_col);

		et_username.setText(UserInfoService.get(this, "username"));
		et_student_number.setText(UserInfoService.get(this, "useraccount"));
	}

	private boolean validateInfo(int cId, String username, String stuNum, String row,
			String col) {
		if (cId == -1 || username.isEmpty() || stuNum.isEmpty() || row.isEmpty()
				|| col.isEmpty()) {
			return false;
		}
		return true;
	}

	private void notice(final String title, final String content) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				new AlertDialog.Builder(Qiandao.this)
				.setIcon(
						getResources().getDrawable(
								R.drawable.login_error_icon))
				.setTitle(title).setMessage(content).create()
				.show();
			}
		});
	}
	
	public void checkIn(View v) {
		final int cId = getIntent().getIntExtra("classroomid", -1);
		final String username = et_username.getText().toString();
		final String stuNum = et_student_number.getText().toString();
		final String row = et_row.getText().toString();
		final String col = et_col.getText().toString();
		if (validateInfo(cId, username, stuNum, row, col)) {
			new Thread() {
				public void run() {
					String result = CheckInService.checkInByGet(cId, username,
							stuNum, row, col);
					// TODO 对服务器端返回的信息进行验证，如果成功则弹出签到成功的提示，失败提示重新签到
					if ("check in success".equals(result)) {
						runOnUiThread(new  Runnable() {
							public void run() {
								Toast.makeText(getApplicationContext(), "签到成功", Toast.LENGTH_LONG).show();
							}
						});
						finish();
					} else if ("check in error".equals(result)) {
						notice("提示", "签到失败，请重新尝试！");
					} else if (null == result) {
						notice("提示", "签到失败，请检查网络");
					}
				};
			}.start();
		} else {
			notice("提示", "请检查信息的正确性，确保没有空缺的信息！");
		}
	}

	public void login_back(View v) { // 标题 返回按钮
		this.finish();
	}
}