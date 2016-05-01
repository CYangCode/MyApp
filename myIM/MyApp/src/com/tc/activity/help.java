package com.tc.activity;

import com.example.myapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class help extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
    }
	public void login_back(View v) { // 标题 返回按钮
		this.finish();
	}
}
