package com.lucifer.common.util;

import com.rt.BASE64Decoder;
import com.rt.BASE64Encoder;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by lucifer on 16/9/29.
 *
 *  加密界面工具类
 */
public class EncryptDecodeUtil {

    // 加密密钥
    public static final String ENCRYPT_KEY = "hm.9986@xxsc~dig";

    /**
     * 加密--使用AES/CBC混合加密
     * @param data
     * @return
     */
    public static String encryptData(String data) {

        try {

            Security.addProvider(new BouncyCastleProvider());
            Key key = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
            Cipher in = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            byte[] iv = "1234567890123456".getBytes();
            in.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] enc = in.doFinal(data.getBytes());
            return new BASE64Encoder().encode(enc);

        } catch (Exception e) {
            LogUtil.error("加密异常:" + e.getMessage());
        }

        return "";
    }

    /**
     * 解密--使用AES/CBC混合加密的数据
     * @param data
     * @return
     */
    public static String decryptData(String data) {

        if (null == data) {
            return "";
        }

        try {

            Security.addProvider(new BouncyCastleProvider());
            Key key = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
            Cipher in = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            byte[] iv = "1234567890123456".getBytes();
            in.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] enc = in.doFinal(new BASE64Decoder().decodeBuffer(data));
            return new String(enc);
        } catch (Exception e) {
            LogUtil.error("解密异常:" + e.getMessage());
        }

        return "";
    }
}
