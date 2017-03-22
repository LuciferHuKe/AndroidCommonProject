/**
 * @工程名：GuoGuo_Version1_Mobil
 * @包名：org.guoguo.base
 * @时间： 2011-12-26 下午02:24:09
 * @作者：jhjiang@inforace.com.cn  蒋金豪
 */
package com.zc.degou.server.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;

import com.zc.degou.server.tool.LogUtil;
import com.zc.degou.tool.ShowMessageUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @ClassName: RefreshUtil
 * @Description: TODO(获得数据的工具类，并控制界面的显示)
 * @author jhjiang@inforace.com.cn 蒋金豪
 * @date 2011-12-26 下午02:24:09
 * 
 */
public class RefreshUtil implements Callback, GuoConstants {

	/* 页面相应的控制器 */
	private Handler myHandler;
	/* 刷新页面接口 */
	private RefreshData myRefreshData;
	/* 刷新页面时的显示控件 */
	private ArrayList<View> myViews = new ArrayList<View>();
	/* 接受页面资源 */
	private Context myContext;
	/* 数据广播接收器 */
	private BroadcastReceiver dataReceiver;
	/* 页面名称 */
	private String tempPageName = "";
	/* 控制显示控件的索引 */
	private int[] indexs;
	/* 是否数据已经返回 */
	private boolean isReturn = false;

	private ServerData myServerData;

	/**
	 * 工具构造器，每一个工具都只对应于一个页面
	 * 
	 * @param myContext
	 *            页面属性参数
	 * @param pageName
	 *            页面名称，通过该名称获得广播的名称
	 * @param rd
	 *            刷新页面用的接口
	 * @param views
	 *            需要控制的页面视图
	 */
	public RefreshUtil(Context myContext, String pageName, RefreshData rd,
			View... views) {
		this.myContext = myContext;
		this.myHandler = new Handler(this);
		this.myRefreshData = rd;
		this.tempPageName = pageName;
		if (null != views) {
			for (int i = 0; i < views.length; i++) {
				myViews.add(views[i]);
			}
		}
		// 注册接收器
		dataReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				try {
					if (intent.getAction().equalsIgnoreCase(tempPageName)) {
						LogUtil.debug(context, tempPageName
								+ "接收到了广播(RefreshUtil)>>>>>>>>>>acitnvity"
								+ intent.getAction());
						if (intent.getBooleanExtra("isError", false)) {
							// 获取请求标识符
							int flag = intent.getIntExtra("flag", 0);
							myServerData = new ServerData();
							myServerData.setFlag(flag);

							Message aa = new Message();
							aa.what = 2;
							myHandler.sendMessage(aa);
							return;
						}

						Bundle tempBundle = intent.getExtras();
						if (tempBundle != null) {
							myServerData = (ServerData) tempBundle
									.getSerializable(SERVER_RESPONSE_DATA_FLAG);
							LogUtil.debug(context,
									"读取数据长度>>" + myServerData.getFlag());
							Message aa = new Message();
							aa.what = myServerData.getDataType();
							myHandler.sendMessage(aa);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					ShowMessageUtil.getShowToast(context, "<<出现异常>>\n 异常处理")
							.show();
				}
			}
		};

		IntentFilter filter = new IntentFilter();
		filter.addAction(tempPageName);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		this.myContext.registerReceiver(dataReceiver, filter);
	}

	@Override
	public boolean handleMessage(Message msg) {
		try {
			synchronized (this) {
				int flag = 0;
				switch (msg.what) {
				case 0:
					// 获取到数据，并通过接口显示数据
					if (myServerData.getFlag() != 100) {
						isReturn = true;
						myRefreshData.refreshData(myServerData);
						dispareViews();
					}
					break;
				case 1:
					// 没有获取到数据，通过接口显示没有数据
					isReturn = true;
					if (myServerData != null) {
						flag = myServerData.getFlag();
					}
					myRefreshData.showNoData(flag);
					dispareViews();
					break;
				case 2:
					// 出现异常时，通过接口显示错误
					isReturn = true;
					if (myServerData != null) {
						flag = myServerData.getFlag();
						myRefreshData.showError(myServerData.getFlag());
					} else {
						myRefreshData.showError(0);
					}
					dispareViews();
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 控制显示控件不显示
	 */
	public void dispareViews() {
		// 循环遍历所有的控件， 并控制其不显示
		LogUtil.debug(myContext, "控制显示空件不显示");
		for (int i = 0; i < myViews.size(); i++) {
			for (int j = 0; j < indexs.length; j++) {
				if (i == indexs[j] - 1) {
					if (myViews.get(i) != null) {
						myViews.get(i).setVisibility(View.INVISIBLE);
					}
				}
			}
		}
	}

	public void getData(int flag, File picFile, Integer... tempIndexs) {

		LogUtil.debug(myContext, "需要控制的试图的个数是:" + myViews.size());
		LogUtil.debug(myContext, "需要控制的试图的索引位置:" + tempIndexs.length);
		isReturn = false;
		this.indexs = new int[tempIndexs.length];
		for (int i = 0; i < tempIndexs.length; i++) {
			this.indexs[i] = tempIndexs[i];
		}
		LogUtil.debug(myContext, "索引个数是:" + indexs.length);
		// 遍历所有的控件，将索引的控件显示出来
		for (int i = 0; i < myViews.size(); i++) {
			for (int j = 0; j < indexs.length; j++) {
				if (i == indexs[j] - 1) {
					if (myViews.get(i) != null) {
						myViews.get(i).setVisibility(View.VISIBLE);
					}
				}
			}
		}

		// 向后台发送广播 通知后台请求数据
		Intent intent = new Intent();
		RequestData rd = new RequestData();
		rd.setFlag(flag);
		rd.setType(1);
		rd.setPicLocation(picFile);
		rd.setActionName(tempPageName);
		Bundle tempbundle = new Bundle();
		tempbundle.putSerializable(SERVER_REQUEST_FLAG, rd);
		intent.putExtras(tempbundle);
		intent.setAction(SERVER_REQUEST);
		myContext.sendBroadcast(intent);

		// 开始获取数据线程
		new Thread(new myGetDataThread()).start();

	}

	/**
	 * 获得数据并控制相应的View
	 * @param flag
	 * @param paras
	 * @param className
	 * @param tempIndexs
     */
	public void getData(int flag, HashMap<String, String> paras,
			String className, Integer... tempIndexs) {
		LogUtil.debug(myContext, "需要控制的试图的个数是:" + myViews.size());
		LogUtil.debug(myContext, "需要控制的试图的索引位置:" + tempIndexs.length);
		if (flag != 100)
			isReturn = false;
		this.indexs = new int[tempIndexs.length];
		for (int i = 0; i < tempIndexs.length; i++) {
			this.indexs[i] = tempIndexs[i];
		}
		LogUtil.debug(myContext, "索引个数是:" + indexs.length);
		// 遍历所有的控件，将索引的控件显示出来
		for (int i = 0; i < myViews.size(); i++) {
			for (int j = 0; j < indexs.length; j++) {
				if (i == indexs[j] - 1) {
					if (myViews.get(i) != null) {
						myViews.get(i).setVisibility(View.VISIBLE);
					}
				}
			}
		}

		// myServerData.setDataClass(className);
		// 向后台发送广播 通知后台请求数据
		Intent intent = new Intent();
		RequestData rd = new RequestData();
		rd.setFlag(flag);
		rd.setActionName(tempPageName);
		rd.setClassName(className);
		rd.setParas(paras);
		rd.setTimeout(System.currentTimeMillis() + rd.getDelayTime());

		Bundle tempbundle = new Bundle();
		tempbundle.putSerializable(SERVER_REQUEST_FLAG, rd);
		intent.putExtras(tempbundle);
		intent.setAction(SERVER_REQUEST);
		myContext.sendBroadcast(intent);

		// 开始获取数据线程
		if (!className.startsWith("YAM")) {
			new Thread(new myGetDataThread()).start();
		}

	}

	/**
	 * 获得数据并控制相应的View
	 * 
	 * @param
	 */
	public void getData(HashMap<String, String> paras, String className,
			int flag) {
		isReturn = false;
		getData(flag, paras, className, 1);

		// myServerData.setDataClass(className);
	}

	/**
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @author jhjiang@inforace.com.cn 蒋金豪
	 * @date 2011-12-26 下午02:01:04
	 * 
	 */
	class myGetDataThread implements Runnable {

		@Override
		public void run() {
			// Log.v("LOOP", "RefreshUtil myGetDataThread");
			try {
				Thread.currentThread().sleep(MOBIL_GETDATAVIEW_OUTTIME);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!isReturn) {
				// 通知后台 没有获得到数据
				Message tempMessage = new Message();
				tempMessage.what = 1;
				myHandler.sendMessage(tempMessage);
				LogUtil.debug(myContext, "没有从后台获得到数据！");
			}
		}
	}

	public void reset() {
		try {
			myContext.unregisterReceiver(dataReceiver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 注册接收器
		dataReceiver = new BroadcastReceiver() {

			private ServerData msg = null;

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub

				try {
					if (intent.getAction().equalsIgnoreCase(tempPageName)) {
						LogUtil.debug(context, tempPageName
								+ "接收到了广播(RefreshUtil)>>>>>>>>>>acitnvity"
								+ intent.getAction());
						if (intent.getBooleanExtra("isError", false)) {
							Message aa = new Message();
							aa.what = 2;
							myHandler.sendMessage(aa);
							return;
						}

						Bundle tempBundle = intent.getExtras();
						if (tempBundle != null) {
							myServerData = (ServerData) tempBundle
									.getSerializable(SERVER_RESPONSE_DATA_FLAG);
							LogUtil.debug(context,
									"读取数据长度>>" + myServerData.getFlag());
							Message aa = new Message();
							aa.what = myServerData.getDataType();
							myServerData.setReadFlag(false);
							myHandler.sendMessage(aa);

						}

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ShowMessageUtil.getShowToast(context, "<<出现异常>>\n 异常处理")
							.show();
				}
			}
		};

		IntentFilter filter = new IntentFilter();
		filter.addAction(tempPageName);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		this.myContext.registerReceiver(dataReceiver, filter);
	}

	/**
	 * 释放掉占用的资源
	 */
	public void releaseUtil() {
		myContext.unregisterReceiver(dataReceiver);
	}
}
