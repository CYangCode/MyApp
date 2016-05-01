package com.tc.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
		SharedPreferences sp = getSharedPreferences("user_info",
				Context.MODE_PRIVATE);
		mUser.setText(sp.getString("username", ""));
		mPassword.setText(sp.getString("password", ""));

	}

	public void login_mainweixin(View v) {
		final String username = mUser.getText().toString();
		final String password = mPassword.getText().toString();
		new Thread() {
			public void run() {
				final String result = LoginService.loginByGet(username,
						password);
				try {
					JSONObject jsonObj = new JSONObject(result);
					final String userAccount = jsonObj.getString("useraccount");
					final String password = jsonObj.getString("password");
					final String position = jsonObj.getString("position");

					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Intent intent = new Intent();
							int times = UserInfoService.saveUserInfo(
									getApplicationContext(), userAccount,
									position, password);
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
				} catch (JSONException e) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							new AlertDialog.Builder(Login.this)
									.setIcon(
											getResources()
													.getDrawable(
															R.drawable.login_error_icon))
									.setTitle("登录失败").setMessage("连接失败！")
									.create().show();
						}
					});
				}

			};
		}.start();
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
