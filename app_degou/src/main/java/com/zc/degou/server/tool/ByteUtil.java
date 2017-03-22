package com.zc.degou.server.tool;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteUtil {
	
	public static byte[] integerToBytes(int integer, int len) 
	{
		//   if (integer < 0) { throw new IllegalArgumentException("Can not cast negative to bytes : " + integer); }
		   ByteArrayOutputStream bo = new ByteArrayOutputStream();
		   for (int i = 0; i < len; i ++) {   
		    bo.write(integer);
		    integer = integer >> 8;
		   }
		   return bo.toByteArray();
		}

	public static byte[] readBytes(InputStream in, long length) throws IOException 
	{
	   ByteArrayOutputStream bo = new ByteArrayOutputStream();
	   byte[] buffer = new byte[1024];
	   int read = 0;
	   while (read < length) {
	    int cur = in.read(buffer, 0, (int)Math.min(1024, length - read));
	    if (cur < 0) 
	    {
	    	break;
	    }
	    read += cur;
	    bo.write(buffer, 0, cur);
	   }
	   return bo.toByteArray();
	}

//	public static int bytesToInteger(byte[] bytes)
//	{ return bytesToInteger(bytes, 0, bytes.length); }

/*	public static int unsignedByteToInt(byte b)
	{
		return (int) b&0xFF;
	}
	
	public static final int bytesToInteger(byte[] b) 
	{
	    int i = 0;
//	    int pos=0;
	    i = i + unsignedByteToInt(b[0]) << 24;
	    i = i + unsignedByteToInt(b[1]) << 16;
	    i = i + unsignedByteToInt(b[2]) << 8;
	    i = i + unsignedByteToInt(b[3]) << 0;
	    
	    return i;
	}
*/	
	
	/**  
	  * ��intתΪ���ֽ���ǰ�����ֽ��ں��byte����  
	  * @param n int  
	  * @return byte[]  
	  */  
	public static byte[] toLH(int n) {   
	  byte[] b = new byte[4];   
	  b[0] = (byte) (n & 0xff);   
	  b[1] = (byte) (n >> 8 & 0xff);   
	  b[2] = (byte) (n >> 16 & 0xff);   
	  b[3] = (byte) (n >> 24 & 0xff);   
	  return b;   
	}    
	  
	/**  
	  * ��intתΪ���ֽ���ǰ�����ֽ��ں��byte����  
	  * @param n int  
	  * @return byte[]  
	  */  
	public static byte[] toHH(int n) {   
	  byte[] b = new byte[4];   
	  b[3] = (byte) (n & 0xff);   
	  b[2] = (byte) (n >> 8 & 0xff);   
	  b[1] = (byte) (n >> 16 & 0xff);   
	  b[0] = (byte) (n >> 24 & 0xff);   
	  return b;   
	}    
	
	
	
	public static final int bytesToInteger(byte[] b) 
	{
		int mask=0xFF;
		int temp=0;
		int n=0,j=3;
		for (int i=0;i<4;i++)
		{
			n<<=8;
			temp=b[j-i]&mask;
			n|=temp;
		}
		return n;
	}

	/**  
	  * �����ֽ�����ת��Ϊint  
	  * @param b byte[]  
	  * @return int  
	  */  
	public static int hBytesToInt(byte[] b) {   
	  int s = 0;   
	  for (int i = 0; i < 3; i++) {   
	    if (b[i] >= 0) {   
	    s = s + b[i];   
	    } else {   
	    s = s + 256 + b[i];   
	    }   
	    s = s * 256;   
	  }   
	  if (b[3] >= 0) {   
	    s = s + b[3];   
	  } else {   
	    s = s + 256 + b[3];   
	  }   
	  return s;   
	}    
	  
	/**  
	  * �����ֽ�����ת��Ϊint  
	  * @param b byte[]  
	  * @return int  
	  */  
	public static int lBytesToInt(byte[] b) {   
	  int s = 0;   
	  for (int i = 0; i < 3; i++) {   
	    if (b[3-i] >= 0) {   
	    s = s + b[3-i];   
	    } else {   
	    s = s + 256 + b[3-i];   
	    }   
	    s = s * 256;   
	  }   
	  if (b[0] >= 0) {   
	    s = s + b[0];   
	  } else {   
	    s = s + 256 + b[0];   
	  }   
	  return s;   
	}    
	  
	public static int ByteArraytoLong(byte[] b)  { 
        int iOutcome = 0; 
        byte bLoop; 
        for (int i = 0; i < 4; i++)  { 
                bLoop = b[i]; 
                iOutcome += (bLoop & 0xff) << (4 * i); 
        } 
        return iOutcome; 
    } 	
	
}
