package com.apple.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;

/**
 * 用户偏好机制工具类
 * @author qj
 * @date 2015-10-30
 * @version 1.0
 */
public class SharedUtils {
	
	//声明名称
	private static final String SHARED_NAME = "qiqi";
	
	//声明第一次
	private static final String FIRST_TIME = "guide";
	
	/**
	 * 获取当前用户是否为第一次Boolean类型的值
	 * @param context 上下文对象
	 * @return
	 * @author qj
	 * @version 1.0
	 * @date 2015-11-10
	 */
	public static boolean getWelcomeBoolean(Context context){
		//指定文件名以及操作模式 ;
		return context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE).getBoolean(FIRST_TIME, false);
		
	}
	
	/**
	 * 写入标记当前用户为已启用过APP数据标记
	 * @param context： 上下文对象
	 * @param isFirst：形参是否第一次
	 * @author qj
	 * @version 1.0
	 * @date 2015-11-10
	 */
	public static void putWelcomBoolean(Context context, Boolean isFirst){
		
		//拿到Editor对象
		Editor editor = context.getSharedPreferences(SHARED_NAME, Context.MODE_APPEND).edit();
		//写入
		editor.putBoolean(FIRST_TIME, isFirst);
		//提交
		editor.commit();
	}
	
	/**
	 * 获取 - 用户城市信息
	 * @param context 
	 * @author qj
	 * @version 1.0
	 * @date 2015-11-10
	 */
	public static String getLocationCityName(Context context) {
		return context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE).getString("cityName", "选择城市");
	}
	
	/**
	 * 写入 - 用户城市信息
	 * @param context
	 * @param cityName 城市名
	 * @author qj
	 * @version 1.0 
	 * @date 2015-11-10
	 */
	public static void putLocationCityName(Context context, String cityName) {
		Editor editor = context.getSharedPreferences(SHARED_NAME, Context.MODE_APPEND).edit();
		editor.putString("cityName", cityName);
		editor.commit();
	}
}
 