package com.apple.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.apple.fragment.MainHomeFragment;
import com.apple.fragment.MainMyFragment;
import com.apple.fragment.MainSearchFragment;
import com.apple.fragment.MainTuanFragment;
import com.apple.personcomment.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * APP-首页入口 
 * @author qj
 *
 */
public class MainActivity extends FragmentActivity implements OnCheckedChangeListener {

	//声明首页底部radio并绑定
	@ViewInject(R.id.radio_main)
	private RadioGroup mainRadio;
	
	//声明首页底部RadioButton并绑定
	@ViewInject(R.id.radio_btn_home)
	private RadioButton mainRadioBtn;
	
	//声明双击退出变量
	private static long exitTime = 0;
	
	//管理fragment
	private FragmentManager fragmentManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//事件绑定相关内容绑定
		ViewUtils.inject(this);
 
		//初始化FragmentManager
		fragmentManager = getSupportFragmentManager();
		
		//设置默认选中
		mainRadioBtn.setChecked(true);
		//设置选择事件
		mainRadio.setOnCheckedChangeListener(this);
		
		//切换不同的fragment
		changeFragment(new MainHomeFragment(), false);
	}

	/* 
	 * 用户选中 radioButton 要做的动作
	 * @see android.widget.RadioGroup.OnCheckedChangeListener#onCheckedChanged(android.widget.RadioGroup, int)
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		switch (checkedId) {
		//首页
		case R.id.radio_btn_home: changeFragment(new MainHomeFragment(), true);
			break;
		//我的
		case R.id.radio_btn_home_my: changeFragment(new MainMyFragment(), true);
			break;
		//发现
		case R.id.radio_btn_home_search: changeFragment(new MainSearchFragment(), true);
			break;
		//团购·
		case R.id.radio_btn_home_tuan: changeFragment(new MainTuanFragment(), true);
			break;
		default:
			break;
		}
		 
	}	
	
	/**
	 * 在changeFragment中如何（出去）
	 * 用户不同的点击切换不同的  fragment 方法
	 * Fragment 需要开启事务  调用 beginTransaction()函数 来拿到fragmentTransaction对象;
	 * @param fragment
	 * @param isFirst
	 */
	public void changeFragment(Fragment fragment, boolean isFirst) {
		//开启事务  
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		//进行不同值得填充 与 更换
		transaction.replace(R.id.frame_main_container, fragment);
		
		//fragment 复用 拿到之前的页面
		if(!isFirst) {
			//添加回退栈 避免重影的效果
			transaction.addToBackStack(null);
		}		
		
		//提交
		transaction.commit();
	}

	
	/* 
	 * 用户双击退出
	 * (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onKeyDown(int, android.view.KeyEvent)
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			// 如果两次按键时间间隔大于2000毫秒，则不退出
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				// 用户提示
				Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
				// 从新赋值
				exitTime = System.currentTimeMillis();
				return true;
			} else {
				// 否则退出程序
				System.exit(0);
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
