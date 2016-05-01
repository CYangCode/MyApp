package com.tc.activity;

import com.example.myapp.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

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
