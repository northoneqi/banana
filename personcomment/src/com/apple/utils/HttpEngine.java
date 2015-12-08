package com.apple.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.apple.entity.City;
import com.apple.entity.ResponseObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * http引擎类
 * @author qj
 * @date 2015-11-10
 * @version 1.0
 */
public class HttpEngine<T> {
    private final static String REQUEST_MOTHOD = "POST";
    private final static String ENCODE_TYPE = "UTF-8";
    private final static int TIME_OUT = 15000;

    private ResponseObject<List<T>> object;
    private static HttpEngine instance = null;

    @SuppressWarnings("rawtypes")
	public static HttpEngine getInstance() {
        if (instance == null) {
            instance = new HttpEngine();
        }
        return instance;
    }
    
    /**
     * Post请求数据
     * @param <T>
     * @param paramsMap 参数
     * @param type 类型
     * @return 数据集合
     * @throws IOException
     */
    public ResponseObject<List<T>> postHandle(Map<String, String> paramsMap, Type type, String url) throws IOException {
        String data = joinParams(paramsMap);
        HttpURLConnection connection = getConnection(url);
        connection.setRequestProperty("Content-Length", String.valueOf(data.getBytes().length));
        connection.connect();
        OutputStream os = connection.getOutputStream();
        os.write(data.getBytes());
        os.flush();
        if (connection.getResponseCode() == 200) {
            // 获取响应的输入流对象
            InputStream is = connection.getInputStream();
            // 创建字节输出流对象
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 定义读取的长度
            int len = 0;
            // 定义缓冲区
            byte buffer[] = new byte[1024];
            // 按照缓冲区的大小，循环读取
            while ((len = is.read(buffer)) != -1) {
                // 根据读取的长度写入到os对象中
                baos.write(buffer, 0, len);
            }
            // 释放资源
            is.close();
            baos.close();
            connection.disconnect();
            // 返回字符串
            final String result = new String(baos.toByteArray());
            
            Log.i("-----返回字符串-----", result);
            
            Gson gson = new Gson();
            
            return gson.fromJson(result, type);
        } else {
            connection.disconnect();
            return null;
        }
    }
    
    private HttpURLConnection getConnection(String httpUrl) {
        HttpURLConnection connection = null;
        // 初始化connection
        try {
            // 根据地址创建URL对象
            URL url = new URL(httpUrl);
            // 根据URL对象打开链接
            connection = (HttpURLConnection) url.openConnection();
            // 设置请求的方式
            connection.setRequestMethod(REQUEST_MOTHOD);
            // 发送POST请求必须设置允许输入，默认为true
            connection.setDoInput(true);
            // 发送POST请求必须设置允许输出
            connection.setDoOutput(true);
            // 设置不使用缓存
            connection.setUseCaches(false);
            // 设置请求的超时时间
            connection.setReadTimeout(TIME_OUT);
            connection.setConnectTimeout(TIME_OUT);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Response-Type", "json");
            connection.setChunkedStreamingMode(0);
            
            Log.i("url--------------------", url+"");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    
    private String joinParams(Map<String, String> paramsMap) {
        StringBuilder stringBuilder = new StringBuilder();
     
        if(!paramsMap.isEmpty()) {
        for (String key : paramsMap.keySet()) {
            stringBuilder.append(key);
            stringBuilder.append("=");
            try {
                stringBuilder.append(URLEncoder.encode(paramsMap.get(key), ENCODE_TYPE));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            stringBuilder.append("&");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
        
        }else{
        	return null;
        }
    }
    
   /**
    * 使用 - XUtils 请求数据
    * @author qj
    * @date 2015-11-11
    * @version 1.0
    * @return T
    */
   public ResponseObject<List<T>> findPostHandle(RequestParams params, String url) {

	  //http请求
	  new HttpUtils().send(HttpMethod.GET, ConstUtils.HOME_CITY_INTERFACE, params, new RequestCallBack<String>() {

		@Override
		public void onFailure(HttpException arg0, String arg1) {
		}

		//成功请求返回的的方法
		@Override
		public void onSuccess(ResponseInfo<String> arg0) {
			Gson gson = new Gson();
			Type type = new TypeToken<ResponseObject<List<City>>>() {}.getType();
			object = new ResponseObject<List<T>>("1", "调用成功");
			object = gson.fromJson(arg0.result, type);
		}
	});
	   return object;
   }
}