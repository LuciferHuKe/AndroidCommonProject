/**
 * @������GuoGuo
 * @����org.guoguo.tool
 * @ʱ�䣺 2011-10-28 ����02:59:10
 * @���ߣ�jliu@inforace.com.cn  ����
 */
package com.zc.degou.tool;

import java.util.Calendar;

/**
 * @ClassName: StringUtil
 * @Description: TODO(String������)
 * @author ����
 * @date 2011-10-28 ����02:59:10
 * 
 */
public class StringUtil {


	/**
	 * �Ƿ񱣳��û��� key
	 */
	public static final String IS_USER_NAME = "is_save_uname";

	/**
	 * �Ƿ񱣳����� key
	 */
	public static final String IS_PASSWORD = "is_save_pwd";

	/**
	 * �û��� key
	 */
	public static final String USERNAME = "username";

	/**
	 * ���� key
	 */
	public static final String PASSWORD = "password";
	
	
	
	/**
	 * ����ǰʱ��ת����Ϊ��ݿ�֧�ֵ��ַ���ʽ
	 * @return
	 */
	public static String getDateStringForDB() {
		Calendar now = Calendar.getInstance();
		String time = ""+now.get(Calendar.YEAR) + (now.get(Calendar.MONTH) + 1)
				+ now.get(Calendar.DAY_OF_MONTH) + " "
				+ now.get(Calendar.HOUR_OF_DAY) + now.get(Calendar.MINUTE)
				+ now.get(Calendar.SECOND);
		return time;
	}

	public static void main(String[] argsq) {
		getDateStringForDB();
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
				+ addZeroBefore(now.get(Calendar.DAY_OF_MONTH) + "", 2)
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
	
//	public static ServerData GetServerData(String data, int flag)
//	{
//		ServerData returnData = new ServerData();
//		returnData.setFlag(flag);
//
//		int mapsIndex = 0;
//
//		String[] allData = data.split(MAP_SEPERATOR);
//		System.out.println("ȫ����ݵ�ѭ�������ǣ�" + allData.length);
//		for (int i = 1; i <= allData.length; i++) {
//			String tempMap = allData[i - 1];
//			System.out.println("ѭ������"
//					+ i + ">>" + tempMap);
//			if (tempMap == null
//					|| "".equalsIgnoreCase(tempMap
//							.trim())) {
//				continue;
//			}
//			System.out.println("�������̣߳������" + mapsIndex + "-->"
//							+ tempMap);
//			HashMap<String, String> dataMap = new HashMap<String, String>();
//			StringTokenizer stMap = new StringTokenizer(
//					tempMap, PARA_SEPERATOR);
//			while (stMap.hasMoreTokens()) {
//				String tempValue = stMap.nextToken();
//				System.out.println("����" + tempValue);
//
//				if (tempValue == null
//						|| ""
//								.equalsIgnoreCase(tempValue
//										.trim())) {
//					continue;
//				}
//				if (tempValue.indexOf(PARA_EQUALS) < 0) {
//					System.out.println("û�в���ָ��");
//					continue;
//				}
//				String dataKey = tempValue.substring(0,
//						tempValue.indexOf(PARA_EQUALS))
//						.trim();
//				String dataValue = tempValue
//						.substring(
//								tempValue
//										.indexOf(PARA_EQUALS) + 1)
//						.trim();
//				System.out.println("�������̣߳�����" + dataKey
//								+ "����ֵ�ǣ�<" + dataValue
//								+ ">");
//				dataMap.put(dataKey, dataValue);
//			}
//			// System.out.println("jiangjianhao 2011 12 27 add2");
//			// System.out.println(dataMap.get("channel_id"));
//			// System.out.println(dataMap.get("channel_name"));
//			// System.out.println("jiangjianhao 2011 12 27 end2");
//			returnData.addData(dataMap);
//		}
//
//		return returnData;
//	}
	
    /**
     * 生成14位时间
     */
    public static String getCurrentTime()
    {
        Calendar now = Calendar.getInstance();
        String time = ""
                + StringUtil.addZeroBefore(now.get(Calendar.YEAR) + "", 4)
                + StringUtil.addZeroBefore((now.get(Calendar.MONTH) + 1) + "",
                        2)
                + StringUtil.addZeroBefore(now.get(Calendar.DAY_OF_MONTH) + "",
                        2)
                + StringUtil.addZeroBefore(now.get(Calendar.HOUR_OF_DAY) + "",
                        2)
                + StringUtil.addZeroBefore(now.get(Calendar.MINUTE) + "", 2)
                + StringUtil.addZeroBefore(now.get(Calendar.SECOND) + "", 2);
        return time;
    }
	
	/**
	 * ͨ��14λ�ַ���ʱ��
	 * 
	 * @param time
	 * @return
	 */
	public static Calendar getDateStringForDB(String time) {
		if (time == null || time.length() != 14) {
			return null;
		}
		Calendar tempCalendar = Calendar.getInstance();
		try {

			int year = Integer.parseInt(time.substring(0, 4));
			int month = Integer.parseInt(time.substring(4, 6));
			int day = Integer.parseInt(time.substring(6, 8));
			int hour = Integer.parseInt(time.substring(8, 10));
			int minute = Integer.parseInt(time.substring(10, 12));
			int second = Integer.parseInt(time.substring(12, 14));
			tempCalendar.set(year, month, day, hour, minute, second);
		} catch (Exception e) {
			return null;
		}
		return tempCalendar;
	}

	/**
	 * ���д��� �������
	 */
	public static String cityCode[] = { "39930000,116279998",// ����
			"31399999,121470001",// �Ϻ�
			"39099998,117169998",// ���
			"29520000,106480003",// ����
			"39669998,118150001",// ��ɽ
			"38029998,114419998",// ʯ��ׯ
			"38900001,121629997",// ����
			"45750000,126769996",// �����
			"20030000,110349998",// ����
			"43900001,125220001",// ����
			"28229999,112870002",// ��ɳ
			"30670000,104019996",// �ɶ�
			"26079999,119279998",// ����
			"23129999,113319999",// ����
			"26579999,106720001",// ����
			"30229999,120169998",// ����
			"31870000,117230003",// �Ϸ�
			"40819999,111680000",// ���ͺ���
			"36680000,116980003",// ����
			"25020000,102680000",// ����
			"29657589,91132050",// ����
			"36040000,103879997",// ����
			"28600000,115919998",// �ϲ�
			"32000000,118800003",// �Ͼ�
			"22819999,108349998",// ����
			"36069999,120330001",// �ൺ
			"22549999,114099998",// ����
			"41770000,123430000",// ����
			"37779998,112550003",// ̫ԭ
			"43779998,87620002",// ��³ľ��
			"30620000,114129997",// �人
			"34299999,108930000",// ����
			"36619998,101769996",// ����
			"24479999,118080001",// ����
			"34279998,117150001",// ����
			"38479999,106220001",// ��
			"34720001,113650001"// ֣��
	};

	/**
	 * ��֤�����Ƿ�Ϸ�
	 * 
	 * @param umail
	 * @return
	 */
	public static boolean emailRule(String umail) {
		boolean result = false;
		String reg = "[a-zA-Z0-9][a-zA-Z0-9._-]{2,16}[a-zA-Z0-9]@[a-zA-Z0-9]+.[a-zA-Z0-9]+";
		if (umail.matches(reg)) {
			result = true;
		} else {
			result = false;
		}

		return result;
	}

	/**
	 * ��������ݿ��е�ʱ���ַ� ת���ɺ�̨��ʽ
	 * @param sourceDate
	 * @return
	 */
	public static String changeDataFormat(String sourceDate){
		if(sourceDate.length()==19){
			StringBuffer sb = new StringBuffer();
			sb.append(sourceDate.subSequence(0, 4));
			sb.append(sourceDate.subSequence(5, 7));
			sb.append(sourceDate.subSequence(8, 10));
			sb.append(sourceDate.subSequence(11, 13));
			sb.append(sourceDate.substring(14, 16));
			sb.append(sourceDate.subSequence(17, 19));
			return sb.toString();
		} else {
			return null;
		}
	}
	
}
