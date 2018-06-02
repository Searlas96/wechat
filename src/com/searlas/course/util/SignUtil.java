package com.searlas.course.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.xml.ws.handler.MessageContext;

public class SignUtil {
	public static String token = "weixintest";
	/*校验签名
	 * 
	 * @param signature 微信加密签名
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @return 
	 * */
	public static boolean checkSignature(String signature,String timestamp,String nonce)
	{
		//对三个按字典排序
		String[] paramArr = new String[] {token,timestamp,nonce};
		Arrays.sort(paramArr);
		String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
		String ciphertext = null;
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest =md.digest(content.toString().getBytes());
			ciphertext = byteToStr(digest);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		
		//将sha1加密后的字符串与signature进行比对
		return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
		
	}
	/*
	 * 将字节数组转换成十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 * 
	 * */
	private static String byteToStr(byte[] byteArray){
		String strDigest ="";
		for(int i=0;i<byteArray.length;i++){
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}
	/*将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 * */
	private static String byteToHexStr(byte mByte)
	{
		char[] Digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0x0F];
		tempArr[1] = Digit[mByte & 0x0f];
		String s = new String(tempArr);
		return s;
		
	}
}










