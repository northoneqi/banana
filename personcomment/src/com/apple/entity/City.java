package com.apple.entity;

/**
 * 城市 - 实体类
 * @author qj
 * @data 2015-11-09
 * 
 */
public class City {
	
	/**
	 * ID
	 */
	private String id;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 城市编码字母
	 */
	private String citySortkey;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return cityName;
	}

	public void setName(String name) {
		this.cityName = name;
	}

	public String getSortKey() {
		return citySortkey;
	}

	public void setSortKey(String sortKey) {
		this.citySortkey = sortKey;
	}

}
