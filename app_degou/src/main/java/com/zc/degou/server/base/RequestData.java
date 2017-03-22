/**
 * @工程名：GuoGuo_Mobil_Version1
 * @包名：org.guoguo.base
 * @时间： 2011-10-28 上午11:28:43
 * @作者：jhjiang@inforace.com.cn  蒋金豪
 */
package com.zc.degou.server.base;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

/**
 * @ClassName: RequestData
 * @Description: TODO(用来封装向后台请求数据)
 * @author jhjiang@inforace.com.cn 蒋金豪
 * @date 2011-10-28 上午11:28:43
 * 
 */
public class RequestData implements Serializable {
	/* 序列号 */
	private static final long serialVersionUID = 8321685581387989121L;
	/* Intent的名称，用于页面的接受 */
	private String ActionName = null;
	/* 向后台请求数据时使用的参数 */
	private HashMap<String, String> paras = new HashMap<String, String>();
	/* 用户请求的数据类名 */
	private String className = null;
	/* 同一个页面中不同请求的标志 */
	private int flag = 0;

	private String selectionKey = "";

	/*
	 * 超时时间
	 */
	private int delayTime = 4000;
	/**
	 * 请求失败的重复请求次数
	 */
	private int retry = 5;

	/* 请求的类型 */
	private int Type = 0;

	/**
	 * 放弃请求时间
	 */
	private long timeout = 0;
	private File picLocation = null;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getActionName() {
		return ActionName;
	}

	public void setActionName(String actionName) {
		ActionName = actionName;
	}

	public HashMap<String, String> getParas() {
		return paras;
	}

	public void setParas(HashMap<String, String> paras) {
		this.paras = paras;
	}

	public int getRetry() {
		return retry;
	}

	public int getRetryM() {
		return (--retry);
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public File getPicLocation() {
		return picLocation;
	}

	public void setPicLocation(File picLocation) {
		this.picLocation = picLocation;
	}

	public int getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	public String getSelectionKey() {
		return selectionKey;
	}

	public void setSelectionKey(String selectionKey) {
		this.selectionKey = selectionKey;
	}

}
