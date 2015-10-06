package com.subaru.models;

import java.util.HashMap;
import java.util.Map;

public class Discount {
	public static Map<Integer, String> map = new HashMap<Integer, String>() {
		{
			put(1, "现金优惠");
			put(2, "送装备");
		}
	};

	// 优惠类型
	private int disCountType;
	private String type;
	private float quota;

	// disCountType-quota
	public Discount(String discount) {
		String[] discountArray = discount.split("-");
		this.disCountType = Integer.valueOf(discountArray[0]);
		this.type = map.get(disCountType);
		this.quota = Float.valueOf(discountArray[1]);
	}

	public Discount(int discountType, float quota) {
		this.disCountType = discountType;
		this.type = map.get(disCountType);
		this.quota = quota;
	}

	public String toString() {
		return "" + disCountType + "-" + quota;
	}
}
