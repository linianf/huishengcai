package com.hsh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class HSHUtil {
	
	public static String toString(Object obj) {
		if(obj == null)
			return "";
		
		return obj.toString();
	}
	
	public static int toInt(Object obj) {
		int a = 0;
		try {
			if (obj != null)
				a = Integer.parseInt(obj.toString());
		} catch (Exception e) {

		}
		return a;
	}
	
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmm");
		String s = outFormat.format(now);
		return s;
	}
	
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(date);
		return strDate;
	}
	
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
	
	public static String  genVarifyCode(){
		String result = "";
		for(int j=0;j<6;j++){
			result = result + (int)(Math.random()*10);
		}
		return result;
	}
	
	public static String getCharacterEncoding() {
		return "utf-8";
	}
	
	public static long getUnixTime(Date date) {
		if( null == date ) {
			return 0;
		}
		
		return date.getTime()/1000;
	}
		
	public static String date2String(Date date, String formatType) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		try {
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "2018-07-15 00:00:00";
	}
	
	public static Date date2String(String dateString, String formatType) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
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
	
	/**
	 * 计算2个date之间的天数、 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int  calacDaysFrom2Date(Date  startDate, Date  endDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		try{
			startDate=sdf.parse(sdf.format(startDate));  
			endDate=sdf.parse(sdf.format(endDate));  
		    Calendar cal = Calendar.getInstance();    
			cal.setTime(startDate);    
			long time1 = cal.getTimeInMillis();                 
			cal.setTime(endDate);    
			long time2 = cal.getTimeInMillis();         
			long between_days=(time2-time1)/(1000*3600*24);  
			return Integer.parseInt(String.valueOf(between_days));  
		}catch(Exception e){
			
		}
        return 0; 
	}
	
	/**
	 * 计算当前日期0点0分0秒
	 * @param nowDate
	 * @return
	 */
	public static String  calacDaysFromToday(Date  nowDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		try{
			  Calendar calendar = Calendar.getInstance();
			  calendar.setTime(new Date()); 
			  calendar.set(Calendar.HOUR_OF_DAY,0);
			  calendar.set(Calendar.MINUTE,0);
			  calendar.set(Calendar.SECOND,0);
			  calendar.set(Calendar.MILLISECOND,0);
			  return sdf.format(calendar.getTime()); 
		}catch(Exception e){
			
		}
        return null; 
	}
	
	/**
	 * 计算当前日期 2天前的0点0分0秒
	 * @param nowDate
	 * @return
	 */
	public static String  calacDaysFrom2beforeToday(Date  nowDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		try{
			  Calendar calendar = Calendar.getInstance();
			  calendar.setTime(new Date()); 
			  calendar.add(Calendar.DAY_OF_MONTH, -2);
			  calendar.set(Calendar.HOUR_OF_DAY,0);
			  calendar.set(Calendar.MINUTE,0);
			  calendar.set(Calendar.SECOND,0);
			  calendar.set(Calendar.MILLISECOND,0);
			  
			  return sdf.format(calendar.getTime()); 
		}catch(Exception e){
			
		}
        return null; 
	}
	
	/**
	 * 计算当前日期所在周的第一天 0点0分0秒
	 * @param nowDate
	 * @return
	 */
	public static String  calacDaysFromThisWeek(Date  nowDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		try{
			  Calendar calendar = Calendar.getInstance();
			  calendar.setFirstDayOfWeek(Calendar.MONDAY); 
			  calendar.setTime(new Date()); 
			  calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()); // Monday 
			  calendar.set(Calendar.HOUR_OF_DAY,0);
			  calendar.set(Calendar.MINUTE,0);
			  calendar.set(Calendar.SECOND,0);
			  calendar.set(Calendar.MILLISECOND,0);
			  return sdf.format(calendar.getTime()); 
		}catch(Exception e){
			
		}
        return null; 
	}
	
	/**
	 * 计算当前日期所在月的第一天 0点0分0秒
	 * @param nowDate
	 * @return
	 */
	public static String  calacDaysFromThisMonth(Date  nowDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		try{
			  Calendar calendar = Calendar.getInstance();
			  calendar.setTime(new Date()); 
			  calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
			  calendar.set(Calendar.HOUR_OF_DAY,0);
			  calendar.set(Calendar.MINUTE,0);
			  calendar.set(Calendar.SECOND,0);
			  calendar.set(Calendar.MILLISECOND,0);
			  return sdf.format(calendar.getTime()); 
		}catch(Exception e){
			
		}
        return null; 
	}
	
	
    private static WebClient webClient = new WebClient();
    
	public String  getQuanFromHSH(int amount) throws  Exception{
		String  quanURL = "";//惠生活购物卷的获取接口
		List<NameValuePair> requestEntity = new ArrayList<NameValuePair>();
		requestEntity.add(new BasicNameValuePair("amount",String.valueOf(amount)));
		String response = webClient.doPost4Html(quanURL, null,null, requestEntity, "utf-8");
		//TODO 从响应中解析出卷号
		return parseQuanFromResponse(response);
	}
	
	private String  parseQuanFromResponse(String response){
		return null;
	}
	
	
    public static void main(String[] args){
    	System.out.println(HSHUtil.calacDaysFrom2beforeToday(new Date()));
    	
    }
	
}
