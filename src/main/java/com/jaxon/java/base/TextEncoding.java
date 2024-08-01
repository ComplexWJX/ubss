package com.jaxon.java.base;

import java.io.UnsupportedEncodingException;

public class TextEncoding {

	public static void main(String[] args) {

		String str = method("中国");
		System.out.println(str);
	}
	static String method(String str){
		String newStr;
		try {
			newStr = new String(str.getBytes("UTF-8"), "GBK");
			return newStr;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
