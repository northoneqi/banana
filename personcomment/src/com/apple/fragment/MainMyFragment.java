package com.apple.fragment;

import com.apple.personcomment.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 首页-（我的） fragment类
 * @author qj
 * @date 2015-11-05
 * @version 1.0
 */
public class MainMyFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		//布局加载器 将XML文件映射一个对象 ：根节点选择为null 
		View view = inflater.inflate(R.layout.fragment_main_my, null);
		
		return view;
	}
}
