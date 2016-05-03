package com.tc.service;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

public class DownloadService {
	
	public static void download(Context context, int cId) {
		DownloadManager manager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
		// 创建下载请求
		DownloadManager.Request down = new DownloadManager.Request(
				Uri.parse("http://49.140.58.25:8080/WebServer/Download/checkin" + cId + ".txt"));
		// 设置允许使用的网络类型，这里是移动网络和wifi都可以
		down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
				| DownloadManager.Request.NETWORK_WIFI);
		// 显示下载界面
		down.setVisibleInDownloadsUi(true);
		// 设置下载后文件存放的位置
		down.setDestinationInExternalFilesDir(context, null, "checkin.txt");
		// 将下载请求放入队列
		manager.enqueue(down);
	}
}
