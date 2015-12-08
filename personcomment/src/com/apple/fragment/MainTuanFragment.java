package com.apple.fragment;

import java.lang.reflect.Type;
import java.util.List;

import com.apple.entity.City;
import com.apple.entity.Goods;
import com.apple.entity.ResponseObject;
import com.apple.personcomment.R;
import com.apple.utils.ConstUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 首页-（团购） fragment类
 * 
 * @author qj
 * @date 2015-11-05
 * @version 1.0
 */
public class MainTuanFragment extends Fragment {

	@ViewInject(R.id.pull_to_refresh_listView_tuan_container)
	private PullToRefreshListView pullToRefreshView;
	
	@ViewInject(R.id.autoCompleteTextView1)
	private AutoCompleteTextView autocomplate;
	@ViewInject(R.id.search_button)
	private Button button;
	// 声明Adapter
	private MyAdapter myAdapter;

	// 声明一个集合
	private List<Goods> listGood;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// 布局加载器 将XML文件映射一个对象 ：根节点选择为null
		View view = inflater.inflate(R.layout.fragment_main_tuan, null);
		ViewUtils.inject(this, view);
		/*View viewWaid = LayoutInflater.from(getActivity()).inflate(R.layout.activity_search_text, null);
		
		pullToRefreshView.addView(viewWaid);
		*/

		/** 设置产品展示信息列表属性值 */
		// 支持上拉下拉刷新功能
		pullToRefreshView.setMode(Mode.BOTH);
		// 支持滚动时不加载数据
		pullToRefreshView.setScrollingWhileRefreshingEnabled(true);
		// 设置监听
		pullToRefreshView
				.setOnRefreshListener(new OnRefreshListener<ListView>() {

					/**
					 * onRefresh - 当刷新的时候执行的方法
					 * 
					 * @param refreshView
					 */
					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {
						/** 下拉刷新 （Y轴 < 0 是） */
						// 加载请求数据
						findPostHandle(pullToRefreshView.getScrollY() < 0, null);

					}
				});

		/**
		 * 首次调用当前页面时自动加载数据方法
		 */
		new Handler(new Handler.Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				pullToRefreshView.setRefreshing();
				return true;
			}
		}).sendEmptyMessageDelayed(0, 320);

		return view;
	}
	
	
	@OnClick(R.id.search_button)
	private void toOnclickSearchButton(View view){
		
		String value = autocomplate.getText().toString();
		
		
		findPostHandle(pullToRefreshView.getScrollY() < 0, value);
		
	}

	// 第几页
	private int page;
	private int count;
	/**
	 * 使用 - XUtils 请求数据
	 * 
	 * @author qj
	 * @date 2015-11-11
	 * @version 1.0
	 * @return T
	 */
	public void findPostHandle(final boolean refresh, String value) {

		if (refresh) {
			page = 1;
		} else {
			page++;
		}

		RequestParams params = new RequestParams();
		params.addQueryStringParameter("start", page + "");
		params.addQueryStringParameter("pageSize", "10");

		// http请求
		new HttpUtils().send(HttpMethod.GET, ConstUtils.HOME_GOODS_INTERFACE,
				params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// 停止刷新
						pullToRefreshView.onRefreshComplete();
					}

					// 成功请求返回的的方法
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// 停止刷新
						pullToRefreshView.onRefreshComplete();

						Gson gson = new Gson();
						Type type = new TypeToken<ResponseObject<List<Goods>>>() {
						}.getType();
						ResponseObject<List<Goods>> object = gson.fromJson(
								arg0.result, type);
						listGood = object.getObjectList();
						count = object.getTotal();
						/*
						 * myAdapter = new MyAdapter();
						 * listTuanContainer.setDividerHeight(1);
						 * listTuanContainer.setAdapter(myAdapter);
						 */

						/** 判断上拉刷新或是下拉刷新 */
						// 下拉刷新
						if (refresh) {
							listGood = object.getObjectList();
							myAdapter = new MyAdapter();
							pullToRefreshView.setAdapter(myAdapter);
						}else {
							//加载更多
							listGood.addAll(object.getObjectList());
							//数据源也要发送通知
							myAdapter.notifyDataSetChanged();
						}
						//判断有没有更多的数据展示
						if(count == page) {
							//只能刷新
							pullToRefreshView.setMode(Mode.PULL_FROM_START);
						}

					}
				});
	}

	/**
	 * 自定义适配器
	 * 
	 * @author qj
	 * @date 2015-11-11
	 * @version 1.0
	 */
	public class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return listGood.size();
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		/*
		 * <p>渲染每一个item对应的视图</p> <p>建议使用listview里的缓存优化</p>
		 * <p>对converView如果为空时给从新映射出来；不为空时，把里面的内容获取到进行填充即可。</p>
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MyHolder myHolder;
			if (convertView == null) {
				myHolder = new MyHolder();
				// 映射，将XML文件转化为convertView视图
				convertView = LayoutInflater.from(parent.getContext()).inflate(
						R.layout.fragment_main_tuan_container, null);
				// 添加标签
				ViewUtils.inject(myHolder, convertView);
				// 把myHolder设置进去
				convertView.setTag(myHolder);
			} else {
				// 直接从convertView取出myHolder
				myHolder = (MyHolder) convertView.getTag();
			}

			/** 获取对应的索取内容 */
			StringBuffer sbf = new StringBuffer("￥"
					+ listGood.get(position).getValueMoney());
			// 添加中划线
			SpannableString spannable = new SpannableString(sbf);
			spannable.setSpan(new StrikethroughSpan(), 0, sbf.length(),
					Spannable.SPAN_INCLUSIVE_INCLUSIVE);

			/** 使用picasso框架避免图片出现OOM（内存溢出）以及图片错位等。 */
			// 拿去上下文对象
			// Picasso.with(parent.getContext()).load(listGood.get(position).getImgUrl()).placeholder(R.drawable.ic_launcher).into(myHolder.leftImg);
			Picasso.with(parent.getContext())
					.load(listGood.get(position).getImgUrl())
					.placeholder(R.drawable.loading).into(myHolder.leftImg);
			myHolder.titleText.setText(listGood.get(position).getTitle());
			myHolder.titleContent.setText(listGood.get(position).getDetail());
			myHolder.priceText.setText("￥" + listGood.get(position).getPrice());
			myHolder.valueText.setText(spannable);
			myHolder.countText
					.setText(listGood.get(position).getBought() + "份");

			// 切记返回的不是null值，是convertview。
			return convertView;

		}

		/**
		 * <p>
		 * 标签类
		 * </p>
		 * 
		 * @author qj
		 * @date 2015-11-11
		 * @version 1.0
		 */
		public class MyHolder {

			/** 图片 */
			@ViewInject(R.id.img_tuan_left)
			public ImageView leftImg;
			/** 标题 */
			@ViewInject(R.id.text_tuan_container_title)
			public TextView titleText;
			/** 详细标题 */
			@ViewInject(R.id.text_tuan_container_title_value)
			public TextView titleContent;
			/** 现价 */
			@ViewInject(R.id.text_tuan_container_price)
			public TextView priceText;
			/** 原价 */
			@ViewInject(R.id.text_tuan_container_value)
			public TextView valueText;
			/** 总数 */
			@ViewInject(R.id.text_tuan_container_rigth_hot)
			public TextView countText;
		}

	}

}
