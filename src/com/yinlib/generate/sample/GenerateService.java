package com.yinlib.generate.sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.yinlib.generate.RSA;

public class GenerateService {
	
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
			outputPub.write(keyPair.get("publicKey"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
