package com.zc.degou.server.base;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ServerData
 * @Description: TODO(������װ�Ӻ�̨���ݿ��õ�������)
 * @author jhjiang@inforace.com.cn �����
 * @date 2011-10-28 ����11:10:27
 * 
 */
public class ServerData implements Serializable {
	/* ���к� */
	private static final long serialVersionUID = 8321685581387989122L;
	/* ���ݵ�λ��ָ�� */
	private int dataIndex = 0;
	/*��ʾ�����ݷ���ȷ*/
	private int dataType = 0;
	/*ҳ��ĵڼ�������*/
	private int flag = 0;
	/*ҳ��ĵڼ�������*/
	private boolean bReadSQL = false;
	/*ҳ�����������*/
	private String className = "";
	/* ����ʵ�� */
	public Map<String, Map<String, String>> resultData = Collections
			.synchronizedMap(new HashMap<String, Map<String, String>>());

	/**
	 * ���ԭʼ����
	 * 
	 * @return
	 */
	public Map<String, Map<String, String>> getResultData() {
		return resultData;
	}

	/**
	 * ������ݣ���Ҫ����service�е���
	 * 
	 * @param data
	 */
	public void addData(Map<String, String> data) {
		this.resultData.put((++dataIndex) + "", data);
	}

	/**
	 * ������ݼ��ϵĸ���
	 * 
	 * @return
	 */
	public int getCount() {
		return dataIndex;
	}

	/**
	 * ����ָ����λ�û������
	 * 
	 * @param index
	 * @return
	 */
	public Map<String, String> getSingleData(int index) {
		return this.resultData.get(index + "");
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public void setDataClass(String strClassName) {
		this.className = strClassName;
	}

	public String getDataClass() {
		return className;
	}
	
	public void setReadFlag(boolean bReadSQL) {
		this.bReadSQL = bReadSQL;
	}

	public boolean getReadFlag() {
		return bReadSQL;
	}
}
