package com.subaru.utils;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    temp_str=sdf.format(dt);  
	    return temp_str;  
	}  
}
