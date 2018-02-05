package com.ifinance.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
   
/** 
 * 所有时间按当前2014-12-02计算 
 * @author alone 
 * 
 */ 
public class DateUtil { 
    public static String FORMAT_YYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";   
    public static String FORMAT_YYYMMDD = "yyyy-MM-dd";  
    public static String FORMAT_MMDD = "MM-dd"; 
    public static String FORMAT_HHmm = "HH:mm"; 
    public static SimpleDateFormat ymdSDF = new SimpleDateFormat(FORMAT_YYYMMDD);   
    private static String year = "yyyy";   
    private static String month = "MM";   
    private static String day = "dd";
    private static String hour = "HH";
    private static String mm = "mm";
    
    public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(FORMAT_YYMMDDHHMMSS);   
    public static SimpleDateFormat yearSDF = new SimpleDateFormat(year);   
    public static SimpleDateFormat monthSDF = new SimpleDateFormat(month);   
    public static SimpleDateFormat daySDF = new SimpleDateFormat(day);   
    public static SimpleDateFormat hourSDF = new SimpleDateFormat(hour); 
    public static SimpleDateFormat minSDF = new SimpleDateFormat(mm);
    public static SimpleDateFormat mmddSDF = new SimpleDateFormat(FORMAT_MMDD);
    public static SimpleDateFormat hhmmSDF = new SimpleDateFormat(FORMAT_HHmm);
    
    public static SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat(   
            "yyyy-MM-dd HH:mm");   
     
    public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");   
     
    public static SimpleDateFormat yyyyMMddHH_NOT_ = new SimpleDateFormat(   
            "yyyyMMdd");   
     
    public static long DATEMM = 86400L;   
    /** 
     * 获得当前时间 
     * 格式：2014-12-02 10:38:53 
     * @return String 
     */ 
    public static String getCurrentTime() {   
        return yyyyMMddHHmmss.format(new Date());   
    }   
    /** 
     * 可以获取昨天的日期 
     * 格式：2014-12-01 
     * @return String 
     */ 
    public static String getYesterdayYYYYMMDD() {   
        Date date = new Date(System.currentTimeMillis() - DATEMM * 1000L);   
        String str = yyyyMMdd.format(date);   
        try {   
            date = yyyyMMddHHmmss.parse(str + " 00:00:00");   
            return yyyyMMdd.format(date);   
        } catch (ParseException e) {   
            e.printStackTrace();   
        }   
        return "";   
    }   
    /** 
     * 可以获取后退N天的日期 
     * 格式：传入2 得到2014-11-30 
     * @param backDay 
     * @return String 
     */ 
    public String getStrDate(String backDay) { 
        Calendar calendar = Calendar.getInstance() ; 
        calendar.add(Calendar.DATE, Integer.parseInt("-" + backDay)); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ; 
        String back = sdf.format(calendar.getTime()) ; 
        return back ; 
    } 
    /** 
     *获取当前的年、月、日 
     * @return String 
     */ 
    public static String getCurrentYear() {   
        return yearSDF.format(new Date());   
    }  
    public static String getCurrentMonth() {   
        return monthSDF.format(new Date());   
    }  
    public static String getCurrentDay() {   
        return daySDF.format(new Date());   
    }  
    
    public static String getCurrentHour() {   
        return hourSDF.format(new Date());   
    }
    
    public static String getCurrentMin() {   
        return minSDF.format(new Date());   
    }
    
    public static String getCurrentHHMM() {   
        return hhmmSDF.format(new Date());   
    }
    
    public static String getCurrentMMDD() {   
        return mmddSDF.format(new Date());   
    }
    
    /** 
     * 获取年月日 也就是当前时间 
     * 格式：2014-12-02 
     * @return String 
     */ 
    public static String getCurrentymd() {   
        return ymdSDF.format(new Date());   
    }   
    /** 
     * 获取今天0点开始的秒数 
     * @return long 
     */ 
    public static long getTimeNumberToday() {   
        Date date = new Date();   
        String str = yyyyMMdd.format(date);   
        try {   
            date = yyyyMMdd.parse(str);   
            return date.getTime() / 1000L;   
        } catch (ParseException e) {   
            e.printStackTrace();   
        }   
        return 0L;   
    }   
    /** 
     * 获取今天的日期 
     * 格式：20141202 
     * @return String 
     */ 
    public static String getTodateString() {   
        String str = yyyyMMddHH_NOT_.format(new Date());   
        return str;   
    } 
    
    /** 
     * 获取昨天的日期 
     * 格式：20141201 
     * @return String 
     */ 
    public static String getYesterdayString() {   
        Date date = new Date(System.currentTimeMillis() - DATEMM * 1000L);   
        String str = yyyyMMddHH_NOT_.format(date);   
        return str;   
    }   
    /**   
     * 获得昨天零点   
     *    
     * @return Date 
     */   
    public static Date getYesterDayZeroHour() {   
        Calendar cal = Calendar.getInstance();   
        cal.add(Calendar.DATE, -1);   
        cal.set(Calendar.SECOND, 0);   
        cal.set(Calendar.MINUTE, 0);   
        cal.set(Calendar.HOUR, 0);   
        return cal.getTime();   
    }   
    /**   
     * 把long型日期转String ；---OK   
     *    
     * @param date   
     *            long型日期；   
     * @param format   
     *            日期格式；   
     * @return   
     */   
    public static String longToString(long date, String format) {   
        SimpleDateFormat sdf = new SimpleDateFormat(format);   
        // 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型   
        java.util.Date dt2 = new Date(date * 1000L);   
        String sDateTime = sdf.format(dt2); // 得到精确到秒的表示：08/31/2006 21:08:00   
        return sDateTime;   
    }   
     
    /**   
     * 获得今天零点   
     *    
     * @return Date 
     */   
    public static Date getTodayZeroHour() {   
        Calendar cal = Calendar.getInstance();   
        cal.set(Calendar.SECOND, 0);   
        cal.set(Calendar.MINUTE, 0);   
        cal.set(Calendar.HOUR, 0);   
        return cal.getTime();   
    }  
    /**   
     * 获得昨天23时59分59秒   
     *    
     * @return   
     */   
    public static Date getYesterDay24Hour() {   
        Calendar cal = Calendar.getInstance();   
        cal.add(Calendar.DATE, -1);   
        cal.set(Calendar.SECOND, 59);   
        cal.set(Calendar.MINUTE, 59);   
        cal.set(Calendar.HOUR, 23);   
        return cal.getTime();   
    }      
 
     
    /**   
     * 获得指定日期所在的自然周的第一天，即周日   
     *    
     * @param date   
     *            日期   
     * @return 自然周的第一天   
     */   
    public static Date getStartDayOfWeek(Date date) {   
        Calendar c = Calendar.getInstance();   
        c.setTime(date);   
        c.set(Calendar.DAY_OF_WEEK, 1);   
        date = c.getTime();   
        return date;   
    }   
     
    /**   
     * 获得指定日期所在的自然周的最后一天，即周六   
     *    
     * @param date   
     * @return   
     */   
    public static Date getLastDayOfWeek(Date date) {   
        Calendar c = Calendar.getInstance();   
        c.setTime(date);   
        c.set(Calendar.DAY_OF_WEEK, 7);   
        date = c.getTime();   
        return date;   
    }   
     
    /**   
     * 获得指定日期所在当月第一天   
     *    
     * @param date   
     * @return   
     */   
    public static Date getStartDayOfMonth(Date date) {   
        Calendar c = Calendar.getInstance();   
        c.setTime(date);   
        c.set(Calendar.DAY_OF_MONTH, 1);   
        date = c.getTime();   
        return date;   
    }   
     
    /**   
     * 获得指定日期所在当月最后一天   
     *    
     * @param date   
     * @return   
     */   
    public static Date getLastDayOfMonth(Date date) {   
        Calendar c = Calendar.getInstance();   
        c.setTime(date);   
        c.set(Calendar.DATE, 1);   
        c.add(Calendar.MONTH, 1);   
        c.add(Calendar.DATE, -1);   
        date = c.getTime();   
        return date;   
    }   
     
    /**   
     * 获得指定日期的下一个月的第一天   
     *    
     * @param date   
     * @return   
     */   
    public static Date getStartDayOfNextMonth(Date date) {   
        Calendar c = Calendar.getInstance();   
        c.setTime(date);   
        c.add(Calendar.MONTH, 1);   
        c.set(Calendar.DAY_OF_MONTH, 1);   
        date = c.getTime();   
        return date;   
    }   
     
    /**   
     * 获得指定日期的下一个月的最后一天   
     *    
     * @param date   
     * @return   
     */   
    public static Date getLastDayOfNextMonth(Date date) {   
        Calendar c = Calendar.getInstance();   
        c.setTime(date);   
        c.set(Calendar.DATE, 1);   
        c.add(Calendar.MONTH, 2);   
        c.add(Calendar.DATE, -1);   
        date = c.getTime();   
        return date;   
    }   
     
    /**   
     *    
     * 求某一个时间向前多少秒的时间(currentTimeToBefer)---OK   
     *    
     * @param givedTime   
     *            给定的时间   
     * @param interval   
     *            间隔时间的毫秒数；计算方式 ：n(天)*24(小时)*60(分钟)*60(秒)(类型)   
     * @param format_Date_Sign   
     *            输出日期的格式；如yyyy-MM-dd、yyyyMMdd等；   
     */   
    public static String givedTimeToBefer(String givedTime, long interval,   
            String format_Date_Sign) {   
        String tomorrow = null;   
        try {   
            SimpleDateFormat sdf = new SimpleDateFormat(format_Date_Sign);   
            Date gDate = sdf.parse(givedTime);   
            long current = gDate.getTime(); // 将Calendar表示的时间转换成毫秒   
            long beforeOrAfter = current - interval * 1000L; // 将Calendar表示的时间转换成毫秒   
            Date date = new Date(beforeOrAfter); // 用timeTwo作参数构造date2   
            tomorrow = new SimpleDateFormat(format_Date_Sign).format(date);   
        } catch (ParseException e) {   
            e.printStackTrace();   
        }   
        return tomorrow;   
    }   
    /**   
     * 把String 日期转换成long型日期；---OK   
     *    
     * @param date   
     *            String 型日期；   
     * @param format   
     *            日期格式；   
     * @return   
     */   
    public static long stringToLong(String date, String format) {   
        SimpleDateFormat sdf = new SimpleDateFormat(format);   
        Date dt2 = null;   
        long lTime = 0;   
        try {   
            dt2 = sdf.parse(date);   
            // 继续转换得到秒数的long型   
            lTime = dt2.getTime() / 1000;   
        } catch (ParseException e) {   
            e.printStackTrace();   
        }   
     
        return lTime;   
    }   
     
    /**   
     * 得到二个日期间的间隔日期；   
     *    
     * @param endTime   
     *            结束时间   
     * @param beginTime   
     *            开始时间   
     * @param isEndTime   
     *            是否包含结束日期；   
     * @return   
     */   
    public static Map<String, String> getTwoDay(String endTime,   
            String beginTime, boolean isEndTime) {   
        Map<String, String> result = new HashMap<String, String>();   
        if ((endTime == null || endTime.equals("") || (beginTime == null || beginTime   
                .equals(""))))   
            return null;   
        try {   
            java.util.Date date = ymdSDF.parse(endTime);   
            endTime = ymdSDF.format(date);   
            java.util.Date mydate = ymdSDF.parse(beginTime);   
            long day = (date.getTime() - mydate.getTime())   
                    / (24 * 60 * 60 * 1000);   
            result = getDate(endTime, Integer.parseInt(day + ""), isEndTime);   
        } catch (Exception e) {   
        }   
        return result;   
    }   
     
    /**   
     * 得到二个日期间的间隔日期；   
     *    
     * @param endTime   
     *            结束时间   
     * @param beginTime   
     *            开始时间   
     * @param isEndTime   
     *            是否包含结束日期；   
     * @return   
     */   
    public static Integer getTwoDayInterval(String endTime, String beginTime,   
            boolean isEndTime) {   
        if ((endTime == null || endTime.equals("") || (beginTime == null || beginTime   
                .equals(""))))   
            return 0;   
        long day = 0l;   
        try {   
            java.util.Date date = ymdSDF.parse(endTime);   
            endTime = ymdSDF.format(date);   
            java.util.Date mydate = ymdSDF.parse(beginTime);   
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);   
        } catch (Exception e) {   
            return 0 ;   
        }   
        return Integer.parseInt(day + "");   
    }   
     
    /**   
     * 根据结束时间以及间隔差值，求符合要求的日期集合；   
     *    
     * @param endTime   
     * @param interval   
     * @param isEndTime   
     * @return   
     */   
    public static Map<String, String> getDate(String endTime, Integer interval,   
            boolean isEndTime) {   
        Map<String, String> result = new HashMap<String, String>();   
        if (interval == 0 || isEndTime) {   
            if (isEndTime)   
                result.put(endTime, endTime);   
        }   
        if (interval > 0) {   
            int begin = 0;   
            for (int i = begin; i < interval; i++) {   
                endTime = givedTimeToBefer(endTime, DATEMM, FORMAT_YYYMMDD);   
                result.put(endTime, endTime);   
            }   
        }   
        return result;   
    } 
   
    public static String dateToString(Date date,String formate) {
    	
    	SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(formate); 
    	
        return yyyyMMddHHmmss.format(date);   
    }
    
    public static Date stringToDate(String date, String format) {   
        SimpleDateFormat sdf = new SimpleDateFormat(format);   
        try {   
            return sdf.parse(date);   
        } catch (ParseException e) {   
            return null;   
        }   
    }   
    
    /**
     * 近7天的数据
     * @param day
     * @return
     */
	public static List<String> getDayOld6() {
		List<String> list = new ArrayList<String>();
		Date beginDate = new Date();
			for (int i = 0; i <= 6; i++) {
				Calendar date = Calendar.getInstance();
				date.setTime(beginDate);
				date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
				String oldDay = ymdSDF.format(date.getTime());
				list.add(oldDay);
			}

		return list;
	}
	
	/**
	 * 判断日期是不是今天
	 * @param yyymmdd
	 * @return
	 */
	public static boolean isToday(String yyymmdd)
	{
		String nowDay = DateUtil.dateToString(new Date(), DateUtil.FORMAT_YYYMMDD);
		if(nowDay.equals(yyymmdd))
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	/**
	 * 计算两个日期中间的间隔自然日
	 * @param oldTime
	 * @param nowTime
	 * @return
	 */
	public static long betweenDay(long oldTime ,long nowTime){
		/**
		   * 计算规则————按自然天数计算
		   * 实现方式：
		   * 1，程序启动时记录保护开始时间
		   * 2，此后进主界面与此时间求差值，在此后(0, 24]小时区间内不跨到第二天记1天，跨天记2天，以此类推(24, 48]小时区间不跨到第三天记2天，跨天记3天...画数轴就知道怎么回事了
		* */

		//程序启动时
		long start = oldTime;

		//需要计算运行天数时，计算差值
		long end = nowTime;
		long days = (end - start) / (1000 * 60 * 60 * 24);
		//运行天数从1开始计数
		long runningDays = days + 1;
		//判断是否跨天，若跨天，运行天数还要+1
		long probableEndMillis = start + (1000 * 60 * 60 * 24) * days;
		if(new Date(probableEndMillis).getDay() != new Date(end).getDay())
		{
		 runningDays++;
		}

		return runningDays;
	}
	
	/**
	 * 本周第一天
	 * @return
	 */
	public static Date getWeekStartDate(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		return date;
	}
	
	/**
	 * 本周最后一天
	 * @return
	 */
	public static Date getWeekEndDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_WEEK, 6);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		Date date = cal.getTime();
		return date;
	}
	
	/**
	 * 本月第一天
	 * @return
	 */
	public static Date getMonthFirstDate() {
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
        
        System.out.println("===============first:"+c.getTime());
        Date date = c.getTime();
		return date;
	}

	/**
	 * 本月最后一天
	 * @return
	 */
	public static Date getMonthEndDate() {
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        ca.set(Calendar.MILLISECOND, 999);
        System.out.println("===============end:"+ca.getTime());
        Date date = ca.getTime();
		return date;
	}
	
	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(getMonthEndDate()));
	}
	
	/**
     * 获取指定日期所在周的第一天和最后一天,用下划线连接
     * @param dataStr
     * @return
     * @throws ParseException
     */
    public static String getFirstAndLastOfMonth(String dataStr,String dateFormat,String resultDateFormat) throws ParseException {
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.setTime(new SimpleDateFormat(dateFormat).parse(dataStr));
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String first = new SimpleDateFormat(resultDateFormat).format(c.getTime());
        System.out.println("===============first:"+first);

        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = new SimpleDateFormat(resultDateFormat).format(ca.getTime());
        System.out.println("===============last:"+last);
        return first+"_"+last;
    }

    /**
     * 每周的第一天和最后一天
     * @param dataStr
     * @param dateFormat
     * @param resultDateFormat
     * @return
     * @throws ParseException
     */
    public static String getFirstAndLastOfWeek(String resultDateFormat) {
        Calendar cal = Calendar.getInstance();
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        // 所在周开始日期
        String data1 = new SimpleDateFormat(resultDateFormat).format(cal.getTime());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        // 所在周结束日期
        String data2 = new SimpleDateFormat(resultDateFormat).format(cal.getTime());
        return data1 + "_" + data2;

    }
}