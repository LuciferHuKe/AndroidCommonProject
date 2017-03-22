package com.zc.degou.server.base;

public interface RefreshData {
	/**
	 * �����ݺ��ҳ���ˢ��
	 */
	public void refreshData(ServerData serverData);
	/**
	 * ��û�л����ݵ�ʱ����� �ڽ�������ʾ
	 */
	public void showNoData(int flag);
	/**
	 * �ڳ��ִ����ʱ������ڽ�������ʾ
	 */
	public void showError(int flag);
}
