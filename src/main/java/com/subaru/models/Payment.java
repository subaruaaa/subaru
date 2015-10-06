package com.subaru.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Payment {
	public static List<String> mortgageBanks = new ArrayList<String>(
			Arrays.asList("建设银行", "交通银行", "工商银行", "招商银行", "平安银行"));

	// 0,全款，1贷款
	private int type;
	// 按揭银行
	private String mortgageBank;
	// 按揭金额
	private Float mortgageAmount;

	public Payment(int type, String mortgageBank, Float mortgageAmount) {
		this.type = type;
		this.mortgageBank = mortgageBank;
		this.mortgageAmount = mortgageAmount;
	}

	public Payment(String payment) {
		String[] paymentArray = payment.split("-");
		this.type = Integer.parseInt(paymentArray[0]);
		this.mortgageBank = paymentArray[1];
		this.mortgageAmount = Float.valueOf(paymentArray[2]);
	}

	@Override
	public String toString() {
		return "" + type + "-" + mortgageBank + "-" + mortgageAmount;
	}
}
