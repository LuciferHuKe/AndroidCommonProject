/**
 * @������GuoGuo
 * @����org.guoguo.server.tool.encapsulation.tcp
 * @ʱ�䣺 2012-2-20 ����06:20:35
 * @���ߣ�jliu@inforace.com.cn  ����
 */
package com.zc.degou.server.tool.encapsulation;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/** 
 * @ClassName: Base64test 
 * @Description: TODO(������һ�仰��������������) 
 * @author ���� 
 * @date 2012-2-20 ����06:20:35 
 *  
 */
public class DES {
	 private static String DESKey = "12345678"; // �ֽ��������8�ı���  
	 private static byte[] iv1 = {(byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78, (byte)0x90, (byte)0xAB, (byte)0xCD, (byte)0xEF};
	 public static void main(String[] args) {
		DES des = new DES();
	} 
	 public byte[] desEncrypt(byte[] plainText) throws Exception  
	    {  
//	        SecureRandom sr = new SecureRandom();  	        
//	        sr.setSeed(iv);
	        
//	    	 IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));  
	    	IvParameterSpec iv = new IvParameterSpec(iv1);
	    	 
	        DESKeySpec dks = new DESKeySpec(DESKey.getBytes());  
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
	        SecretKey key = keyFactory.generateSecret(dks);  
	        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");  
	        cipher.init(Cipher.ENCRYPT_MODE, key, iv);  
	        byte data[] = plainText;  
	        byte encryptedData[] = cipher.doFinal(data);  
	        return encryptedData;  
	    }  
	      
	    public String encrypt(String input)   
	    {  
	    	String result = "input";
	        try {
				result = base64Encode(desEncrypt(input.getBytes()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return result;
	    }  
	      
	    public  String base64Encode(byte[] s)   
	    {  
	        if (s == null)  
	            return null;  
	        return Base64.encodeToString(s, Base64.DEFAULT);

	    }  
}