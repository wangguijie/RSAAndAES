package com.yinlib.generate.sample;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.yinlib.generate.AES;
import com.yinlib.generate.RSA;
import com.yinlib.generate.utils.LicenseContent;
import com.yinlib.generate.utils.LicenseInfo;
import com.yinlib.generate.utils.StringUtils;

public class CertificationAuthority {
	private static final String AESKEY = "1234567890asdfgh";
	private static final Gson sGson = new Gson();
	public static boolean verifyLicense(String content){
		if(!StringUtils.isNotEmpty(content)){
			return false;
		}
		splitLicense(content);
		String[] licenseChain = splitLicense(content);
		if(licenseChain == null || licenseChain.length == 0){
			return false;
		}
		boolean isPass = false;
		try{
			isPass = checkLicenseChain(licenseChain);
		}catch(Exception e){
			//e.printStackTrace();
			isPass = false;
		}
		System.out.println("checkLicenseChain : " + isPass);
		return isPass;
	}
	
	private static boolean checkLicenseChain(String[] licenseChain) {
		if(licenseChain == null || licenseChain.length == 0){
			return false;
		}
		int length = licenseChain.length;
		List<LicenseContent> licenseContents = new ArrayList<LicenseContent>(length); 
		List<LicenseInfo> licenseInfos = new ArrayList<LicenseInfo>(length); 
		for(int i = 0; i < length; i++){
			String content = AES.decryptFromBase64(licenseChain[i], AESKEY);
			LicenseContent licenseContent = sGson.fromJson(content, LicenseContent.class);
			System.out.println(content);
			if(licenseContent == null){
				return false;
			}
			licenseContents.add(licenseContent);
			boolean isSignCheckPass = RSA.checkSign(licenseContent.getMessage(), licenseContent.getMessageDigest(), licenseContent.getPublicKey());
			if(!isSignCheckPass){
				return false;
			}
			LicenseInfo licenseInfo = sGson.fromJson(licenseContent.getMessage(), LicenseInfo.class);
			if(licenseInfo == null){
				return false;
			}
			licenseInfos.add(licenseInfo);
			System.out.println("[" + i + "] : " + isSignCheckPass);
		}
		boolean status = checkLicenseChainPermission(licenseInfos);
		return status;
	}

	private static boolean checkLicenseChainPermission(
			List<LicenseInfo> licenseInfos) {
		//check head time / product
		//check child license time/appid/count and so on permission
		return true;
	}

	private static String[] splitLicense(String content){
		if(!StringUtils.isNotEmpty(content)){
			return null;
		}
		String[] childContent = content.split(StringUtils.headSplit());
		if(childContent == null || childContent.length < 3){
			return null;
		}
		int length = childContent.length;
		String[] licenseContent = childContent[2].split(StringUtils.contentSplit());
		if(licenseContent == null || licenseContent.length < 1){
			return null;
		}
		length = licenseContent.length;
		String[] licenseChain = new String[length];
		for(int i = 0; i < length; i++){
			licenseChain[i] = StringUtils.replaceBlank(licenseContent[i]);
		}
		return licenseChain;
	}
}
