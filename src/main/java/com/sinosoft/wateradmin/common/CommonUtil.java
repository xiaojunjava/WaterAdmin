package com.sinosoft.wateradmin.common;

import com.sinosoft.wateradmin.app.bean.Users;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *日期、字符等处理工具类
 * @author lkj.
 */
public class CommonUtil {
	private static final String dateTimeFmt="yyyy-MM-dd HH:mm:ss";
	private static final String dateFmt="yyyy-MM-dd";
	public static final SimpleDateFormat SDF_DATETIME = new SimpleDateFormat(getDateTimeFmt());
	public static final SimpleDateFormat SDF_DATE = new SimpleDateFormat(getDateFmt());
	/**
	 * 获取时间格式（带时分秒）
	 * @return
	 */
	public synchronized static String getDateTimeFmt(){
		return dateTimeFmt;
	}

	/**
	 * 获取日期格式（不带时分秒）
	 * @return
	 */
	public synchronized static String getDateFmt(){
		return dateFmt;
	}

	/**
	 * 日期转字符串
	 * @param fmt
	 * @param date
	 * @return
	 */
	public  static String getDateTimeStr(String fmt,Date date){
		SimpleDateFormat sdf =new SimpleDateFormat(fmt);
		String re=sdf.format(date);
		return re;
	}

	/**
	 * 获取当前登录用户
	 * @return
	 */
	public static Users getLoginUser(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Users user=(Users)request.getSession().getAttribute("user");
		return user;
	}

	public static void main(String[] args) {
		String timeStr="2017-10-27 08:00:00";
		String hourCode=timeStr.substring(11,13);
		System.out.println();
	}
}
