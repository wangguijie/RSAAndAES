package com.yinlib.generate;

import java.util.Map;
import java.util.TreeMap;
import org.apache.log4j.Logger;

import com.yinlib.generate.utils.StringUtils;

public class EncryUtil {
	private static final Logger log = Logger.getLogger(EncryUtil.class);

	public static String handleRSA(TreeMap<String, Object> map,
			String privateKey) {
		StringBuffer sbuffer = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sbuffer.append(entry.getValue());
		}
		String signTemp = sbuffer.toString();

		String sign = "";
		if (StringUtils.isNotEmpty(privateKey)) {
			sign = RSA.sign(signTemp, privateKey);
		}
		return sign;
	}

	/**
	 * 返回的结果进行验�?
	 * 
	 * @param data
	 *            业务数据密文
	 * @param encrypt_key
	 *            对ybAesKey加密后的密文
	 * @param clientPublicKey
	 *            客户端公�?
	 * @param serverPrivateKey
	 *            服务器私�?
	 * @return 验签是否通过
	 * @throws Exception
	 */
	public static boolean checkDecryptAndSign(String data, String encrypt_key,
			String clientPublicKey, String serverPrivateKey) throws Exception {
		
//		String AESKey = "";
//		try {
//			AESKey = RSA.decrypt(encrypt_key, serverPrivateKey);
//		} catch (Exception e) {
//			e.printStackTrace();
//			/** AES密钥解密失败 */
//			log.error(e.getMessage(), e);
//			return false;
//		}
//
//		/** 2.用aeskey解开data。取得data明文 */
//		String realData = AES.decryptFromBase64(data, AESKey);
//		
//		TreeMap<String, String> map = JSON.parseObject(realData,
//				new TypeReference<TreeMap<String, String>>() {
//				});
//
//		/** 3.取得data明文sign�? */
//		String sign = StringUtils.trimToEmpty(map.get("sign"));
//
//		/** 4.对map中的值进行验�? */
//		StringBuffer signData = new StringBuffer();
//		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
//		while (iter.hasNext()) {
//			Entry<String, String> entry = iter.next();
//
//			/** 把sign参数隔过�? */
//			if (StringUtils.equals((String) entry.getKey(), "sign")) {
//				continue;
//			}
//			signData.append(entry.getValue() == null ? "" : entry.getValue());
//		}
//		
//		/** 5. result为true时表明验签�?�过 */
//		boolean result = RSA.checkSign(signData.toString(), sign,
//				clientPublicKey);
//		return result;
		return true;
	}

	/**
	 * 生成hmac
	 */
	public static String handleHmac(TreeMap<String, String> map, String hmacKey) {
		StringBuffer sbuffer = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sbuffer.append(entry.getValue());
		}
		String hmacTemp = sbuffer.toString();

		String hmac = "";
		if (StringUtils.isNotEmpty(hmacKey)) {
			hmac = Digest.hmacSHASign(hmacTemp, hmacKey, Digest.ENCODE);
		}
		return hmac;
	}
}
