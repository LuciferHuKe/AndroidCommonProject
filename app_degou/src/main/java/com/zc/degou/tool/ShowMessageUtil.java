package com.zc.degou.tool;

import android.content.Context;
import android.widget.Toast;

/**
 * 
 * @ClassName: ShowMessage_Util
 * @Description: TODO(������Ϣ������)
 * @author ����
 * @date 2011-11-8 ����01:51:37
 * 
 */
public class ShowMessageUtil {
	// public static Toast getShowToast(Context context,
	// String message) {
	// Toast jjhToast = new Toast(context);
	// LayoutInflater inflater = LayoutInflater.from(context);
	// View layout = inflater.inflate(R.layout.show_head_toast, null);
	// TextView errorText =
	// (TextView)layout.findViewById(R.id.show_head_toast_text);
	// errorText.setText(message);
	// jjhToast.setGravity(Gravity.CENTER, 0, 0);
	// jjhToast.setView(layout);
	// jjhToast.setDuration(Toast.LENGTH_SHORT);
	// return jjhToast;
	// }
	
	public  static Toast instance;
	
	public static void getInstance(Context context){
		if(instance==null){
			instance = Toast.makeText(context, "", Toast.LENGTH_SHORT); 
		}
	}

	public static Toast getShowToast(Context context, String message) {
		Toast jjhToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		return jjhToast;
	}
	
	public static void show(String text){
		instance.cancel();
		instance.setText(text);
		instance.show();
	}

	/**
	 * Ĭ�ϵ�ToastЧ��
	 */
	public static Toast getToastDefault(Context context, String message,
			int time) {
		return Toast.makeText(context, message, time);
		// t.setGravity(Gravity.BOTTOM, 100 + 20, 200 + 20);
	}

	/**
	 * ������ Toast
	 * 
	 * @param context
	 */
	public static void showWaitToast(Context context) {
		String message = "Waiting";
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
