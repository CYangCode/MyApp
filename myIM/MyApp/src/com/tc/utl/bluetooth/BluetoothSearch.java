package com.tc.utl.bluetooth;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.example.myapp.R;
import com.tc.activity.CreateClassroom;
import com.tc.adapter.ClassroomListViewAdapter;
import com.tc.service.BluetoothService;
import com.tc.service.UserInfoService;
import com.tc.vo.ClassroomUser;

public class BluetoothSearch extends ListActivity {
	private Handler _handler = new Handler();
	private List<ClassroomUser> classroomUsers = new ArrayList<ClassroomUser>();
	/* 取得默认的蓝牙适配器 */
	private BluetoothAdapter _bluetooth = BluetoothAdapter.getDefaultAdapter();
	/* 用来存储搜索到的蓝牙设备 */
	private List<BluetoothDevice> _devices = new ArrayList<BluetoothDevice>();
	/* 是否完成搜索 */
	private volatile boolean _discoveryFinished;
	private Runnable _discoveryWorkder = new Runnable() {
		public void run() {
			/* 开始搜索 */
			_bluetooth.startDiscovery();
			for (;;) {
				if (_discoveryFinished) {
					break;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}
	};
	/**
	 * 接收器 当搜索蓝牙设备完成时调用
	 */
	private BroadcastReceiver _foundReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			/* 从intent中取得搜索结果数据 */
			BluetoothDevice device = intent
					.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			/* 将结果添加到列表中 */
			synchronized (_devices) {
				_devices.add(device);
			}
			/* 显示列表 */

		}
	};
	private BroadcastReceiver _discoveryReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			/* 卸载注册的接收器 */
			unregisterReceiver(_foundReceiver);
			unregisterReceiver(this);
			_discoveryFinished = true;
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		setContentView(R.layout.main_tab_weixin);
		/* 如果蓝牙适配器没有打开，则打开 */
		if (!_bluetooth.isEnabled()) {
			_bluetooth.enable();
		}
		/* 注册接收器 */
		IntentFilter discoveryFilter = new IntentFilter(
				BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		registerReceiver(_discoveryReceiver, discoveryFilter);
		IntentFilter foundFilter = new IntentFilter(
				BluetoothDevice.ACTION_FOUND);
		registerReceiver(_foundReceiver, foundFilter);
		/* 显示一个对话框,正在搜索蓝牙设备 */
		SamplesUtils.indeterminate(BluetoothSearch.this, _handler,
				"Scanning...", _discoveryWorkder, new OnDismissListener() {
					public void onDismiss(DialogInterface dialog) {

						for (; _bluetooth.isDiscovering();) {

							_bluetooth.cancelDiscovery();
						}

						_discoveryFinished = true;
						showDevices();
					}
				}, true);

	}

	protected void showDevices() {
		final List<String> bluetoothAddrs = new ArrayList<String>();
		// 因为自己建立的教室自己也要看到
		synchronized (_devices) {
			bluetoothAddrs.add(BluetoothAdapter.getDefaultAdapter().getAddress());
			for (int i = 0, size = _devices.size(); i < size; ++i) {
				BluetoothDevice d = _devices.get(i);
				bluetoothAddrs.add(d.getAddress());
			}
		}
		
		new Thread() {
			public void run() {
					classroomUsers = BluetoothService
							.getClsrmsByBluetoothAddrs(bluetoothAddrs);
					
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						setListAdapter(new ClassroomListViewAdapter(
								BluetoothSearch.this, classroomUsers));
					}
				});
			};
		}.start();
	}

	/**
	 * 打开教室界面
	 * 
	 * @param v
	 */
	public void createclassroom(View v) { // 创建教室界面
		String pos = UserInfoService.get(this, "position");
		if ("teacher".equals(pos)) {
			//学生没有创建教室的权限
			Intent intent = new Intent(BluetoothSearch.this, CreateClassroom.class);
			startActivity(intent);
		} else {
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					new AlertDialog.Builder(BluetoothSearch.this)
					.setIcon(
							getResources()
									.getDrawable(
											R.drawable.login_error_icon))
					.setTitle("提示").setMessage("学生无法创建教室！")
					.create().show();					
				}
			});
		}
	}

}
