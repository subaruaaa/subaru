package com.subaru.services;

import org.springframework.stereotype.Component;

@Component
public class CommonMessage {

	private static ThreadLocal<String> tel = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return "";
		}
	};

	public static void setTel(String tel) {
		CommonMessage.tel.set(tel);
	}

	public static String getTel() {
		return getTel();
	}

	public static void clear() {
		CommonMessage.tel.remove();
	}

}
