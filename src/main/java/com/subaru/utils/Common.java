package com.subaru.utils;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.subaru.constants.FIELDS;

public class Common {
	// 获取登陆人的key
	public static String getLoginTel(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		for (Cookie c : cookies) {
			if (c.getName().equals("pauth")) {
				String values = c.getValue();
				String[] args = values.split("\\|");
				try {
					return args[0];
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public static String GetNowDate(){  
	    String temp_str="";  
	    Date dt = new Date();  
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    temp_str=sdf.format(dt);  
	    return temp_str;  
	}
	
	public static boolean isDate(String format, String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		
		return true;
	}
	
	/** 
     * 手机号验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }
    
    public static boolean isEmail(String email){  
        if (null==email || "".equals(email)) return false;    
//      Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配  
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配  
        Matcher m = p.matcher(email);  
        return m.matches();  
       }  
}
