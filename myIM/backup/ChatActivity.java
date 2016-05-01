package com.tc.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapp.R;

public class ChatActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	private Button mBtnSend;
	private Button mBtnBack;
	private EditText mEditTextContent;
	private ListView mListView;
	private Spinner spinner;
	private ChatMsgViewAdapter mAdapter;
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_xiaohei);
		// 启动activity时不自动弹出软键�?
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		spinner=(Spinner)this.findViewById(R.id.spinner);
		initView();
		
		initData();
		List<String> list=new ArrayList<String>();
		list.add("菜单");
		list.add("发起投票");
		list.add("发起签到");
		list.add("签到文件");
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner,R.id.text,list);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String str=arg0.getItemAtPosition(arg2).toString();
				if(str=="发起投票")
				{
					Intent intent=new Intent();
					intent.setClass(ChatActivity.this, Toupiao.class);
					startActivity(intent);
				}
				if(str=="发起签到")
				{
					Intent intent=new Intent();
					intent.setClass(ChatActivity.this, Qiandao.class);
					startActivity(intent);
				}
				if(str=="签到文件")
				{
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		} );
	}
//	class SpinnerOnItemSelectedListener implements OnItemSelectedListener{
//		
//			// TODO Auto-generated method stub
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				// TODO Auto-generated method stub
//				String select=arg0.getItemAtPosition(arg2).toString();
//				if(select.equals("发起签到"))
//				{
//					Intent intent=new Intent();
//					intent.setClass(ChatActivity.this, Qiandao.class);
//					startActivity(intent);
//				}
//				if(select.equals("发起投票"))
//				{
//					Intent intent=new Intent();
//					intent.setClass(ChatActivity.this, Toupiao.class);
//					startActivity(intent);
//				}
//			}

//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//	}
	
	

	public void initView() {
		mListView = (ListView) findViewById(R.id.listview);
		mBtnSend = (Button) findViewById(R.id.btn_send);
		mBtnSend.setOnClickListener(this);
		mBtnBack = (Button) findViewById(R.id.btn_back);
		mBtnBack.setOnClickListener(this);
		//spinner=(Spinner)findViewById(R.id.spinner);
		

		mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
	}

	private String[] msgArray = new String[] { "hi！逗比", "你才是逗比！", "我是大师兄派来的！", "你是猴子派来的？",
			"你知道Siri吗？那个自称阿姨的机器", "你不也是机器？逗比小冰，你知道Google Now吗", "我们各有特色，但伦家更接地气啦~", "尼滚....", };

	private String[] dataArray = new String[] { "2015-09-01 18:00",
			"2015-09-01 18:10", "2015-09-01 18:11", "2015-09-01 18:20",
			"2015-09-01 18:30", "2015-09-01 18:35", "2015-09-01 18:40",
			"2015-09-01 18:50" };
	private final static int COUNT = 8;

	public void initData() {
		for (int i = 0; i < COUNT; i++) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setDate(dataArray[i]);
			if (i % 2 == 0) {
				entity.setName("小冰");
				entity.setMsgType(true);
			} else {
				entity.setName("MM");
				entity.setMsgType(false);
			}

			entity.setText(msgArray[i]);
			mDataArrays.add(entity);
		}

		mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
		mListView.setAdapter(mAdapter);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_send:
			send();
			break;
		case R.id.btn_back:
			finish();
			break;
		}
	}

	private void send() {
		String contString = mEditTextContent.getText().toString();
		if (contString.length() > 0) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setDate(getDate());
			entity.setName("MM");
			entity.setMsgType(false);
			entity.setText(contString);

			mDataArrays.add(entity);
			mAdapter.notifyDataSetChanged();

			mEditTextContent.setText("");

			mListView.setSelection(mListView.getCount() - 1);
		}
	}

	private String getDate() {
		Calendar c = Calendar.getInstance();

		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH));
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mins = String.valueOf(c.get(Calendar.MINUTE));

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":"
				+ mins);

		return sbBuffer.toString();
	}

	public void head_xiaohei(View v) { // 标题�?返回按钮
		Intent intent = new Intent(ChatActivity.this, InfoXiaohei.class);
		startActivity(intent);
	}
//	public void qiandao(View v) { // 标题�?返回按钮
//		Intent intent = new Intent(ChatActivity.this, Qiandao.class);
//		startActivity(intent);
//	}
	}
