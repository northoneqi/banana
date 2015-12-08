package com.apple.api.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.apple.api.CityApl;
import com.apple.entity.City;
import com.apple.entity.ResponseObject;
import com.apple.utils.ConstUtils;
import com.apple.utils.HttpEngine;
import com.lidroid.xutils.http.RequestParams;

/**
 * 城市列表 - impl类
 * 
 * @author qj
 * @date 2015-11-09
 * @version 1.0
 * @param <T>
 */
public class CityApiImpl extends HttpEngine<City> implements CityApl {
	private final static String TIME_OUT_EVENT = "CONNECT_TIME_OUT";
	private final static String TIME_OUT_EVENT_MSG = "连接服务器失败";

	// http引擎
/*	private HttpEngine<T> httpEngine;

	public CityApiImpl() {
		httpEngine = HttpEngine.getInstance();
	}*/

	@Override
	public ResponseObject<List<City>> findListCity(
			Map<String, String> paramMap, Type type) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("metch", "android");
		params.addQueryStringParameter("value", "city");
		try {
			return super.postHandle(paramMap, type,ConstUtils.HOME_CITY_INTERFACE);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseObject<List<City>>("0", "数据失败");
		}
	}
}
