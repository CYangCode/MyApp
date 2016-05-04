package com.tc.thread;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tc.service.TimeOutSerivce;

public class TimeCheckThread extends Thread {
	private Date clsrmEndTime;// 教室关闭时间
	private String cId;// 教室的id

	@SuppressLint("SimpleDateFormat")
	public TimeCheckThread(String cId, String cEndTime) {
		this.cId = cId;
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			clsrmEndTime = formate.parse(cEndTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		super.run();
		Date date = new Date();
		while (clsrmEndTime.getTime() > date.getTime()) {
			// 教室结束时间在当前时间之后，不会关闭
			try {
				sleep(5000);
				date = new Date();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 到了教室结束时间
		TimeOutSerivce.sendByGet(cId);
	}

}
