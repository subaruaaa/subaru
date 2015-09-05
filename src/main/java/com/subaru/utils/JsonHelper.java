package com.subaru.utils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonHelper {
	public static ResponseEntity<String> jsonpEntity(Object obj, String callback) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		String output;
		Gson gson = new GsonBuilder().create();
		output = gson.toJson(obj);
		if (callback != null)
			output = callback + "(" + output + ")";

		return new ResponseEntity<String>(output, headers, HttpStatus.OK);
	}

	public static String encode(String s) {
		StringBuilder sb = new StringBuilder(s.length() * 3);
		for (char c : s.toCharArray()) {
			if (c < 256) {
				sb.append(c);
			} else {
				sb.append("\\u");
				sb.append(Character.forDigit((c >>> 12) & 0xf, 16));
				sb.append(Character.forDigit((c >>> 8) & 0xf, 16));
				sb.append(Character.forDigit((c >>> 4) & 0xf, 16));
				sb.append(Character.forDigit((c) & 0xf, 16));
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Gson gson = new GsonBuilder().create();
		Map<String, String> a = new HashMap<String, String>();
		a.put("a", "我《");
		String b = gson.toJson(a);
		System.out.println(JsonHelper.encode(b));
	}
}
