package com.subaru.utils;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

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

}
