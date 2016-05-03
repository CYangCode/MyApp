package com.tc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.myapp.R;

public class question extends Activity{

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
    }
	 public void main_setting(View v) {  
			this.finish();
	 }
	public void login_back(View v) { // 标题 返回按钮
		this.finish();
	}
}
