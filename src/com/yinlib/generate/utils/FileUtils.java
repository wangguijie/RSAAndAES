package com.yinlib.generate.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;

public class FileUtils {
	private static final Logger log = Logger.getLogger(FileUtils.class);
	public static void wirte(String path, byte[] data){
		if(path == null || data == null){
			log.error("path or data is null!");
			return;
		}
		File file = new File(path);
		if(file.exists() && file.isFile()){
			return;
		}
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(file);
			output.write(data);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(output != null){
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String read(String path){
		if(path == null){
			log.error("path is null!");
			return null;
		}
		File file = new File(path);
		if(!file.exists() || !file.isFile()){
			return null;
		}
		FileInputStream input = null;
		byte[] content = null;
		try {
			input = new FileInputStream(file);
			int length = input.available();
			if(length == 0){
				return null;
			}
			content = new byte[length];
			input.read(content);
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(input != null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new String(content);
	}
}
