package com.tc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapp.R;

public class about extends Activity {
	   private LinearLayout layout;
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.about);
	        layout=(LinearLayout)findViewById(R.id.about_layout);
			layout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！", 
							Toast.LENGTH_SHORT).show();	
				}
			});
	    }
	 public boolean onTouchEvent(MotionEvent event){
			finish();
			return true;
		}
		
		public void exitbutton0(View v) {  
	    	this.finish();    	
	      }  

}
