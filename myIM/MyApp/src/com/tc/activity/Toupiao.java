package com.tc.activity;

import com.example.myapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Toupiao extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toupiao);
    }
	public void toupiao(View v) {  //使用帮助界面
		Intent intent=new Intent();
		intent.setClass(Toupiao.this, Qiandao.class);
		startActivity(intent);
         //this.finish();
      } 
	public void login_back(View v) { // 标题 返回按钮
		this.finish();
	}
}
