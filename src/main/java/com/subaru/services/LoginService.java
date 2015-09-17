package com.subaru.services;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.subaru.dao.SubaruDao;
import com.subaru.utils.HashHelper;

@Component
public class LoginService {
	@Autowired
	SubaruDao subaruDao;

	public static String salt = "laixiaohang";
	// 修改密码的时间
	Map<String, Long> tel_codetime = new HashMap<String, Long>();
	
	public void setCodetime(String employeeTel){
		tel_codetime.put(employeeTel, System.currentTimeMillis());
	}

	public boolean isChangePasswd(String employeeTel) {
		Long codeTime = tel_codetime.get(employeeTel);
		if (codeTime == null) {
			return false;
		}
		if (System.currentTimeMillis() - codeTime <= 3 * 60 * 1000L) {
			return true;
		} else
			return false;
	}

	public void clearTelCodeMap(String employeeTel) {
		tel_codetime.remove(employeeTel);
	}

	public boolean login(String employeeTel, String passwd,
			HttpServletResponse response) {
		String md5Passwd = HashHelper.md5(passwd + salt);
		if (subaruDao.getMd5Passwd(employeeTel, md5Passwd).size() == 1) {
			Cookie cookie = new Cookie("pauth", employeeTel + "|" + passwd
					+ "|" + md5Passwd);
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60 * 24 * 30);
			response.addCookie(cookie);
			return true;
		}
		return false;
	}

	public void savePasswd(String employeeTel, String newPasswd) {
		newPasswd = HashHelper.md5(newPasswd + LoginService.salt);
		subaruDao.saveMd5Passwd(employeeTel, newPasswd);
	}
}
