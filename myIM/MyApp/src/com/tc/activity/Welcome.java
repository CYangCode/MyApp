package com.tc.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapp.R;

public class Welcome extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }
    public void welcome_login(View v) {  
      	Intent intent = new Intent();
		intent.setClass(Welcome.this,Login.class);
		startActivity(intent);
      }  
    public void welcome_register(View v) {  
      	Intent intent = new Intent();
		intent.setClass(Welcome.this,Register.class);
		startActivity(intent);
      }  
   
}
