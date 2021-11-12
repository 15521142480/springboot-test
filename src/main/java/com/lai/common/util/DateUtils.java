package com.lai.common.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作辅助类
 *
 * @author tymon
 * @version 2016年9月10日
 */
public final class DateUtils {
	private DateUtils() {
	}

	/**
	 * 秒的毫秒数
	 */
	public final static long SECOND_MILLIS = 1000;

	/**
	 * 分钟的毫秒数
	 */
	public final static long MIN_MILLIS = 60 * SECOND_MILLIS;

	/**
	 * 小时的毫秒数
	 */
	public final static long HOUR_MILLIS = 60 * MIN_MILLIS;

	/**
	 * 天的毫秒数
	 */
	public final static long DAY_MILLIS = 24 * HOUR_MILLIS;

	/**
	 * 拼接的开始时间
	 */
	public final static String START_TIME = " 00:00:00";

	/**
	 * 拼接的结束时间
	 */
	public final static String END_TIME = " 23:59:59";


	static String PATTERN = "yyyy-MM-dd";

	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

	/**
	 * yyyyMMddHHmmss
	 */
	public static final String DATE_PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyyMMddHHmmss";


	/**
	 * yyyyMMdd
	 */
	public static final String DATE_PATTERN_YYYYMMDD = "yyyyMMdd";

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_PATTERN_YYYY_MM_DDHHMMSS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String DATE_PATTERN_YYYY_MM_DDHHMM = "yyyy-MM-dd HH:mm";

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String DATE_PATTERN_HH_MM_SS = "HH:mm:ss";

	/**
	 * 格式化日期
	 *
	 * @param date
	 * @return
	 */
	public static final String format(Object date) {
		return format(date, PATTERN);
	}

	/**
	 * 格式化日期
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final String format(Object date, String pattern) {
		if (date == null) {
			return null;
		}
		if (pattern == null) {
			return format(date);
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 获取日期
	 *
	 * @return
	 */
	public static final String getDate() {
		return format(new Date());
	}

	/**
	 * 获取日期时间
	 *
	 * @return
	 */
	public static final String getDateTime() {
		return format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取日期
	 *
	 * @param pattern
	 * @return
	 */
	public static final String getDateTime(String pattern) {
		return format(new Date(), pattern);
	}

	/**
	 * 获取日期
	 *
	 * @return
	 */
	public static final Date getCurDate() {
		return new Date();
	}

	/**
	 * 日期计算
	 *
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static final Date addDate(Date date, int field, int amount) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 字符串转换为日期:不支持yyM[M]d[d]格式
	 *
	 * @param date
	 * @return
	 */
	public static final Date stringToDate(String date) {
		if (date == null) {
			return null;
		}
		String separator = String.valueOf(date.charAt(4));
		String pattern = "yyyyMMdd";
		if (!separator.matches("\\d*")) {
			pattern = "yyyy" + separator + "MM" + separator + "dd";
			if (date.length() < 10) {
				pattern = "yyyy" + separator + "M" + separator + "d";
			}
		} else if (date.length() < 8) {
			pattern = "yyyyMd";
		}
		pattern += " HH:mm:ss.SSS";
		pattern = pattern.substring(0, Math.min(pattern.length(), date.length()));
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 字符串转换为日期:不支持yyM[M]d[d]格式
	 *
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static final Date stringToDate(String dateStr,String format) {
		if (dateStr == null) {
			return null;
		}
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

		try {
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			//e.printStackTrace();
		}

		return date;
	}

	/**
	 * 间隔分钟数
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final long getMinuteBetween(Date startDate, Date endDate) {
		return (endDate.getTime() - startDate.getTime()) / (1000 * 60);
	}

	/**
	 * 间隔小时数
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final long getHourBetween(Date startDate, Date endDate) {
		return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60);
	}

	/**
	 * 间隔天数
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final long getDayBetween(Date startDate, Date endDate) {
		return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 间隔月
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getMonthBetween(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || !startDate.before(endDate)) {
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int year1 = start.get(Calendar.YEAR);
		int year2 = end.get(Calendar.YEAR);
		int month1 = start.get(Calendar.MONTH);
		int month2 = end.get(Calendar.MONTH);
		int n = (year2 - year1) * 12;
		n = n + month2 - month1;
		return n;
	}

	/**
	 * 间隔月，多一天就多算一个月
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getMonthBetweenWithDay(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || !startDate.before(endDate)) {
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int year1 = start.get(Calendar.YEAR);
		int year2 = end.get(Calendar.YEAR);
		int month1 = start.get(Calendar.MONTH);
		int month2 = end.get(Calendar.MONTH);
		int n = (year2 - year1) * 12;
		n = n + month2 - month1;
		int day1 = start.get(Calendar.DAY_OF_MONTH);
		int day2 = end.get(Calendar.DAY_OF_MONTH);
		if (day1 <= day2) {
			n++;
		}
		return n;
	}

	/**
	 * 当天最后时间
	 * @return
	 */
	public static final Date getTodayLastTime() {
		Date now = getCurDate();
		String nowStr = format(now);
		String lastTimeStr = nowStr + END_TIME;
		Date lastTime = stringToDate(lastTimeStr);
		return lastTime;
	}

	/**
	 * 当天开始时间
	 * @return
	 */
	public static final Date getTodayFirstTime() {
		Date now = getCurDate();
		String nowStr = format(now);
		String lastTimeStr = nowStr + START_TIME;
		Date lastTime = stringToDate(lastTimeStr);
		return lastTime;
	}

	/**
	 * 上周开始时间
	 * @return
	 */
	public static final Date getLastWeekStartTime(){
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String dateStr = format(cal.getTime()) + START_TIME;
		return stringToDate(dateStr);
	}

	/**
	 * 上周结束时间
	 * @return
	 */
	public static final Date getLastWeekEndTime(){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String dateStr = format(cal.getTime()) + END_TIME;
		return stringToDate(dateStr);
	}


	/**
	 * 上月开始时间
	 * @return
	 */
	public static Date getLastMonthStartTime(){
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String dateStr = format(cal.getTime()) + START_TIME;
		return stringToDate(dateStr);
	}

	/**
	 * 上月结束时间
	 * @return
	 */
	public static final Date getLastMonthEndTime(){
		Calendar cal= Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		String dateStr = format(cal.getTime()) + END_TIME;
		return stringToDate(dateStr);
	}

	/**
	 * 获取提前或往后几个月的日期
	 * 例: -1:前一个月的开始时间 1:往后一个的开始时间
	 * @param amount 负数就是提前几个月，正数就是往后几个月
	 * @return
	 */
	public static final Date getMonthStartTime(int amount) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, amount);
		String dateStr = format(cal.getTime()) + START_TIME;
		return stringToDate(dateStr);
	}


	public static final Date getDateByMonth(int month,Date date) {
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}

	/**
	 * date2比date1多的天数
	 * @param date1
	 * @param date2
	 * @return
	 * @author	hzj
	 */
	public static int differentDays(Date date1,Date date2)
	{
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1= cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if(year1 != year2)   //同一年
		{
			int timeDistance = 0 ;
			for(int i = year1 ; i < year2 ; i ++)
			{
				if(i%4==0 && i%100!=0 || i%400==0)    //闰年
				{
					timeDistance += 366;
				}
				else    //不是闰年
				{
					timeDistance += 365;
				}
			}

			return timeDistance + (day2-day1) ;
		}
		else    //不同年
		{
			//System.out.println("判断day2 - day1 : " + (day2-day1));
			return day2-day1;
		}
	}

	//出生日期字符串转化成Date对象
	public static Date parse(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(strDate);
	}

	public static Date parse2(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(strDate);
	}

	//由出生日期获得年龄
	public static int getPersionAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) age--;
			}else{
				age--;
			}
		}
		return age;
	}

	/**
    * 根据年 月 获取对应的月份 天数
   */
	public static int getDaysByYearMonth(int year, int month) {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);

		return maxDate;

	}

	/**
    * 指定日期月份的最后一天
   */
	public static int daysCount(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, 0);
		return cal.get(Calendar.DATE);
	}

	public static void main(String[] args){
		try {
			String dateStr = "2018-09-03 15:23:38";
			Date date = DateUtils.stringToDate(dateStr);
			Date date1 = DateUtils.addDate(date, Calendar.MONTH,+3);
			System.out.println(DateUtils.format(date1, DateUtils.DATE_PATTERN_YYYY_MM_DD));
		}catch (Exception e){
			//e.printStackTrace();
		}
	}
}
