package com.tc.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapp.R;
/**
 * 开机画面
 * @author 北 纬
 *
 */
public class Appstart extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.appstart);
		
	new Handler().postDelayed(new Runnable(){
		@Override
		public void run(){
		//此处应有判断
			Intent intent = new Intent (Appstart.this,Welcome.class);			
			startActivity(intent);			
			Appstart.this.finish();
		}
	}, 800);
   }
}