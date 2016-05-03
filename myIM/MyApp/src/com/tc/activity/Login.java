package com.tc.activity;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapp.R;
import com.tc.service.LoginService;
import com.tc.service.UserInfoService;

public class Login extends Activity {
	private EditText mUser; // 帐号编辑
	private EditText mPassword; // 密码编辑

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		mUser = (EditText) findViewById(R.id.login_user_edit);
		mPassword = (EditText) findViewById(R.id.login_passwd_edit);
		mUser.setText(UserInfoService.get(this, "useraccount"));
		mPassword.setText(UserInfoService.get(this, "password"));

	}

	public void login_mainweixin(View v) {
		final String username = mUser.getText().toString();
		final String password = mPassword.getText().toString();
		new Thread() {
			public void run() {
				final String result = LoginService.loginByGet(username,
						password);
				if (result == null) {
					notice("登录失败", "连接错误！");
				} else {
					try {
						JSONObject jsonObj = new JSONObject(result);
						final String userAccount = jsonObj.getString("useraccount");
						final String username = jsonObj.getString("username");
						final String password = jsonObj.getString("password");
						final String position = jsonObj.getString("position");

						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Intent intent = new Intent();
								int times = UserInfoService.saveUserInfo(
										getApplicationContext(), userAccount,
										username, position, password);
								if (times == 0) {
									intent.setClass(Login.this,
											LoadingActivity.class);
								} else {
									intent.setClass(Login.this, MainWeixin.class);
								}
								startActivity(intent);
								finish();
							}
						});
					} catch (Exception e) {
						notice("登陆失败", "账号或密码错误！");
					}

				}
				
			};
		}.start();
	}

	private void notice(final String title, final String content) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				new AlertDialog.Builder(Login.this)
						.setIcon(
								getResources()
										.getDrawable(
												R.drawable.login_error_icon))
						.setTitle(title).setMessage(content)
						.create().show();
			}
		});
	}
	public void login_back(View v) { // 标题 返回按钮
		this.finish();
	}

	public void login_pw(View v) { // 忘记密码按钮

		/******************************************************/
		// Uri uri = Uri.parse("http://3g.qq.com");
		// Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		// startActivity(intent);
		/*****************************************************/

		// Intent intent = new Intent();
		// intent.setClass(Login.this,Whatsnew.class);
		// startActivity(intent);
	}
}
