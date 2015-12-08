package com.apple.application;

import com.apple.service.CityService;
import com.apple.service.impl.CityServiceImpl;

public class Application extends android.app.Application {
	
	private CityService cityService;

    @Override
    public void onCreate() {
        super.onCreate();
        cityService = new CityServiceImpl(this);
    }

    public CityService getCityService() {
        return cityService;
    }
}
