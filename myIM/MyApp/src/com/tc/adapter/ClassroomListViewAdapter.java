package com.tc.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapp.R;
import com.tc.activity.ChatActivity;
import com.tc.service.TransmitService;
import com.tc.vo.ClassroomUser;

public class ClassroomListViewAdapter extends BaseAdapter {

	private List<ClassroomUser> list = null;
	private Context mContext = null;

	public ClassroomListViewAdapter(Context context,
			List<ClassroomUser> ClassroomUsers) {
		mContext = context;
		list = ClassroomUsers;
	}

	public void setMarkerData(List<ClassroomUser> ClassroomUsers) {
		list = ClassroomUsers;
	}

	@Override
	public int getCount() {
		int count = 0;
		if (null != list) {
			count = list.size();
		}
		return count;
	}

	@Override
	public ClassroomUser getItem(int position) {
		ClassroomUser item = null;

		if (null != list) {
			item = list.get(position);

		}

		return item;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		final int thePosition = position;
		if (null == convertView) {
			viewHolder = new ViewHolder();
			LayoutInflater mInflater = LayoutInflater.from(mContext);
			convertView = mInflater.inflate(R.layout.classroom_ceil, null);
			// //
			convertView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(mContext, ChatActivity.class);
					intent.putExtra("classroomid", list.get(thePosition)
							.getId());
					//发送空包，告知服务器客户端已连接
					new Thread() {
						public void run() {
							TransmitService.send(list.get(thePosition).getId());
						};
					}.start();
					mContext.startActivity(intent);
				}
			});
			// //
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			viewHolder.description = (TextView) convertView
					.findViewById(R.id.description);
			viewHolder.date = (TextView) convertView.findViewById(R.id.date);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		// set item values to the viewHolder:

		ClassroomUser classroomUser = getItem(position);
		if (null != classroomUser) {
			viewHolder.title.setText(classroomUser.getTitle());
			viewHolder.description.setText(classroomUser.getDescription());
			viewHolder.date.setText(new SimpleDateFormat("yyyy-MM-dd")
					.format(classroomUser.getDate()));
		}

		return convertView;
	}

	private static class ViewHolder {
		TextView title;
		TextView description;
		TextView date;
	}

}