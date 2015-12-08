package com.apple.entity;

/**
 * <p>
 * 数据响应类
 * </p>
 * 
 * @author qj
 * @param 泛型
 *            <T>
 * @date 2015-11-09
 */
public class ResponseObject<T> {

	// 构造函数，初始化code和msg
	public ResponseObject(String event, String msg) {
		this.event = event;
		this.message = msg;
	}

	/**
	 * 返回码 ,0：表示成功 ；1：表示失败
	 */
	private String event;
	/**
	 * 描述信息
	 */
	private String message;

	/**
	 * 起始位置, 默认为-1
	 */
	private int start = -1;

	/**
	 * 最大查询条数, 默认为-1
	 */
	private int limit = -1;

	/**
	 * 单个数据对象
	 */
	private T object;

	/**
	 * 多个数据对象
	 */
	private T objectList;
	/**
	 * 当前页
	 */
	private int currentPage;
	/**
	 * 每页条数
	 */
	private int pageSize;
	/**
	 * 总数
	 */
	private int total;
	/**
	 * 总页数
	 */
	private int totalPage;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public T getObjectList() {
		return objectList;
	}

	public void setObjectList(T objectList) {
		this.objectList = objectList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void RessponseObject(String initEvent, T initObject) {
		this.event = initEvent;
		this.object = initObject;
	}

	// 判断结果是否成功
	public boolean isSuccess() {
		return event.equals("0");
	}
}
