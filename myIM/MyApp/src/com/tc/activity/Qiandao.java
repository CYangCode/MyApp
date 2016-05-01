package com.tc.activity;

import com.example.myapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Qiandao extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qiandao);
    }
	public void qiandao(View v) { 
		this.finish();
	}
	public void login_back(View v) { // 标题 返回按钮
		this.finish();
	}
}