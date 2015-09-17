package com.subaru.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class LoginHelper {

	public static boolean isLogin(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("pauth")) {
					String values = c.getValue();
					String[] args = values.split("\\|");
					try {
						if (HashHelper.md5(args[1] + "laixiaohang").equals(
								args[2])) {
							return true;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		return false;
	}
}
