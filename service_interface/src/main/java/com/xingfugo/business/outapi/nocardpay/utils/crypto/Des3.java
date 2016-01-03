package com.xingfugo.business.outapi.nocardpay.utils.crypto;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.xingfugo.business.outapi.nocardpay.utils.encoding.Base16;
public class Des3 {

    public static String des3EncodeECB(String key, String data)  
            throws Exception {  
        Key deskey = null;  
        DESedeKeySpec spec = new DESedeKeySpec(Base16.decode(key));  
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");  
        deskey = keyfactory.generateSecret(spec);  
        Cipher cipher = Cipher.getInstance("DESede" + "/ECB/NoPadding");  
        cipher.init(Cipher.ENCRYPT_MODE, deskey);  
//        byte[] bytes;
//        byte[] vvv=data.getBytes("gb2312");
//        int d=vvv.length%8;
//        if(d!=0)
//        {
//        	bytes=new byte[vvv.length+8-d];
//        	System.arraycopy(vvv, 0, bytes, 0, vvv.length);
//        	bytes[bytes.length-1]=(byte) (8-d);
//        	
//        }else
//        {
//        	bytes=new byte[vvv.length];
//        	System.arraycopy(vvv, 0, bytes, 0, vvv.length);
//        }
        byte[] bOut = cipher.doFinal(data.getBytes("gb2312"));  
        return Base16.encode(bOut);  
    }  
  
    public static String ees3DecodeECB(String key, String data)  
            throws Exception {  
        Key deskey = null;  
        DESedeKeySpec spec = new DESedeKeySpec(Base16.decode(key));  
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");  
        deskey = keyfactory.generateSecret(spec);  
        Cipher cipher = Cipher.getInstance("DESede" + "/ECB/NoPadding");  
        cipher.init(Cipher.DECRYPT_MODE, deskey);  
        byte[] bOut = cipher.doFinal(Base16.decode(data));  
        return new String(bOut,"gb2312");  
    }  
   
    
    public static void main(String[] args) throws Exception {
		String key="313131313131313131313131313131313131313131313131";  //3131313131313131
		String data="1111111111111111";
		String vv=Des3.des3EncodeECB(key,data);
		System.out.println(vv);
		System.out.println(vv.length());
		String dd=Des3.ees3DecodeECB(key,vv);
		System.out.println(dd);
		
	}
    
}  

