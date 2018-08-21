package com.yinlib.generate.utils;

public class StringUtils {
	public static boolean isNotEmpty(String str){
		if(str == null || str.length() == 0){
			return false;
		}
		return true;
	}
}
