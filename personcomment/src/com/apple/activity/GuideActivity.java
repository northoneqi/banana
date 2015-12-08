package com.apple.activity;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.apple.personcomment.R;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 引导页_Activity
 * @author dell
 * @date 2015/10/29
 * @version 1.0
 */

public class GuideActivity extends Activity {
	
	//声明点击进入首页
	@ViewInject(R.id.guide_btn)
	private Button guideBtn;
	
	//声明viewpager
	@ViewInject(R.id.guide_pager)
	private ViewPager guidePager;
	
	//声明list集合
	private List<View> listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_guide);
		
		com.lidroid.xutils.ViewUtils.inject(this);
		//初始化
		initViewPager();
		
		guidePager.addOnPageChangeListener(new OnPageChangeListener() {
			
			//也可被选中的方法
			@Override
			public void onPageSelected(int arg0) {
				//2：为第三个页面
				if(arg0 == 2){
					guideBtn.setVisibility(View.VISIBLE);
				}else{
					guideBtn.setVisibility(View.GONE);
				}
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
	}
	
	/**
	 * 点击进入首页
	 */
	@OnClick(R.id.guide_btn)
	private void toIndex(View view){
		startActivity(new Intent(getApplicationContext(), MainActivity.class));	
		
		finish();
	}
	
	/**
	 * 初始化view函数
	 */
	private void initViewPager() {
		listView = new ArrayList<View>();
		
		ImageView imgOne = new ImageView(this);
		imgOne.setImageResource(R.drawable.guide_01);
		listView.add(imgOne);
		
		ImageView imgTwo = new ImageView(this);
		imgTwo.setImageResource(R.drawable.guide_02);
		listView.add(imgTwo);
		
		ImageView imgThree = new ImageView(this);
		imgThree.setImageResource(R.drawable.guide_03);
		listView.add(imgThree);
		
		//添加item页卡
		guidePager.setAdapter(new MyViewPagerAdapter());
		
	}
	
	/**
	 * 定义ViewPager适配器
	 * @author qijing
	 * @version 1.0
	 * @date 2015-10-30
	 */
	class MyViewPagerAdapter extends PagerAdapter{
		
		//计算需要多少item显示
		@Override
		public int getCount() {
			return listView.size();
		}
		
		//object 与 view 对比
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		
		//初始化item实例方法
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(listView.get(position));
			return listView.get(position);
		}

		//item销毁的方法  ：重写了
		//我们销毁item方法并不是遵照我们系统之前给我们定义的：而使用我们自己定义的形式将我们view对象从我们viewpager中移除
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
//			super.destroyItem(container, position, object);
			container.removeView(listView.get(position));
		}
	}
}
