package com.apple.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;

import com.apple.personcomment.R;
import com.apple.utils.SharedUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SplashActivity extends Activity {
	@ViewInject(R.id.buttonPanel)
	private Button loginBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index_splash);
		
		//第一种：实现handler实现延时发送一些消息然后去接收消息
		new Handler(new Handler.Callback() {
			
			//处理接收到消息时 执行handlerMessage发方法
			@Override
			public boolean handleMessage(Message msg) {
				
				//实现页面的跳转  ; 添加分之语句
				if(SharedUtils.getWelcomeBoolean(getBaseContext())){
					startActivity(new Intent(getBaseContext(), MainActivity.class));
				}else{
					startActivity(new Intent(getBaseContext(), GuideActivity.class));
					//保存记录
					SharedUtils.putWelcomBoolean(getBaseContext(), true);
				}
				
				finish();
				return false;
			}
		}).sendEmptyMessageDelayed(0, 1000); //表示延时三秒钟发送
		
		
		
		
		//第二种使用Java中的定时器进行处理
	/*	Timer timer = new Timer();
		timer.schedule(new task(), 2000);*/ //定时器延时执行任务的方法，定时器任务执行的对象task 其实是一条线程
		
	}
	
	/*class task extends TimerTask{

		@Override
		public void run() {
			//其实它的内部封装了内部线程对象
			startActivity(new Intent(getApplicationContext(), MainActivity.class));
			System.exit(0);
		}
		
	}*/

}
