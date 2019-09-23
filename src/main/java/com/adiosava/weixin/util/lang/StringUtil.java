package com.adiosava.weixin.util.lang;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;

import java.text.MessageFormat;

public class StringUtil {
	private static final char A='A';
	private static final char Z='Z';
	/**
	 * 
	 * @param str   
	 * @return  
	 */
	public static String firstCharSmall(final String str){
        final char c =str.charAt(0);
        final String temp= String.valueOf(c);
        if(c>=A && c<=Z)
        	return str.replaceFirst(temp,temp.toLowerCase());  
        return str;
	}
	public static String format2(String pattern,Object... arguments){
		return MessageFormat.format(pattern, arguments);
	}
	/**
	 * index form 0.
	 * @param format   {} world,{} earth
	 * @param arguments  hello,hello
	 * @return hello world,hello earth
	 */
	public static String format(String format,Object[] arguments){
		return MessageFormatter.arrayFormat(format, arguments).getMessage();
	}
	public static String format(String format,String a){
		return format(format, new Object[] {a});
	}
	public static String format(String format,String a,String b){
		return format(format, new Object[] {a,b});
	}

	public static boolean isBlank(final CharSequence cs) {
		return StringUtils.isBlank(cs);
	}
	 public static boolean isNotBlank(final CharSequence cs) {
	        return !isBlank(cs);
	  }
	 
	 
	public static boolean equals(String a,String b){
        return a != null && a.equals(b);
    }
	public static boolean notEquals(String a, String b) {
		return !equals(a, b);
	}
	public static boolean ingoreEquals(String a, String b) {
		return a.equalsIgnoreCase(b);
	}
}
