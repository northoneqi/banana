package com.apple.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apple.entity.City;
import com.apple.personcomment.R;
import com.apple.service.CityService;
import com.apple.service.impl.CityServiceImpl;
import com.apple.utils.CallbackListener;
import com.apple.view.SiderBar;
import com.apple.view.SiderBar.OnTouchingChangedListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

/**
 * 城市列表 - Activity类
 * @author qj
 * @date 2015-11-09
 * @version 1.0
 */
public class CityActivity extends Activity implements OnTouchingChangedListener {

	private CityService cityService;
	public CityActivity() {
		super();
		this.cityService = new CityServiceImpl(getBaseContext());
	}
	//声明ListView
	@ViewInject(R.id.list_city)
	private ListView listCityData ;
	
	//声明集合
	private List<City> listCity;
	//选择城市text
	private TextView textView;
	//定义
	@ViewInject(R.id.view_siderbar_city)
	private SiderBar siderbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index_city);
		//注入
		ViewUtils.inject(this);
		View view = LayoutInflater.from(this).inflate(R.layout.activity_city_search, null);
		
		listCityData.addHeaderView(view);
		//设置Item之间-（间隙）
		listCityData.setDividerHeight(1);  
		//执行异步任务
		cityService.findListCity(new CallbackListener<List<City>>() {

			@Override
			public void putOnSuccess(List<City> data) {
				if (data != null) {
					listCity = data;
					Log.i("onsuccess-->", listCity.size()+"");
					
					MyAdapter myAdapter = new MyAdapter(listCity);
					
					listCityData.setAdapter(myAdapter);
				}
			}
			@Override
			public void putOnFailure(String errorEvent, String message) {
			}
		});
		
		
		//给自定义viewSiderba设置监听
		siderbar.setOnTouchingChangedListener(this);
		
	}
	
	@OnClick({R.id.text_city_top, R.id.img_city_search})
	private void toOnclick(View view) {
		
		switch (view.getId()) {
		case R.id.text_city_top: //返回
			finish();
			break;
		case R.id.img_city_search: //刷新
			cityService.findListCity(new CallbackListener<List<City>>() {

				@Override
				public void putOnSuccess(List<City> data) {
					if (data != null) {
						listCity = data;
						
						MyAdapter myAdapter = new MyAdapter(listCity);
						
						listCityData.setAdapter(myAdapter);
					}
				}
				@Override
				public void putOnFailure(String errorEvent, String message) {
				}
			});
			break;

		default:
			break;
		}
	}
	
	@OnItemClick(R.id.list_city)
	private void toOnItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.i("toonitem--", position+"");
		Intent intent = new Intent();
		
		if(0 == position){
			textView = (TextView) view.findViewById(R.id.text_city_search);
		}else{
			textView = (TextView) view.findViewById(R.id.text_city_list_container);
		}
		intent.putExtra("cityName", textView.getText().toString());

		setResult(Activity.RESULT_OK, intent);
		finish();
	}
	
	
	

	//用于第一次保存首字母索引
	StringBuffer buffer = new StringBuffer();
	
	//用于保存索引值对应的城市名称
	private List<String> listName = new ArrayList<String>();
	
	/**
	 * 自定义适配器
	 * @author qj
	 * @date 2015-11-11
	 * @version 1.0
	 */
	public class MyAdapter extends BaseAdapter {

		private List<City> list;
		
		public MyAdapter(List<City> list) {
			super();
			this.list = list;
		}
		
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			Holder holder;
			//对convertView缓存做了处理
			if(convertView == null) {
				holder = new Holder();
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_city_list_container, null);
				ViewUtils.inject(holder, convertView);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			
			//数据显示适配处理
			City city = list.get(position);
			
			String sortkey = city.getSortKey();
			String cityName = city.getName();
			
			if(buffer.indexOf(sortkey) == -1) {
				buffer.append(sortkey);
				listName.add(cityName);
				
			}
			if(listName.contains(cityName)) {
				holder.cityContainerKeyText.setText(sortkey);
				//包含对应的城市就会让索引显示
				holder.cityContainerKeyText.setVisibility(View.VISIBLE);
			}else{
				holder.cityContainerKeyText.setVisibility(View.GONE);
			}
			holder.cityContainerText.setText(cityName);
			return convertView;
		}
		
	}
	
	/**
	 * 自定义View
	 * @author qj
	 * @date 2015-11-11
	 * @version 1.0
	 */
	public class Holder {
		
		@ViewInject(R.id.text_city_List_container_sortkey)
		private TextView cityContainerKeyText;
		
		@ViewInject(R.id.text_city_list_container)
		private TextView cityContainerText;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		finish();
	}

	
	/*  
	 * @see com.apple.view.SiderBar.OnTouchingChangedListener#onTouchingChanged(java.lang.String)
	 */
	@Override
	public void onTouchingChanged(String value) {
		//找到listview中显示的索引位置
		listCityData.setSelection(findIndex(listCity, value));
	} 
	
	public int findIndex(List<City> list, String s) {
		if(list != null){
			for(int i=0; i < list.size(); i++) {
				City city = list.get(i);
				if(s.equals(city.getSortKey())) {
					return i;
				}
			}
		}else{
			Toast.makeText(getBaseContext(), "暂无数据", 2000).show();
		}
		return -1;
		
	}
}
