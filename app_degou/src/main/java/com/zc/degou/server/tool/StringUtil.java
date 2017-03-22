/**   
 * @Title: StringUtil.java 
 * @Package org.guoguo.util 
 * @Description: TODO(��һ�仰�������ļ���ʲô) 
 * @author ����� jjhhappy@sina.com   
 * @date 2011-10-27 ����03:32:46 
 * @version V1.0   
 */
package com.zc.degou.server.tool;

import java.util.Calendar;

/**
 * @ClassName: StringUtil
 * @Description: TODO(������һ�仰��������������)
 * @author ����� jjhhappy@sina.com
 * @date 2011-10-27 ����03:32:46
 * 
 */
public class StringUtil {
	/**
	 * ����ǰʱ��ת����Ϊ��ݿ�֧�ֵ��ַ���ʽ
	 * 
	 * @return
	 */
	public static String getDateStringForDB() {
		Calendar now = Calendar.getInstance();
		String time = "" + addZeroBefore(now.get(Calendar.YEAR) + "", 4)
				+ addZeroBefore((now.get(Calendar.MONTH) + 1) + "", 2)
				+ addZeroBefore(now.get(Calendar.DAY_OF_MONTH) + "", 2) + " "
				+ addZeroBefore(now.get(Calendar.HOUR_OF_DAY) + "", 2)
				+ addZeroBefore(now.get(Calendar.MINUTE) + "", 2)
				+ addZeroBefore(now.get(Calendar.SECOND) + "", 2);
		return time;
	}

	/**
	 * ��ù̶�ʱ�䳤��֮ǰ��ʱ���ַ�
	 * 
	 * @return
	 */
	public static String getDateStringBefore(int seconds) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, -seconds);
		String time = "" + addZeroBefore(now.get(Calendar.YEAR) + "", 4)
				+ addZeroBefore((now.get(Calendar.MONTH) + 1) + "", 2)
				+ addZeroBefore(now.get(Calendar.DAY_OF_MONTH) + "", 2) + " "
				+ addZeroBefore(now.get(Calendar.HOUR_OF_DAY) + "", 2)
				+ addZeroBefore(now.get(Calendar.MINUTE) + "", 2)
				+ addZeroBefore(now.get(Calendar.SECOND) + "", 2);
		return time;
	}

	/**
	 * �Թ̶����ַ�̶����ȴ��� ���ȳ����˹̶��ĳ��ȣ����ȡǰ�ߵĹ̶����� ���Ȳ���������ǰ�油��
	 * 
	 * @param source
	 * @param size
	 * @return
	 */
	public static String addZeroBefore(String source, int size) {
		// logger.debug("����ǰ���ַ��ǣ�" + source);
		String temp = source;
		if (source.length() == size) {
		} else if (source.length() < size) {
			for (int i = 0; i < size - source.length(); i++) {
				temp = "0" + temp;
			}
		} else if (source.length() > size) {
			temp = source.substring(0, size);
		}
		// logger.debug("�������ַ��ǣ�" + temp);
		return temp;
	}

	public static void main(String[] argsq) {
	}
}
