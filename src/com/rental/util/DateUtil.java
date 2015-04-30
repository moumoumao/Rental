package com.rental.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author jy
 *
 */
public class DateUtil {
	/**
	 * 格式化时间 yyyy-MM-dd HH:mm:ss
	 * @param time
	 * @return
	 */
	public static String toYMD(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		return sdf.format(time);
	}
	
	/**
	 * 格式化时间yyyyMMdd
	 * @param time
	 * @return
	 */
	public static String forProduct(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");   
		return sdf.format(time);
	}
	
	/**
	 * 格式化时间 yyyy-MM-dd
	 * @param time
	 * @return
	 */
	public static String toString(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
		return sdf.format(time);
	}
	
	/**
	 * 格式化时间yyyyMMdd
	 * @param time
	 * @return
	 */
	public static String forOrder(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");   
		return sdf.format(time);
	}
	
	/**
	 * 格式化时间yyyyMMdd
	 * @param time
	 * @return
	 */
	public static String forCreateDate(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
		return sdf.format(time);
	}
	
	/**
	 * 字符串转日期
	 * @param str
	 * @return
	 */
	public static String forDate(String str)
	{
		SimpleDateFormat ddf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String result="";
		try {
			result=sdf.format(ddf.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
}
