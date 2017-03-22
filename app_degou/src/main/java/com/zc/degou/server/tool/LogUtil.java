package com.zc.degou.server.tool;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * ��־�ļ�
* @ClassName: LogUtil 
* @Description: TODO(������һ�仰��������������) 
* @author ����
* @date 2011-10-28 ����02:39:22 
*
 */
public class LogUtil {
	private static String LOG_TAG="com.flowertreeinfo";
	/**
	 * �����쳣��־
	 * @param context
	 * @param message
	 */
	public static void debug(Context context,String message) {
//		String className = context.getClass().getName().toString();
//		String msg ="\t [" + getSysDate() + "]\t"+"Debug:" + className +"\t" + message;
//		Log.d(LOG_TAG,msg);
	}
	/**
	 * �����쳣��־
	 * @param context
	 * @param message
	 */
	public static void error(Context context,String message) {
//		String className = context.getClass().getName().toString();
//		String msg ="\t [" + getSysDate() + "]\t"+"Error:" + className +"\t" + message;
//		Log.e(LOG_TAG,msg);
	}
	/**
	 * �����쳣��־
	 * @param context
	 * @param message
	 */
	public static void error(Context context,String message, Throwable e) {
//		String className = context.getClass().getName().toString();
//		String msg ="\t [" + getSysDate()+ "]\t"+"Error:" + className +"\t" + message;
//		Log.e(LOG_TAG,message, e);
	}
	/**
	 * �����־
	 * @param context
	 * @param message
	 */
	public  static void info(Context context,String message) {
//		String className = context.getClass().getName().toString();
//		String msg ="\t [" + getSysDate() + "]\t"+"Info:" + className +"\t" + message;
//		Log.v(LOG_TAG,msg);
	}
	
	/**
	 * �澯��־
	 * @param context
	 * @param message
	 */
	public static void warning(Context context,String message) {
		String className = context.getClass().getName().toString();
		String msg ="\t [" + getSysDate() + "]\t"+"Warning:" + className +"\t" + message;
		Log.w(LOG_TAG,msg);
	}
	/**
	 * �õ�ϵͳ����
	 * @return String
	 */
    public final static String getSysDate() {
    	String ret="";
    	try
        {
    		Date date = new Date();
    		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
    		ret = df.format(date.getTime());
        }catch(Exception e)
        {
			ret="20130701000000";
            e.printStackTrace();
        }
		return ret;
	}
}
