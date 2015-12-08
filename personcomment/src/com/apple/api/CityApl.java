package com.apple.api;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.apple.entity.City;
import com.apple.entity.ResponseObject;

/**
 * 城市 - Apl类
 * @author qj
 * @date 2015-11-09
 * @version 1.0
 */
public interface CityApl {
	/**
	 * 获取城市列表
	 * @return 
	 */
	public ResponseObject<List<City>> findListCity(Map<String, String> paramsMap, Type type);

}
