package com.tc.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapp.R;
import com.tc.service.RegisterService;
import com.tc.service.UserInfoService;

public class Register extends Activity {
	private EditText et_account; // 帐号编辑
	private EditText et_name; // 用户名
	private EditText et_password; // 密码编辑
	private EditText et_conpassword;// 确认密码
	private RadioButton rb_student;// 学生单选按钮

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		et_account = (EditText) findViewById(R.id.register_user_edit);
		et_name = (EditText) findViewById(R.id.register_name);
		et_password = (EditText) findViewById(R.id.register_passwd_edit);
		et_conpassword = (EditText) findViewById(R.id.register_confirm_passwd_edit);
		rb_student = (RadioButton) findViewById(R.id.student);
	}

	private boolean validateRegisterInfo() {
		if (et_account.getText().length() == 0
				|| et_account.getText().toString().matches("\\s{1,}")) {
			showErrorMes("注册失败", "账号为空，\n请检查后重新输入！");
			return false;
		} else if (!et_password.getText().toString()
				.equals(et_conpassword.getText().toString())) {
			showErrorMes("注册失败", "两次密码不一致，\n请检查后重新输入！");
			return false;
		} else if (et_password.getText().length() == 0
				|| et_conpassword.getText().length() == 0) {
			showErrorMes("注册失败", "密码为空，\n请检查后重新输入！");
			return false;
		} else if (et_name.getText().length() == 0
				|| et_name.getText().toString().matches("\\s{1,}")) {
			showErrorMes("注册失败", "姓名为空，\n请检查后重新输入！");
			return false;
		}
		return true;
	}

	private void showErrorMes(final String title, final String content) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				new AlertDialog.Builder(Register.this)
						.setIcon(
								getResources().getDrawable(
										R.drawable.login_error_icon))
						.setTitle(title).setMessage(content).create().show();
			}
		});

	}

	public void login_mainweixin(View v) {
		if (validateRegisterInfo()) {
			final String userAccount = et_account.getText().toString();
			final String username = et_name.getText().toString();
			final String password = et_password.getText().toString();
			final String position = rb_student.isChecked() ? "student" : "teacher";

			new Thread() {
				public void run() {
					String result = RegisterService.registerByGet(userAccount,
							username, password, position);
					if ("register success".equals(result)) {
						// 获得登录次数的信息
						int times = UserInfoService.saveUserInfo(Register.this,
								userAccount, username, position, password);
						Intent intent = new Intent();
						if (times == 0) {
							intent.setClass(Register.this, LoadingActivity.class);
						} else {
							intent.setClass(Register.this, MainWeixin.class);
						}
						startActivity(intent);
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								Toast.makeText(getApplicationContext(), "注册成功",
										Toast.LENGTH_SHORT).show();
							}
						});
						finish();
					} else if ("register error".equals(result)) {
						showErrorMes("注册失败", "注册失败，请尝试其他账号！");
					} else if (result == null) {
						showErrorMes("注册失败", "连接失败！");
					}
				};
			}.start();
			
		}

	}

	public void login_back(View v) { // 标题 返回按钮
		this.finish();
	}

}
