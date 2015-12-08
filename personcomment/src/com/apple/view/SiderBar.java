package com.apple.view;


import com.apple.personcomment.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 城市列表的索引（绘制字母）
 * @author qj
 * @date 2015-11-11
 * @version 1.0 
 */
public class SiderBar extends View {

	/**
	 * 用于XML文件创建控件对象时调用。
	 * @param context
	 * @param attrs
	 */
	public SiderBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 一般在new对象时调用
	 * @param context
	 */
	public SiderBar(Context context) {
		super(context);
	}
	
	/**
	 * 显示数据
	 */
	public static String [] siderba = {"☆", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
		"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"}; 

	//定义画笔
	private Paint paint = new Paint();
	
	//定义监听事件
	private OnTouchingChangedListener changedLitener;
	
	//选中位置
	private int choose;
	 
	/*  
	 * 绘制图形
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//设置画笔为灰色
		paint.setColor(Color.GRAY);
		//设置字体样式为粗体
		paint.setTypeface(Typeface.DEFAULT);
		paint.setTextSize(28);
		//获取自定义view的宽和高
		int height = this.getHeight();
		int width = this.getWidth();
		//设定每一个字母所在控件的高度
		int eachHeight = height/siderba.length;
		//遍历绘制每一个字母
		for(int i=0; i<siderba.length; i++){
			//文字所在区域的X注的偏移量
			float x = width/2-paint.measureText(siderba[i])/2;
			//文字所在区域的Y注的偏移量
			float y = (1+i)*eachHeight;
			//提交画布
			canvas.drawText(siderba[i], x, y, paint);
		}
		
	}
	
	/**
	 * 定义监听事件
	 */
	public interface OnTouchingChangedListener {
		public void onTouchingChanged(String value);
	}
	
	public void setOnTouchingChangedListener(OnTouchingChangedListener changerListenner ) {
		this.changedLitener = changerListenner;
	}
	
	/* 分发对应的touch监听
	 * @see android.view.View#dispatchTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		//捕获取对应的动作
		final int action = event.getAction();
		//低级Y轴的坐标
		final float y = event.getY();
		final OnTouchingChangedListener listener = this.changedLitener;
		//获取点击Y轴坐标所占总高度的比例*数组的长度就是等于数组中点击字母的索引
		final int c = (int) (y/this.getHeight()*siderba.length);
		
		switch (action) {
		case MotionEvent.ACTION_UP: //抬起手指时
			//手指抬起时设置背景颜色
			setBackgroundResource(android.R.color.transparent);
			//应用
			invalidate();
			break;

		default:
			//设置背景改变
			setBackgroundResource(R.drawable.sidebar_background);
			if(c > 0 && c < siderba.length) {
				if(listener != null) {
					listener.onTouchingChanged(siderba[c]);
				}
				choose  = c;
				invalidate();
			}
			break;
		}
		//true直接响应
		return true;

	}
	
}
