package com.apple.entity;

import java.util.Date;

/**
 * 商品 - entity
 * 
 * @author qj
 * @date 2015-11-11
 * @version 1.0
 */
public class Goods implements java.io.Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 分类ID
	 */
	private String categoryId;

	/**
	 * 商家ID
	 */
	private String shopId;
	/**
	 * 城市ID
	 */
	private String cityId;
	/**
	 * 商品名称
	 */
	private String title;
	/**
	 * 商品描述信息
	 */
	private String sortTitle;
	/**
	 * 图片地址
	 */
	private String imgUrl;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 商品原价
	 */
	private String valueMoney;
	/**
	 * 商品销售价
	 */
	private String price;
	/**
	 * 商品折扣价
	 */
	private String ribat;
	/**
	 * 购买量
	 */
	private String bought;
	/**
	 * 最大库存
	 */
	private String maxQuota;
	/**
	 * 是否发送信息
	 */
	private String post;
	/**
	 * 是否卖出
	 */
	private String soldOut;
	/**
	 * 使用劵提示
	 */
	private String tip;
	/**
	 * 商品结束时间
	 */
	private String endTime;
	/**
	 * 商品详情
	 */
	private String detail;
	/**
	 * 
	 */
	private String reFund;
	/**
	 * 
	 */
	private String overTime;
	/**
	 * 
	 */
	private String minquota;
	/**
	 * 
	 */
	private String distance;
	/**
	 * 商品所属商家
	 */
	private Shop shop;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSortTitle() {
		return sortTitle;
	}

	public void setSortTitle(String sortTitle) {
		this.sortTitle = sortTitle;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getValueMoney() {
		return valueMoney;
	}

	public void setValueMoney(String valueMoney) {
		this.valueMoney = valueMoney;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRibat() {
		return ribat;
	}

	public void setRibat(String ribat) {
		this.ribat = ribat;
	}

	public String getBought() {
		return bought;
	}

	public void setBought(String bought) {
		this.bought = bought;
	}

	public String getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(String maxQuota) {
		this.maxQuota = maxQuota;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getSoldOut() {
		return soldOut;
	}

	public void setSoldOut(String soldOut) {
		this.soldOut = soldOut;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getReFund() {
		return reFund;
	}

	public void setReFund(String reFund) {
		this.reFund = reFund;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public String getMinquota() {
		return minquota;
	}

	public void setMinquota(String minquota) {
		this.minquota = minquota;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
