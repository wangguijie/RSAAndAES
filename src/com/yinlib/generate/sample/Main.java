package com.yinlib.generate.sample;


/**
 *
 * Description: AES+RSA
 *
 */
public class Main 
{	
	public static void main(String[] args) throws Exception {
		String content = GenerateCertification.generateLicense();
//		content = content.replaceFirst("Q", "q");
		CertificationAuthority.verifyLicense(content);
	}
}

//static{
//	TreeMap<String, Object> params = new TreeMap<String, Object>();
//	params.put("userid", "152255855");
//	params.put("phone", "18965621420");
//	byte[] rootLic = FileUtils.read("root.lic");
//	String rootContent = new String(rootLic);
//	System.out.print(new String(rootContent));
//	LicenseInfo jsonRootBean = sGson.fromJson(rootContent, LicenseInfo.class);
//	String objectStr = sGson.toJson(jsonRootBean);
//	System.out.print(new String(rootContent));
//}
