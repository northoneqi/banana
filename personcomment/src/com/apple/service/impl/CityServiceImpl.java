package com.apple.service.impl;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.AsyncTask;

import com.apple.api.CityApl;
import com.apple.api.impl.CityApiImpl;
import com.apple.entity.City;
import com.apple.entity.ResponseObject;
import com.apple.service.CityService;
import com.apple.utils.CallbackListener;
import com.google.gson.reflect.TypeToken;

/**
 * 城市列表 - Service实现类
 * 
 * @author qj
 * @date 2015-11-09
 * @version 1.0
 */
public class CityServiceImpl implements CityService {
	private Context context;
	private CityApl cityApl;

	public CityServiceImpl(Context context) {
		this.context = context;
		this.cityApl = new CityApiImpl();
	}

	@Override
	public void findListCity(final CallbackListener<List<City>> listener) {
		/**
		 * 请求Api
		 * 
		 * @author qj
		 * 
		 */
		new AsyncTask<Void, Void, ResponseObject<List<City>>>() {
			@Override
			protected ResponseObject<List<City>> doInBackground(Void... voids) {
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("method", "qiqi");
				Type type = new TypeToken<ResponseObject<List<City>>>() {
				}.getType();
				ResponseObject<List<City>> object = cityApl.findListCity(
						paramMap, type);
				return object;
			}

			// 提交
			@Override
			protected void onPostExecute(ResponseObject<List<City>> response) {
				if (listener != null && response != null) {
					if (response.isSuccess()) {
						listener.putOnSuccess(response.getObjectList());
					} else {
						listener.putOnFailure(response.getEvent(),
								response.getMessage());
					}
				}
			}
		}.execute();
	}
}
