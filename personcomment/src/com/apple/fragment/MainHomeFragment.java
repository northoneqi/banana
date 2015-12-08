package com.apple.fragment;

import com.apple.activity.CityActivity;
import com.apple.personcomment.R;
import com.apple.utils.SharedUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 首页-（index） fragment类
 * 
 * @author qj
 * @date 2015-11-05
 * @version 1.0
 */
public class MainHomeFragment extends Fragment {

	// 声明TextView
	@ViewInject(R.id.text_index_title_city)
	private TextView cityText;

	// 声明当前城市名称
	private String cityName;

	// 声明GPS管理类对象
	//private LocationManager locationManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 布局加载器 将XML文件映射一个对象 ：根节点选择为null
		View view = inflater.inflate(R.layout.fragment_main_home, null);
		// 注入
		ViewUtils.inject(this, view);
		// 获取之前数据并显示
		cityText.setText(SharedUtils.getLocationCityName(getActivity()));

		return view;
	}
	
	/**
	 * 绑定OnClick事件
	 * @param view
	 * @date 2015-11-09
	 */
	@OnClick(R.id.text_index_title_city)
	public void toOnClickCity(View view) {
		Log.i("onclick--------", view.getId()+"----fdfdffrferrferfewrrfewew");

		switch (view.getId()) {
		case R.id.text_index_title_city:
			startActivityForResult(new Intent(getActivity(), CityActivity.class), 2);
			
			break;
		default:
			break;
		}
	}

	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 2 && resultCode == Activity.RESULT_OK) {
			cityName = data.getStringExtra("cityName");
			cityText.setText(cityName);
		}
		
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
