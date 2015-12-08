package com.apple.entity;

import java.util.Date;

/**
 * 供应商 - entity
 * 
 * @author qj
 * @date 2015-11-11
 * @version 1.0
 */
public class Shop implements java.io.Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private int id;
	/**
	 * 商家名称
	 */
	private String shopName;
	/**
	 * 商家电话
	 */
	private String shopTel;
	/**
	 * 地址
	 */
	private String shopAddress;
	/**
	 * 城市
	 */
	private String shopArea;
	/**
	 * 开始时间
	 */
	private Date shopStartTime;
	/**
	 * 商家经度
	 */
	private double shopLon;
	/**
	 * 商家纬度
	 */
	private double showLat;
	/**
	 * 交通信息
	 */
	private String trafficInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopTel() {
		return shopTel;
	}

	public void setShopTel(String shopTel) {
		this.shopTel = shopTel;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopArea() {
		return shopArea;
	}

	public void setShopArea(String shopArea) {
		this.shopArea = shopArea;
	}

	public Date getShopStartTime() {
		return shopStartTime;
	}

	public void setShopStartTime(Date shopStartTime) {
		this.shopStartTime = shopStartTime;
	}

	public double getShopLon() {
		return shopLon;
	}

	public void setShopLon(double shopLon) {
		this.shopLon = shopLon;
	}

	public double getShowLat() {
		return showLat;
	}

	public void setShowLat(double showLat) {
		this.showLat = showLat;
	}

	public String getTrafficInfo() {
		return trafficInfo;
	}

	public void setTrafficInfo(String trafficInfo) {
		this.trafficInfo = trafficInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
