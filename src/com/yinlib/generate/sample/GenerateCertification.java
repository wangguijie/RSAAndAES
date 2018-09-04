package com.yinlib.generate.sample;

import java.util.UUID;

import com.google.gson.Gson;
import com.yinlib.generate.utils.FileUtils;
import com.yinlib.generate.utils.LicenseInfo;
import com.yinlib.generate.utils.StringUtils;

public class GenerateCertification {
	private static final Gson sGson = new Gson();
	public static String generateLicense() {
		GenerateService.generateRootKeyPare();
		GenerateService.generateSubKeyPare();
		String rootContent = FileUtils.read("root.lic");
		String rootLic = generateRootLicense(rootContent);
		String subContent = FileUtils.read("sub.lic");
		String subLic = generateSubLicense(subContent);
		String head = generateHead(subContent);
		String content = head + StringUtils.formatContent(rootLic) + StringUtils.contentSplit() + StringUtils.formatContent(subLic) + StringUtils.headSplit();
		System.out.println("License format : \n" + content);
		return content;
	}
	
	private static String generateHead(String license){
		StringBuilder sb = new StringBuilder();
		LicenseInfo licenseInfo = sGson.fromJson(license, LicenseInfo.class);
		sb.append(StringUtils.headSplit());
		sb.append("\n#  YinLib License");
		sb.append("\n#  License Product : " + licenseInfo.getProduct());
		sb.append("\n#  Expiration : " + licenseInfo.getLimit().getExpiration().get(0) + " ~ " + licenseInfo.getLimit().getExpiration().get(1) );
		sb.append("\n#  License SN : " + UUID.randomUUID().toString());
		sb.append("\n");
		sb.append(StringUtils.headSplit());
		sb.append("\n");
		System.out.println("license head : " + sb);
		return sb.toString();
	}
	
	private static final String AESKEY = "1234567890asdfgh";
	private static String generateRootLicense(String rootContent){
		String rootPrivateKey = FileUtils.read("root.pri");
		String rootPublicKey = FileUtils.read("root.pub");
		String entryContent = "";
		try {
			entryContent = GenerateService.encrypt(rootContent, rootPrivateKey, rootPublicKey, AESKEY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entryContent;
	}
	
	public static String generateSubLicense(String subContent){
		String rootPrivateKey = FileUtils.read("root.pri");
		String rootPublicKey = FileUtils.read("root.pub");
		String entryContent = "";
		try {
			entryContent = GenerateService.encrypt(subContent, rootPrivateKey, rootPublicKey, AESKEY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entryContent;
	}
	
}
