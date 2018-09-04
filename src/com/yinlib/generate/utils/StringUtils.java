package com.yinlib.generate.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	private static final int MAX_LINE = 60;
	public static boolean isNotEmpty(String str){
		if(str == null || str.length() == 0){
			return false;
		}
		return true;
	}
	
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	
	public static String headSplit(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < MAX_LINE; i++){
			sb.append("#");
		}
		return sb.toString();
	}
	
	public static String contentSplit(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < MAX_LINE; i++){
			sb.append("-");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	public static String formatContent(String content){
		if(!StringUtils.isNotEmpty(content)){
			return null;
		}
		int left = content.length() % MAX_LINE;
		int line = content.length() / MAX_LINE;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < line; i++){
			sb.append(content.substring(i*MAX_LINE, (i+1) * MAX_LINE));
			sb.append("\n");
		}
		if(left != 0){
			sb.append(content.substring(line * MAX_LINE, content.length()));
			sb.append("\n");
		}
		return sb.toString();
	}
}
