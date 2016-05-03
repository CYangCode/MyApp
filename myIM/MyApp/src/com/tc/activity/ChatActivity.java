package com.tc.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapp.R;
import com.tc.resource.Instructions;
import com.tc.service.DownloadService;
import com.tc.service.FileRequestService;
import com.tc.service.TransmitService;
import com.tc.service.UserInfoService;

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
		// 启动activity时不自动弹出软键
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		initView();
		initSpinner();
		recvThreadStart();
	}

	private void initSpinner() {
		spinner = (Spinner) this.findViewById(R.id.spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		adapter.add("菜单");
		if (UserInfoService.get(this, "position").equals("teacher")) {
			adapter.add("发起投票");
			adapter.add("发起签到");
			adapter.add("签到文件");
		}
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String str = arg0.getItemAtPosition(arg2).toString();
				if (str.equals("发起投票")) {
					Intent intent = new Intent();
					intent.setClass(ChatActivity.this, Toupiao.class);
					startActivity(intent);
				}
				if (str.equals("发起签到")) {
					new Thread() {
						public void run() {
							TransmitService.send(
									getIntent().getIntExtra("classroomid", -1),
									UserInfoService.get(
											getApplicationContext(),
											"useraccount"),
									Instructions.CHECK_IN, "");
						};
					}.start();
				}
				if (str.equals("签到文件")) {
					new Thread() {
						public void run() {
							//TODO 使用service发送生成文件请求
							final int cId = getIntent().getIntExtra("classroomid", -1);
							String result = FileRequestService.requestByGet(cId);
							//TODO 从服务器端下载文件
							if ("success".equals(result)) {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(getApplicationContext(), "正在下载", Toast.LENGTH_SHORT).show();
										DownloadService.download(getApplicationContext(), cId);
									}
								});
							} else {
								notice("下载失败", "请尝试重新下载！");
							}
						};
					}.start();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
	}
	
	private void notice(final String title, final String content) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				new AlertDialog.Builder(ChatActivity.this)
						.setIcon(
								getResources().getDrawable(
										R.drawable.login_error_icon))
						.setTitle(title).setMessage(content).create().show();
			}
		});

	}
	private void recvThreadStart() {
		new Thread() {
			public void run() {
				String userAccount = UserInfoService.get(
						getApplicationContext(), "useraccount");
				while (true) {
					try {
						// 接收服务器端的信息
						String jsonStr = TransmitService.resv();
						ChatMsgEntity tempEntity = new ChatMsgEntity(jsonStr);
						final ChatMsgEntity entity = tempEntity;
						if (!entity.getAccount().equals(userAccount)) {
							showRecvMes(entity);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	private void showRecvMes(final ChatMsgEntity entity) {
		synchronized (mDataArrays) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (null != entity
							&& entity.getText().equals(Instructions.CHECK_IN)) {
						Intent intent = new Intent();
						int cId = getIntent().getIntExtra("classroomid", -1);
						intent.putExtra("classroomid", cId);
						intent.setClass(ChatActivity.this, Qiandao.class);
						startActivity(intent);
					} else if (null != entity) {
						mDataArrays.add(entity);
						mAdapter.notifyDataSetChanged();
						mListView.setSelection(mListView.getCount() - 1);
					}
				}
			});

		}
	}

	public void initView() {
		mListView = (ListView) findViewById(R.id.listview);
		mBtnSend = (Button) findViewById(R.id.btn_send);
		mBtnSend.setOnClickListener(this);
		mBtnBack = (Button) findViewById(R.id.btn_back);
		mBtnBack.setOnClickListener(this);
		// spinner=(Spinner)findViewById(R.id.spinner);

		mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
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
		final String contString = mEditTextContent.getText().toString();
		if (contString.length() > 0) {
			ChatMsgEntity entity = new ChatMsgEntity();
			final String time = getDate();
			entity.setDate(time);
			final String userAccount = UserInfoService.get(this, "useraccount");
			entity.setAccount(userAccount);
			entity.setMsgType(false);
			entity.setText(contString);
			int cId = getIntent().getIntExtra("classroomid", -1);
			TransmitService.send(cId, userAccount, contString, time);
			synchronized (mDataArrays) {

				mDataArrays.add(entity);
				mAdapter.notifyDataSetChanged();

				mEditTextContent.setText("");

				mListView.setSelection(mListView.getCount() - 1);
			}
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

	public void toupiao(View v) { // 标题�?返回按钮
		Intent intent = new Intent(ChatActivity.this, Toupiao.class);
		startActivity(intent);
	}
}
