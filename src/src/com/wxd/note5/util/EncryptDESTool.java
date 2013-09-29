package com.wxd.note5.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
 
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.net.BaseEndpoint;
import org.springframework.stereotype.Component;

/**
 * 采用DES算法进行加密的工具
 * 
 * @author 王旭东
 * @version 1.0 2013年8月5日 下午9:40:20
 *
 */
@Component
public class EncryptDESTool {

	//SecretKey 负责保存对称密钥
	private SecretKey deskey;
	//Cipher负责完成加密或解密工作
	private Cipher c;
	//该字节数组负责保存加密的结果
	private byte[] cipherByte;
	
	private static final String ENCODING = "UTF-8";
	
	public EncryptDESTool() throws Exception{
		this(null);
	}
	
	
	public EncryptDESTool(SecretKey keys) throws Exception{
		this.deskey = keys;
		
		if(keys == null){
			//读取秘钥
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/wxd/note5/util/deskeys.data");
			ObjectInputStream ois = new ObjectInputStream(is);
			Object obj = ois.readObject();
			this.deskey = (SecretKey) obj;
			ois.close();
		}
		
		//生成Cipher对象,指定其支持的DES算法
		c = Cipher.getInstance("DES");
	}
	
	/**
	 * 对字符串加密
	 * 
	 * @param str
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] encrypte(byte[] src) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		// 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
		c.init(Cipher.ENCRYPT_MODE, deskey);
		
		// 加密，结果保存进cipherByte
		cipherByte = c.doFinal(src);
		return cipherByte;
	}

	/**
	 * 对字符串解密
	 * 
	 * @param buff 
	 */
	public byte[] decrypte(byte[] buff) throws InvalidKeyException,IllegalBlockSizeException, BadPaddingException {
		if(buff == null ||  buff.length == 0) return null; 
		
		// 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
		c.init(Cipher.DECRYPT_MODE, deskey);
		cipherByte = c.doFinal(buff);
		return cipherByte;
	}
	
	/**
	 * 加密字符串，返回加密后的字符串
	 * @param plainText
	 * @return
	 * @throws Exception 
	 */
	public String encrypt(String plainText) throws Exception{
		if(plainText == null || "".equals(plainText.trim())) return "";
		
		byte[] plainBytes = plainText.getBytes(ENCODING);
		byte[] cipherBytes = this.encrypte(plainBytes);
		
		String base64Str = new String(Base64.encodeBase64(cipherBytes),ENCODING);
		return base64Str;
	}
	
	public String decrypt(String cipherText) throws Exception{
		if(cipherText == null || "".equals(cipherText.trim())) return "";
		
		byte[] cipherBytes = Base64.decodeBase64(cipherText.getBytes(ENCODING));
		byte[] plainBytes = this.decrypte(cipherBytes);
		
		return new String(plainBytes,ENCODING);
	}
 
	public static void main(String args[]) throws NoSuchAlgorithmException, Exception, IOException{
		 
		//t javax.crypto.KeyGenerator keygen = KeyGenerator.getInstance("DES");
		//生成密钥
		//SecretKey deskey = keygen.generateKey();
		
		EncryptDESTool tool = new EncryptDESTool();
		String msg = "王旭东";
	   String cipher = tool.encrypt(msg);
		
		System.out.println(tool.decrypt(cipher));
	}
}
