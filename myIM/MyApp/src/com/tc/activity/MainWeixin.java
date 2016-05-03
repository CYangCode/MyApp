package com.tc.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.myapp.R;
import com.tc.utl.bluetooth.BluetoothSearch;

public class MainWeixin extends Activity {

	public static MainWeixin instance = null;

	private ViewPager mTabPager;
	private ImageView mTabImg;// 动画图片
	private ImageView mTab1, mTab2, mTab3;
	private int zero = 0;// 动画图片偏移
	private int currIndex = 0;// 当前页卡编号
	private int one;// 单个水平动画位移
	private int two;
	private LinearLayout mClose;
	private LinearLayout mCloseBtn;
	private View layout;
	private boolean menu_display = false;
	private PopupWindow menuWindow;
	private LayoutInflater inflater;
	private LocalActivityManager manager = null;

	// private Button mRightBtn;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_weixin);
		// 启动activity时不自动弹出软键
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		instance = this;

		
		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);

		// viewpage的
		mTabPager = (ViewPager) findViewById(R.id.tabpager);
		mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());
		// 主页下面三个图片，分别是“教室”，“答疑解惑”，“个人中心”
		mTab1 = (ImageView) findViewById(R.id.img_classroom);
		mTab2 = (ImageView) findViewById(R.id.img_dayijiehuo);
		mTab3 = (ImageView) findViewById(R.id.img_settings);
		// 整体的框框
		mTabImg = (ImageView) findViewById(R.id.img_tab_now);

		// 给三个绑定监听器
		mTab1.setOnClickListener(new MyOnClickListener(0));
		mTab2.setOnClickListener(new MyOnClickListener(1));
		mTab3.setOnClickListener(new MyOnClickListener(2));
		
		// 获取屏幕当前分辨
		Display currDisplay = getWindowManager().getDefaultDisplay();
		int displayWidth = currDisplay.getWidth();
		one = displayWidth / 3; // 设置水平动画平移大小
		two = one * 2;

		// 将要分页显示的View装入数组mLi
		LayoutInflater mLi = LayoutInflater.from(this);
		View view1 = mLi.inflate(R.layout.main_tab_weixin, null);
		// View view2 = mLi.inflate(R.layout.main_tab_address, null);
		View view2 = mLi.inflate(R.layout.main_tab_address, null);
		View view3 = mLi.inflate(R.layout.main_tab_settings, null);



		// 每个页面的view数据
		final ArrayList<View> views = new ArrayList<View>();
		
/*************************************/

	views.add(getView("A", new Intent(MainWeixin.this,
				BluetoothSearch.class)));


//测试用代码
/* 初始化view1(教室页面)
	Intent intent=getIntent();
	 String string1=intent.getStringExtra("classname");
	 String string2=intent.getStringExtra("classdescribe");
	 if(string1==null&&string2==null)
	{
		 string1="教室名称示例";
		 string2="教室描述实例";
	}
 ListView classrooms = (ListView)view1.findViewById(android.R.id.list);
 List<ClassroomUser> list=CeShiData.getClassroomUsers();
 list.add(0, new ClassroomUser(string1, string2, new Date()));
 classrooms.setAdapter(new ClassroomListViewAdapter(this,list));
 views.add(view1);
//*************************************/
		views.add(view2);
		views.add(view3);
		// 填充ViewPager的数据适配器
		PagerAdapter mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(views.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};

		mTabPager.setAdapter(mPagerAdapter);
	}
	/**
	 * 图标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mTabPager.setCurrentItem(index);
		}
	};

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				mTab1.setImageDrawable(getResources().getDrawable(
						R.drawable.tab_weixin_pressed));
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_address_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_find_frd_normal));
				}
				break;

			case 1:
//				mTab3.setImageDrawable(getResources().getDrawable(
//						R.drawable.tab_find_frd_pressed));
				mTab2.setImageDrawable(getResources().getDrawable(
						R.drawable.tab_address_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, one, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_weixin_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
//					mTab2.setImageDrawable(getResources().getDrawable(
//							R.drawable.tab_address_normal));
					mTab3.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_find_frd_normal));
				}
				break;
			case 2:
				mTab3.setImageDrawable(getResources().getDrawable(
						R.drawable.tab_find_frd_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, two, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_weixin_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_address_normal));
				}
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(150);
			mTabImg.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { // 获取
																				// back
			if (menu_display) { // 如果 Menu已经打开 ，先关闭Menu
				menuWindow.dismiss();
				menu_display = false;
			} else {
				Intent intent = new Intent();
				intent.setClass(MainWeixin.this, Exit.class);
				startActivity(intent);
			}
		}

		else if (keyCode == KeyEvent.KEYCODE_MENU) { // 获取 Menu�?
			if (!menu_display) {
				// 获取LayoutInflater实例
				inflater = (LayoutInflater) this
						.getSystemService(LAYOUT_INFLATER_SERVICE);
				// 这里的main布局是在inflate中加入的哦，以前都是直接this.setContentView()的吧？呵�?
				// //该方法返回的是一个View的对象，是布�?��的根
				layout = inflater.inflate(R.layout.main_menu, null);

				// 下面我们要�?虑了，我怎样将我的layout加入到PopupWindow中呢？？？很�?��
				menuWindow = new PopupWindow(layout, LayoutParams.FILL_PARENT,
						LayoutParams.WRAP_CONTENT); // 后两个参数是width和height
				menuWindow.showAtLocation(this.findViewById(R.id.mainweixin),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
				// 如何获取我们main中的控件呢？也很�?��
				mClose = (LinearLayout) layout.findViewById(R.id.menu_close);
				mCloseBtn = (LinearLayout) layout
						.findViewById(R.id.menu_close_btn);

				// 下面对每�?��Layout进行单击事件的注册吧。�?�? //比如单击某个MenuItem的时候，他的背景色改�?
				// //事先准备好一些背景图片或者颜�?
				mCloseBtn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// Toast.makeText(Main.this, "�?��",
						// Toast.LENGTH_LONG).show();
						Intent intent = new Intent();
						intent.setClass(MainWeixin.this, Exit.class);
						startActivity(intent);
						menuWindow.dismiss(); // 响应点击事件之后关闭Menu
					}
				});
				menu_display = true;
			} else {
				// 如果当前已经为显示状态，则隐藏起�?
				menuWindow.dismiss();
				menu_display = false;
			}

			return false;
		}
		return false;
	}

	// 设置标题栏右侧按钮的作用
	public void btnmainright(View v) {
		Intent intent = new Intent(MainWeixin.this, MainTopRightDialog.class);
		startActivity(intent);
		// Toast.makeText(getApplicationContext(), "点击了功能按�?,
		// Toast.LENGTH_LONG).show();
	}
	 public void personalinfo(View v) {  //个人信息界面
	      	Intent intent = new Intent();
			intent.setClass(MainWeixin.this,Personalinfo.class);
			startActivity(intent);
			//this.finish();
	      } 
	 public void help(View v) {  //使用帮助界面
	      	Intent intent = new Intent();
			intent.setClass(MainWeixin.this,help.class);
			startActivity(intent);
			//this.finish();
	      } 
	 public void question(View v) {  //问题反馈界面
	      	Intent intent = new Intent();
			intent.setClass(MainWeixin.this,question.class);
			startActivity(intent);
			//this.finish();
	      } 
	 public void about(View v) {  //关于同窗界面
	      	Intent intent = new Intent();
			intent.setClass(MainWeixin.this,about.class);
			startActivity(intent);
			//this.finish();
	      } 
	 public void createclassroom(View v) {  //创建教室界面
	      	Intent intent = new Intent();
			intent.setClass(MainWeixin.this,CreateClassroom.class);
			startActivity(intent);
			//this.finish();
			/*
		 Intent intent = new Intent(MainWeixin.this, Createclassroom.class);
			startActivity(intent);*/
	      }
//	public void startchat(View v) { // 小黑 对话界面
//		Intent intent = new Intent(MainWeixin.this, ChatActivity.class);
//		startActivity(intent);
//		// Toast.makeText(getApplicationContext(), "登录成功",
//		// Toast.LENGTH_LONG).show();
//	}

	public void exit_settings(View v) { // �?�� 伪�?对话框�?，其实是�?��activity
		Intent intent = new Intent(MainWeixin.this, ExitFromSettings.class);
		startActivity(intent);
	}
	public void tongxunlu(View v) { // 通讯录
		Intent intent = new Intent(MainWeixin.this, MainActivity.class);
		startActivity(intent);
	}

	public void btn_shake(View v) { // 手机摇一�?
		Intent intent = new Intent(MainWeixin.this, ShakeActivity.class);
		startActivity(intent);
	}

	// 通过activity返回页面View
	private View getView(String id, Intent intent) {
		return manager.startActivity(id, intent).getDecorView();
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
		manager.dispatchDestroy(isFinishing());
	}

	@Override
	protected void onResume() {
		super.onResume();
		manager.dispatchResume();
	}
}
