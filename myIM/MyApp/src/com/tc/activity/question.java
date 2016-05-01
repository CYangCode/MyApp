package com.tc.activity;

import com.example.myapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
