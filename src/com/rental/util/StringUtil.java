package com.rental.util;
/*
 * String工具
 */
public class StringUtil {
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.length() == 0 || str.trim().equals(""))
			return true;

		else
			return false;
	}

	public static int StringToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static String getValiCode() {
		return String.valueOf(System.nanoTime()).substring(6, 12);
	}
	
	public static Long StringToLong(String str)
	{
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			return 0l;
		}		
	}
}
