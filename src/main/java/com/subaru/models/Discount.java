package com.subaru.models;

public class Discount {
	// 优惠类型
	private int disCountType;
	private float quota;

	public Discount(int disCountType, float quota) {
		this.disCountType = disCountType;
		this.quota = quota;
	}
}
