package com.yinlib.generate.sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.google.gson.Gson;
import com.yinlib.generate.AES;
import com.yinlib.generate.Base64;
import com.yinlib.generate.EncryptionUtil;
import com.yinlib.generate.RSA;
import com.yinlib.generate.utils.LicenseContent;
import com.yinlib.generate.utils.StringUtils;

public class GenerateService {
	private static Gson sGson = new Gson();
	public static boolean generateRootKeyPare(){
		File rootCertificatePub = new File("root.pub");
		File rootCertificatePri = new File("root.pri");
		if(rootCertificatePri.exists() && rootCertificatePub.exists() && rootCertificatePri.isFile() && rootCertificatePub.isFile()){
			return true;
		}
		try {
			Map<String, String> keyPair = RSA.generateKeyPair();
			if(keyPair == null || keyPair.size() == 0){
				return false;
			}
			FileOutputStream outputPub = new FileOutputStream(rootCertificatePub);
			outputPub.write(keyPair.get("publicKey").getBytes());
			outputPub.close();
			FileOutputStream outputPri = new FileOutputStream(rootCertificatePri);
			outputPri.write(keyPair.get("privateKey").getBytes());
			outputPri.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		return true;
	}
	
	public static boolean generateSubKeyPare(){
		File subCertificatePub = new File("sub.pub");
		File subCertificatePri = new File("sub.pri");
		if(subCertificatePri.exists() && subCertificatePub.exists() && subCertificatePri.isFile() && subCertificatePub.isFile()){
			return true;
		}
		try {
			Map<String, String> keyPair = RSA.generateKeyPair();
			if(keyPair == null || keyPair.size() == 0){
				return false;
			}
			FileOutputStream outputPub = new FileOutputStream(subCertificatePub);
			outputPub.write(keyPair.get("publicKey").getBytes());
			outputPub.close();
			FileOutputStream outputPri = new FileOutputStream(subCertificatePri);
			outputPri.write(keyPair.get("privateKey").getBytes());
			outputPri.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		return true;
	}
	
	public static final String encrypt(String content, String privateKey, String publicKey, String aesKey) throws Exception{
		if(!StringUtils.isNotEmpty(content) || !StringUtils.isNotEmpty(privateKey) || !StringUtils.isNotEmpty(publicKey) || !StringUtils.isNotEmpty(aesKey)){
			return null;
		}
		content = StringUtils.replaceBlank(content);
		String rsaContent = encrypt(content, privateKey, publicKey);
		System.out.println("encrypt rsa content : " + rsaContent.length());
		String encryptContent = AES.encryptToBase64(rsaContent, aesKey);
		System.out.println("encrypt aes content : " + encryptContent);
		return encryptContent;
	}
	
	public static final String encrypt(String content, String privateKey, String publicKey) throws Exception{
		if(!StringUtils.isNotEmpty(content) || !StringUtils.isNotEmpty(privateKey) || !StringUtils.isNotEmpty(publicKey)){
			return null;
		}
		String rsaData = RSA.sign(content, privateKey);
		LicenseContent licenseContent = new LicenseContent();
		licenseContent.setMessage(content);
		licenseContent.setPublicKey(publicKey);
		licenseContent.setMessageDigest(rsaData);
		String objectStr = StringUtils.replaceBlank(sGson.toJson(licenseContent));
		System.out.println("encrypt rsa content : " + objectStr);
		return objectStr;
	}
	
	
}
