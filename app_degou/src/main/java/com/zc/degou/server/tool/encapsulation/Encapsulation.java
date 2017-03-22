package com.zc.degou.server.tool.encapsulation;

import com.zc.degou.server.tool.ZipUtils;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class Encapsulation {
	public static byte[] encapsulate(String data) {
		byte[] ret = null;
		try {
//			SecureRandom sr = new SecureRandom();
//
//			KeyGenerator kg = KeyGenerator.getInstance("DES");
//			kg.init(sr);
//
//			SecretKey key = kg.generateKey();

			byte rawKeyData[] = "11111111".getBytes();
			
			byte[] encryptedData = EncryptDecryptData.encrypt(rawKeyData, data);
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			bo.write(rawKeyData);
			bo.write(encryptedData);
			ret = bo.toByteArray();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	
	public static String antiEncapsulation(byte[] data) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
		String ret = null;

		byte[] key = new byte[8];
		byte[] strData = new byte[data.length - 8];
		for (int i = 0; i < data.length; i++) {
			if (i < 8) {
				key[i] = data[i];
			} else {
				strData[i - 8] = data[i];
			}

		}
		byte [] ret2;
		String gunzip = "";
		if("11111111".equalsIgnoreCase(new String(key))){
			gunzip = ZipUtils.gunzip(EncryptDecryptData.decrypt(key,strData));
			ret2 = gunzip.getBytes();
		}else{
			ret2 = strData.clone();
		}
		try {
			ret = EncryptDecryptData.decrypt(key, ret2);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ret;
	}

	
}
