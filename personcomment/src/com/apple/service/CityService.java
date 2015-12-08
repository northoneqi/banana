package com.apple.service;

import java.util.List;

import com.apple.entity.City;
import com.apple.utils.CallbackListener;

/**
 * 城市 - Service接口类
 * @author qj
 * @date 2015-11-09
 * @version 1.0
 */
public interface CityService {
	
	/**
	 * 查询城市列表
	 * @param listener
	 */
	public void findListCity(CallbackListener<List<City>> listener);

}
