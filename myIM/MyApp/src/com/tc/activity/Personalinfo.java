package com.tc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myapp.R;

public class Personalinfo extends Activity {
	private LinearLayout layout;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalinfo);
    }

	/**
	 * @param args
	 */
	public void btn_back(View v) { // 标题 返回按钮
		this.finish();
	} 

}
