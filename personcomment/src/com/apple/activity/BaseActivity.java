package com.apple.activity;

import com.apple.application.Application;
import com.apple.service.CityService;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Activity - 基类
 * @author qj
 * @date 2015-11-10
 * @version 1.0
 */
public class BaseActivity extends FragmentActivity {
	// 上下文实例
    public Context context;
    // 应用全局的实例
    public Application application;
    // 核心层的Action实例
    public CityService appAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        application = (Application) this.getApplication();
        appAction = application.getCityService();
    }
}
