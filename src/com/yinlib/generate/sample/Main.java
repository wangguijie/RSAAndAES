package com.yinlib.generate.sample;

import java.util.TreeMap;

import com.yinlib.generate.utils.FileUtils;

/**
 *
 * Description: AES+RSA
 *
 */
public class Main 
{	
	public static void main(String[] args) throws Exception {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("userid", "152255855");
		params.put("phone", "18965621420");
		FileUtils.wirte("out", "data".getBytes());
	}
}
