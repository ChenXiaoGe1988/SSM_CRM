package com.ckb.ssm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
	/**
	 * 获取系统当前时间
	 * @return
	 */
	public static String getSysTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}

	public static String formatDate(Date date, String format) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date != null) {
			result = sdf.format(date);
		}
		return result;
	}


	public static Date formatString(String str, String format) throws Exception {
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(str);
	}

}
