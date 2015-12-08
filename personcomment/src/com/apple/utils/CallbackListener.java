package com.apple.utils;

/**
 * 回调监听器
 * @author qj
 * @date 2015-11-10
 * @version 1.0
 * @param <T>
 */
public interface CallbackListener<T> {
	
	/**
	 * 成功时调用
	 * @author qj
	 * @param data 返回的数据
	 */
	public void putOnSuccess(T data);

	/**
	 * 失败时调用
	 * @param errorEvent 错误码
	 * @param message 错误信息
	 */
	public void putOnFailure(String errorEvent, String message);
}
