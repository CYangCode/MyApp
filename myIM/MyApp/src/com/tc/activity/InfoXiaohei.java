package com.tc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapp.R;

public class InfoXiaohei extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_xiaohei);              
    }

   public void btn_back(View v) {     //标题�?返回按钮
      	this.finish();
      } 
   public void btn_back_send(View v) {     //标题�?返回按钮
     	this.finish();
     } 
   public void head_xiaohei(View v) {     //头像按钮
	   Intent intent = new Intent();
		intent.setClass(InfoXiaohei.this,InfoXiaoheiHead.class);
		startActivity(intent);
    } 
    
}
